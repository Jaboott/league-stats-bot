package LeagueObjects;

import org.json.JSONArray;
import org.json.JSONObject;
import utility.AccessApi;
import utility.SummonerInfoGetter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LeaguePlayer extends SummonerInfoGetter{

    private int level;
    private String summonerName;
    private String rank = "unranked";
    private int leaguePoint;
    private int rankedWins;
    private int rankedLosses;
    private int normalWins;
    private int normalLosses;
    private List<String> topChampions;
    private String encryptedSummonerID;


    public LeaguePlayer(String summonerName) {
        if (playerExist(summonerName.replace(" ", ""))) {
            this.summonerName = summonerName;
            summonerName = summonerName.replaceAll(" ", "");
            encryptedSummonerID = getAccountId(summonerName);
            level = getLevel(summonerName);
            initPlayer((JSONArray) accessApi("https://na1.api.riotgames.com","/lol/league/v4/entries/by-summoner/", encryptedSummonerID));
        } else {
            System.out.println("Player doesn't exist");
        }

    }

    private void initPlayer(JSONArray playerData) {
        for(int i = 0; i < playerData.length(); i++) {
            JSONObject jsonObject = playerData.getJSONObject(i);
            topChampions = new ArrayList<>();
            getTopChampions();
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

    private void getTopChampions() {
        JSONArray topChampionList = getTopChampions(summonerName.replaceAll(" ", ""));

        StringBuilder sb = new StringBuilder();
        try (Scanner scanner = new Scanner(new FileReader("championJson.json"))){
            while (scanner.hasNext()) {
                sb.append(scanner.next());
            }
            JSONObject champions = new JSONObject(sb.toString());
            for (int i = 0; i < topChampionList.length(); i++) {
                int championId = topChampionList.getJSONObject(i).getInt("championId");
                topChampions.add(champions.getString(String.valueOf(championId)));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> getMatchHistory(int numMatchHistory, boolean detailedMatch) {
        ArrayList<String> tempMatchList = new ArrayList<>();
        JSONArray recentMatches = getMatches(summonerName.replaceAll(" ", ""), numMatchHistory);
        for (int i = 0; i < recentMatches.length(); i++) {
            tempMatchList.add("match:" + (i + 1) + "\n");
            String matchId = recentMatches.getString(i);
            LeagueMatch match = new LeagueMatch(matchId);
            if (detailedMatch) {
                tempMatchList.add(match.getMatch().toString() + "\n");
            } else {
                tempMatchList.add(match.getPlayerStat(encryptedSummonerID).toString() + "\n");
            }
        }
        return tempMatchList;
    }

    @Override
    public String toString() {
        try {
            StringBuilder topChampion = new StringBuilder();
            topChampions.forEach(s -> topChampion.append(s + ", "));

            return "summonerID: %s \n".formatted(encryptedSummonerID ) +
                    "summonerName: %s, summonerLevel: %d , top champions: %s\n".formatted(summonerName, level, topChampion) +
                    "rank: %s, lp: %d \n".formatted(rank, leaguePoint) +
                    "rankedWins: %d, rankedLosses: %d, ranked win rate for the past %d games: %d%%\n".formatted( rankedWins, rankedLosses, rankedWins + rankedLosses,
                            Math.round(((double) rankedWins / (double)(rankedLosses + rankedWins)) * 100)) +
                    "normalWins: %d, normalLosses: %d, normal win rate for the past %d games: %d%%\n".formatted(normalWins, normalLosses, normalLosses + normalWins,
                            Math.round(((double) normalWins / (double)(normalLosses + normalWins)) * 100));
        } catch (NullPointerException e) {
            System.out.println("Player is either deleted or no info was found");
            return null;
        }

    }
}
