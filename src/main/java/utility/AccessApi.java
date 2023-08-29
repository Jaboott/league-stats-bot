package utility;

import org.json.JSONArray;

import java.io.IOException;

public abstract class AccessApi {

    private String BASE_URL;
    private String END_POINT;

    public Object accessApi(String encryptedSummonerID) {
        try {
            Object playerData = UrlReader.readJsonFromUrl
                    (BASE_URL + END_POINT + encryptedSummonerID + "?api_key=" + KeyHandler.getKey("RiotApiKey"));
            return playerData;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setBASE_URL(String BASE_URL) {
        this.BASE_URL = BASE_URL;
    }

    public void setEND_POINT(String END_POINT) {
        this.END_POINT = END_POINT;
    }
}
