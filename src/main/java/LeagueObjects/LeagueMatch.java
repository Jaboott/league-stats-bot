package LeagueObjects;

import org.json.JSONArray;
import utility.AccessApi;
import utility.SummonerInfoGetter;

import java.util.List;

public class LeagueMatch extends SummonerInfoGetter{

    private List<LeaguePlayer> redTeam;
    private List<LeaguePlayer> blueTeam;
    private String matchId;
    private SummonerInfoGetter playerFactory = new SummonerInfoGetter();
    private AccessApi access = new AccessApi();
    // Todo: make it so it shows all the player in the game, who won, what their highest mastery are.

    public JSONArray getMatches(String summonerName, int numGames) {
        JSONArray matchList = (JSONArray) accessApi("https://americas.api.riotgames.com" ,getPuuid(summonerName),
                "/ids?type=ranked&start=0&count=" + numGames + "&", "/lol/match/v5/matches/by-puuid/");
        return matchList;
    }


}
