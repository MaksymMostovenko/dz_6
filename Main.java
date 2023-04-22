import org.json.JSONArray;
import org.json.JSONObject;
import java.util.*;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        final int WORD_COUNT = 2;
        Set <Character> uniLetters = new HashSet<>();


        String[] wordsArr = readJson();

        int word = 0;
        int wCount = 0;
        while (word < wordsArr.length && wCount < WORD_COUNT){
            HashMap<Character, Integer> lettersMap = new HashMap<>();
            char[] cWord = wordsArr[word].toCharArray();
            for(char letter : cWord){
                lettersMap.compute(letter, (k, v) -> (v == null) ? 1 : v + 1);
            }

            int sum =0;
            for (int number : lettersMap.values()){
                sum += number;
            }

            if ( (lettersMap.size()%2 == 0) &&  (sum%2 == 0) ){
                uniLetters.addAll(lettersMap.keySet());
                wCount++;
            }

            lettersMap.clear();
            word++;
        }

        for (Character element : uniLetters){
            System.out.println(element);
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




