package utility;

import org.json.JSONObject;
import org.json.JSONWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ChampionsJsonBuilder extends UrlReader{

    public static void championJsonToSimplifiedJson() {
        try (FileWriter jsonFile = new FileWriter("championJson.json")){
            JSONObject jsonObject = ((JSONObject) readJsonFromUrl("https://ddragon.leagueoflegends.com/cdn/13.16.1/data/en_US/champion.json")).getJSONObject("data");
            JSONObject newJson = new JSONObject();
            Set<String> championKeySet = jsonObject.keySet();
            championKeySet.forEach(k -> newJson.put(String.valueOf(jsonObject.getJSONObject(k).getInt("key")), k));
            System.out.println(newJson);
            jsonFile.write(newJson.toString());
            jsonFile.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
