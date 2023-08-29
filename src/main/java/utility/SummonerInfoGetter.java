package utility;

import org.json.JSONObject;

public class SummonerInfoGetter extends AccessApi{

    public SummonerInfoGetter() {
        setEndPoint("/lol/summoner/v4/summoners/by-name/");
    }

    public String getAccountId(String summonerName) {
        JSONObject jsonObject = (JSONObject) accessApi(summonerName);
        String accountId = jsonObject.getString("id");
        return accountId;
    }

    public String getPuuid(String summonerName) {
        JSONObject jsonObject = (JSONObject) accessApi(summonerName);
        String puuid = jsonObject.getString("puuid");
        return puuid;
    }


}
