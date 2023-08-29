package LeagueObjects;

import org.json.JSONArray;
import org.json.JSONObject;
import utility.AccessApi;
import utility.SummonerInfoGetter;

import java.util.ArrayList;
import java.util.List;

public class LeaguePlayer extends SummonerInfoGetter{

    private int level;
    private String summonerName;
    private String rank = "unranked";
    private int leaguePoint;
    private int rankedWins;
    private int rankedLosses;
    private int normalWins;
    private int normalLosses;
    private String encryptedSummonerID;
    private List<LeagueMatch> matches = new ArrayList<>();

    public LeaguePlayer(String summonerName) {
        summonerName = summonerName.replaceAll(" ", "");
        this.summonerName = summonerName;
        encryptedSummonerID = getAccountId(summonerName);
        level = getLevel(summonerName);
        initPlayer((JSONArray) accessApi("https://na1.api.riotgames.com","/lol/league/v4/entries/by-summoner/", encryptedSummonerID));
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

    public void getMatchHistory(int numMatchHistory, boolean detailedMatch) {
        JSONArray recentMatches = getMatches(summonerName, numMatchHistory);
        for (int i = 0; i < recentMatches.length(); i++) {
            System.out.println("match:" + (i + 1));
            String matchId = recentMatches.getString(i);
            LeagueMatch match = new LeagueMatch(matchId);
            if (detailedMatch) {
                System.out.println(match.getMatch());
            } else {
                System.out.println(match.getPlayerStat(encryptedSummonerID));
            }
        }
    }

    @Override
    public String toString() {
        return "summonerID: %s \n summonerName: %s, summonerLevel: %d \n rank: %s, lp: %d \n rankedWins: %d, rankedLosses: %d \n normalWins: %d, normalLosses: %d, normal win rate for the past %d games: %d%%\n".formatted(
                encryptedSummonerID ,summonerName, level, rank, leaguePoint, rankedWins, rankedLosses, normalWins, normalLosses, normalLosses + normalWins,
                Math.round(((double) normalWins / (double)(normalLosses + normalWins)) * 100));
    }
}
