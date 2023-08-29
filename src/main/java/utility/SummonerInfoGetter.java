package utility;

import org.json.JSONObject;

public class SummonerInfoGetter extends AccessApi{

    private final String BASE_URL = "https://na1.api.riotgames.com";
    private final String END_POINT = "/lol/summoner/v4/summoners/by-name/";

    public String getAccountId(String summonerName) {
        JSONObject jsonObject = (JSONObject) accessApi(BASE_URL, summonerName, END_POINT);
        String accountId = jsonObject.getString("id");
        return accountId;
    }

    public String getPuuid(String summonerName) {
        JSONObject jsonObject = (JSONObject) accessApi(BASE_URL, summonerName, END_POINT);
        String puuid = jsonObject.getString("puuid");
        return puuid;
    }


}
