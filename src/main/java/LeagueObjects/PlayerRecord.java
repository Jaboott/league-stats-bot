package LeagueObjects;

public record PlayerRecord(String summonerName, int level, String champion, int kills, int deaths, int assists, boolean wonGame) {

    @Override
    public String toString() {
        return "summonerName:" + summonerName +
                ", level:" + level +
                ", champion:" + champion +
                ", kills:" + kills +
                ", deaths:" + deaths +
                ", assists:" + assists +
                ", wonGame:" + wonGame +
                "\n";
    }
}
