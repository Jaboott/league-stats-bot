package utility;

import org.json.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class KeyHandler {

    public static String getKey(String key) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (Scanner scanner = new Scanner(new FileReader("key.json"))){
            while (scanner.hasNext()) {
                sb.append(scanner.next());
            }
            JSONObject jsonObject = new JSONObject(sb.toString());
            return (String) jsonObject.get(key);
        }
    }
}
