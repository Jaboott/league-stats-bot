package utility;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class LeaguePlayer{

    private int level;
    private String summonerName;
    private String rank = "unranked";
    private int leaguePoint;
    private int rankedWins;
    private int rankedLosses;
    private int normalWins;
    private int normalLosses;
    private final String BASE_URL = "https://na1.api.riotgames.com";
    private final String END_POINT = "/lol/league/v4/entries/by-summoner/";
    private String API_KEY;
    private String encryptedSummonerID;

    {
        try {
            API_KEY = KeyHandler.getKey("RiotApiKey");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public LeaguePlayer(String encryptedSummonerID) {
        this.encryptedSummonerID = encryptedSummonerID;
        initPlayer(accessApi(encryptedSummonerID));
    }

    private JSONArray accessApi(String encryptedSummonerID) {
        try {
            JSONArray playerData = (JSONArray) UrlReader.readJsonFromUrl
                    (BASE_URL + END_POINT + encryptedSummonerID + "?api_key=" + API_KEY);
            return playerData;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void initPlayer(JSONArray playerData) {
        for(int i = 0; i < playerData.length(); i++) {
            JSONObject jsonObject = playerData.getJSONObject(i);
            if (jsonObject.getString("queueType").equals("RANKED_SOLO_5x5")) {
                rank = jsonObject.getString("tier") + " " + jsonObject.getString("rank");
                leaguePoint = jsonObject.getInt("leaguePoints");
                rankedWins = jsonObject.getInt("wins");
                rankedLosses = jsonObject.getInt("losses");
            } else if (jsonObject.getString("queueType").equals("CHERRY")) {
                normalWins = jsonObject.getInt("wins");
                normalLosses = jsonObject.getInt("losses");
                summonerName = jsonObject.getString("summonerName");
            }
        }
    }

    @Override
    public String toString() {
        return "summonerID: %s \n summonerName: %s \n rank: %s, lp: %d \n rankedWins: %d, rankedLosses: %d \n normalWins: %d, normalLosses: %d".formatted(
                encryptedSummonerID ,summonerName, rank, leaguePoint, rankedWins, rankedLosses, normalWins, normalLosses);
    }
}
