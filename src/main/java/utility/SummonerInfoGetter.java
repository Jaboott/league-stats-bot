package utility;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SummonerInfoGetter extends AccessApi{

    private final String BASE_URL = "https://na1.api.riotgames.com";
    private final String END_POINT = "/lol/summoner/v4/summoners/by-name/";

    protected String getAccountId(String summonerName) {
        JSONObject jsonObject = (JSONObject) accessApi(BASE_URL, END_POINT, summonerName);
        String accountId = jsonObject.getString("id");
        return accountId;
    }

    protected String getPuuid(String summonerName) {
        JSONObject jsonObject = (JSONObject) accessApi(BASE_URL, END_POINT, summonerName);
        String puuid = jsonObject.getString("puuid");
        return puuid;
    }

    protected int getLevel(String summonerName) {
        JSONObject jsonObject = (JSONObject) accessApi(BASE_URL, END_POINT, summonerName);
        int level = jsonObject.getInt("summonerLevel");
        return level;
    }

    protected JSONArray getMatches(String summonerName, int numGames) {
        JSONArray matchList = (JSONArray) accessApi("https://americas.api.riotgames.com" ,"/lol/match/v5/matches/by-puuid/"
                ,getPuuid(summonerName), "/ids?type=ranked&start=0&count=" + numGames + "&");
        return matchList;
    }

    protected JSONArray getTopChampions(String summonerName) {
        JSONArray topChampions = (JSONArray) accessApi("https://na1.api.riotgames.com" ,"/lol/champion-mastery/v4/champion-masteries/by-summoner/"
                ,getAccountId(summonerName), "/top?");
        return topChampions;
    }

    protected boolean playerExist(String summonerName) {
        URL url = null;
        try {
            url = new URL((BASE_URL + END_POINT + summonerName  + "?api_key=" + KeyHandler.getKey("RiotApiKey")));
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            int statusCode = http.getResponseCode();
            System.out.println(statusCode);
            if (statusCode == 404) {
                return false;
            } else {
                return true;
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
