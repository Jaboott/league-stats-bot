package utility;

import org.json.JSONObject;

public class SummonerFactory extends AccessApi{

    private String getAccountId(String summonerName, String key) {
        setBASE_URL("https://na1.api.riotgames.com");
        setEND_POINT("/lol/summoner/v4/summoners/by-name/");
        JSONObject jsonObject = (JSONObject) accessApi(summonerName);
        String accountId = jsonObject.getString(key);
        return accountId;
    }

    public LeaguePlayer makeLeaguePlayer(String summonerName) {
        return new LeaguePlayer(getAccountId(summonerName, "id"));
    }

}
