import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        int word = 0;

        String[] wordsArr = readJson();
        while (word < wordsArr.length){
            char[] cWord = wordsArr[word].toCharArray();
            
            word++;
        }
    }

    private static String[] readJson() {
        String[] emptyArray = new String[0];
        try {
            // Read JSON file as a String
            String jsonData = new String(Files.readAllBytes(Paths.get("words.json")));

            // Parse the JSON data into a JSONObject
            JSONObject json = new JSONObject(jsonData);
            JSONArray wordsArr = json.getJSONArray("words");

            // Access the data in the JSON object
            String[] words = new String[wordsArr.length()];

            for (int i = 0; i < wordsArr.length(); i++) {
                words[i] = wordsArr.getString(i);
            }

            return words;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return emptyArray;
        }
    }
}




