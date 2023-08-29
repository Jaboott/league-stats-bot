package LeagueObjects;

import utility.AccessApi;
import utility.SummonerInfoGetter;

import java.util.List;

public class LeagueMatch{

    private List<LeaguePlayer> redTeam;
    private List<LeaguePlayer> blueTeam;
    private String matchId;
    private SummonerInfoGetter playerFactory = new SummonerInfoGetter();
    private AccessApi access = new AccessApi();
    // Todo: make it so it shows all the player in the game, who won, what their highest mastery are.

    public LeagueMatch() {
        access.setEndPoint();
    }


}
