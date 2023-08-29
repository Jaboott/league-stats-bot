package utility;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class LeaguePlayer extends AccessApi{

    private int level;
    private String summonerName;
    private String rank = "unranked";
    private int leaguePoint;
    private int rankedWins;
    private int rankedLosses;
    private int normalWins;
    private int normalLosses;
    private String encryptedSummonerID;

    public LeaguePlayer(String encryptedSummonerID) {
        this.encryptedSummonerID = encryptedSummonerID;
        setBASE_URL("https://na1.api.riotgames.com");
        setEND_POINT("/lol/league/v4/entries/by-summoner/");
        initPlayer((JSONArray) accessApi(encryptedSummonerID));
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

    // Todo
    public void getMatchHistory() {

    }

    @Override
    public String toString() {
        return "summonerID: %s \n summonerName: %s \n rank: %s, lp: %d \n rankedWins: %d, rankedLosses: %d \n normalWins: %d, normalLosses: %d, normal win rate for the past %d games: %d%%".formatted(
                encryptedSummonerID ,summonerName, rank, leaguePoint, rankedWins, rankedLosses, normalWins, normalLosses, normalLosses + normalWins,
                Math.round(((double) normalWins / (double)(normalLosses + normalWins)) * 100));
    }
}
