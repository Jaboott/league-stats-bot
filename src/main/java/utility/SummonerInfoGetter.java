package utility;

import org.json.JSONObject;

public class SummonerInfoGetter {

    private AccessApi access = new AccessApi();

    public SummonerInfoGetter() {
        access.setEndPoint("/lol/summoner/v4/summoners/by-name/");
    }

    public String getAccountId(String summonerName) {
        JSONObject jsonObject = (JSONObject) access.accessApi(summonerName);
        String accountId = jsonObject.getString("id");
        return accountId;
    }

    public String getPuuid(String summonerName) {
        JSONObject jsonObject = (JSONObject) access.accessApi(summonerName);
        String puuid = jsonObject.getString("puuid");
        return puuid;
    }


}
