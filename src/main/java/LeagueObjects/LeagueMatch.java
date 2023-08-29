package LeagueObjects;

import org.json.JSONArray;
import org.json.JSONObject;
import utility.AccessApi;
import utility.SummonerInfoGetter;

import java.util.ArrayList;
import java.util.List;

public class LeagueMatch extends SummonerInfoGetter{

    private List<LeaguePlayer> redTeam;
    private List<LeaguePlayer> blueTeam;
    private String matchId;
    private SummonerInfoGetter playerFactory = new SummonerInfoGetter();
    private AccessApi access = new AccessApi();
    // Todo: make it so it shows all the player in the game, who won, what their highest mastery are.

    public LeagueMatch(String matchId) {
        this.matchId = matchId;
        initMatch();
    }

    private void initMatch() {
        JSONObject matchData = (JSONObject) accessApi("https://americas.api.riotgames.com/lol", "/match/v5/matches/", matchId);
        JSONArray players = matchData.getJSONObject("metadata").getJSONArray("participants");
        List<LeaguePlayer> leaguePlayerList = new ArrayList<>();
        for (int i = 0; i < players.length(); i++) {
            leaguePlayerList.add(new LeaguePlayer(getAccountName(players.getString(i))));
        }
        System.out.println(leaguePlayerList);
    }


}
