import utility.SummonerAPI;

public class LeagueHandler {
    public static void main(String[] args) {
        SummonerAPI test = new SummonerAPI();
        System.out.println(test.makeLeaguePlayer("kojaxz").toString());
    }
}
