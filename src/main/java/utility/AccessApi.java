package utility;

import java.io.IOException;

public class AccessApi {

    private String baseUrl;
    private String endPoint;

    public AccessApi() {
        baseUrl = "https://na1.api.riotgames.com";
    }

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

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }
}
