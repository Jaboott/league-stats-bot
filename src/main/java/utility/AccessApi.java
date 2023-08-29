package utility;

import java.io.IOException;

public class AccessApi {

    public Object accessApi(String baseUrl, String endPoint, String someId, String field) {
        try {
            Object playerData = UrlReader.readJsonFromUrl
                    (baseUrl + endPoint + someId + field + "api_key=" + KeyHandler.getKey("RiotApiKey"));
            return playerData;
        } catch (IOException e) {
            System.out.println("Cannot access api: " + (baseUrl + endPoint + someId  + "?api_key="));
            e.printStackTrace();
        }
        return null;
    }

    public Object accessApi(String baseUrl, String endPoint, String someId) {
        try {
            Object playerData = UrlReader.readJsonFromUrl
                    (baseUrl + endPoint + someId  + "?api_key=" + KeyHandler.getKey("RiotApiKey"));
            return playerData;
        } catch (IOException e) {
            System.out.println("Cannot access api: " + (baseUrl + endPoint + someId  + "?api_key="));
            e.printStackTrace();
        }
        return null;
    }

}
