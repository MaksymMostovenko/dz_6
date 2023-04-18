import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        String[] words = readJson();
        words = shuffleArray(words);
        System.out.println(findUniqueCharsOfTwoWords(words).toString());
    }

    /* Я знаю, що це "велосипед", але чомусь моя Java не має методу toCharArray() */
    private static char[] homemadeToCharArray(String w) {
        char[] charArray = new char[w.length()];
        for (int i = 0; i < w.length(); i++) {
            charArray[i] = w.charAt(i);
        }
        return charArray;
    }

    public static Set<Character> findUniqueCharsOfTwoWords(String[] words) {
        int iWord = 0; //word iterator
        int wCounter = 0; // should count to 2. I use it to get only 2 words with common letters
        Set<Character> uniqueChars = new HashSet<>();

        while (iWord < words.length && wCounter < 2) {
            char[] cWords = homemadeToCharArray(words[iWord]);

            charLoop:
            for (int i = 0; i < cWords.length; i++) {
                for (int j = i + 1; j < cWords.length; j++) {
                    if (cWords[i] == cWords[j]) {
                        uniqueChars.addAll(getUniqueChars(words[iWord], words[(iWord + 1) % words.length]));
                        wCounter++;
                        break charLoop;
                    }
                }
            }
            iWord++;
        }
        return uniqueChars;
    }

    private static Set<Character> getUniqueChars(String word1, String word2) {
        Set<Character> uniqueChars = new HashSet<>();
        for (char c : word1.toCharArray()) {
            if (word2.indexOf(c) != -1) {
                uniqueChars.add(c);
            }
        }
        return uniqueChars;
    }


    @SuppressFBWarnings("DMI_RANDOM_USED_ONLY_ONCE")
    private static String[] shuffleArray(String[] arr) {
        Random rand = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            String temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return arr;
    }

    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings("DM_DEFAULT_ENCODING")
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




