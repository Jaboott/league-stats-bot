package utility;

import org.json.JSONObject;

import java.io.IOException;

public class SummonerAPI {

    private final String BASE_URL = "https://na1.api.riotgames.com";
    private final String END_POINT = "/lol/summoner/v4/summoners/by-name/";
    private String API_KEY;

    {
        try {
            API_KEY = KeyHandler.getKey("RiotApiKey");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private JSONObject accessApi(String summonerName) {
        try {
            JSONObject jsonObject = (JSONObject) UrlReader.readJsonFromUrl
                    (BASE_URL + END_POINT + summonerName + "?api_key=" + API_KEY);
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getAccountId(String summonerName, String key) {
        JSONObject jsonObject = accessApi(summonerName);
        String accountId = jsonObject.getString(key);
        return accountId;
    }

    public LeaguePlayer makeLeaguePlayer(String summonerName) {
        return new LeaguePlayer(getAccountId(summonerName, "id"));
    }

}
