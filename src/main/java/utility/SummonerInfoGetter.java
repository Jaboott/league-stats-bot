package utility;

import org.json.JSONArray;
import org.json.JSONObject;

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

    //protected JSONArray


}
