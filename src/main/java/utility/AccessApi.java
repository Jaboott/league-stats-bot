package utility;

import java.io.IOException;

public class AccessApi {

    private final String baseUrl = "https://na1.api.riotgames.com";;
    private String endPoint;

    public Object accessApi(String encryptedSummonerID) {
        try {
            Object playerData = UrlReader.readJsonFromUrl
                    (baseUrl + endPoint + encryptedSummonerID + "?api_key=" + KeyHandler.getKey("RiotApiKey"));
            return playerData;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }
}
