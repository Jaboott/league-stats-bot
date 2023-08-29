package utility;

import org.json.JSONObject;

public class SummonerFactory{

    private AccessApi access = new AccessApi();

    public SummonerFactory() {
        access.setBASE_URL("https://na1.api.riotgames.com");
        access.setEND_POINT("/lol/summoner/v4/summoners/by-name/");
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
