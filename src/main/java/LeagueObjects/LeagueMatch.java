package LeagueObjects;

import org.json.JSONArray;
import org.json.JSONObject;
import utility.AccessApi;
import utility.SummonerInfoGetter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeagueMatch extends SummonerInfoGetter{

    private Map<String, PlayerRecord> wonTeam = new HashMap<>();
    private Map<String, PlayerRecord> lostTeam = new HashMap<>();
    private JSONObject matchData;
    // Todo: make it so it shows all the player in the game, who won, what their highest mastery are.

    public LeagueMatch(String matchId) {
        matchData = (JSONObject) accessApi("https://americas.api.riotgames.com/lol", "/match/v5/matches/", matchId);
    }

    public LeagueMatch getMatch() {
        JSONArray participants = matchData.getJSONObject("info").getJSONArray("participants");
        for (int i = 0; i < participants.length(); i++) {
            JSONObject player = participants.getJSONObject(i);
            if (player.getBoolean("win") == true) {
                wonTeam.put(player.getString("summonerName"), new PlayerRecord(player.getString("summonerName"), player.getInt("summonerLevel"), player.getString("championName"),
                        player.getInt("kills"), player.getInt("deaths"), player.getInt("assists"), player.getBoolean("win")));
            } else {
                lostTeam.put(player.getString("summonerName"), new PlayerRecord(player.getString("summonerName"), player.getInt("summonerLevel"), player.getString("championName"),
                        player.getInt("kills"), player.getInt("deaths"), player.getInt("assists"), player.getBoolean("win")));
            }
        }
        return this;
    }

    public PlayerRecord getPlayerStat(String summonerId) {
        JSONArray participants = matchData.getJSONObject("info").getJSONArray("participants");
        for (int i = 0; i < participants.length(); i++) {
            JSONObject player = participants.getJSONObject(i);
            if (player.getString("summonerId").equals(summonerId)) {
                return new PlayerRecord(player.getString("summonerName"), player.getInt("summonerLevel"), player.getString("championName"),
                        player.getInt("kills"), player.getInt("deaths"), player.getInt("assists"), player.getBoolean("win"));
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "wonTeam:" + "\n" + wonTeam + "\n" +
                "lostTeam:" + "\n" + lostTeam + "\n";
    }
}
