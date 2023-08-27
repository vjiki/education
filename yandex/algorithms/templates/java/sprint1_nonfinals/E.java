import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class E {

    private static String getLongestWord(String text) {
        String[] splits = text.split(" ");
        if (splits.length == 0) {
            return "";
        }
        int longestIdx = 0;
        int longestLength = 0;
        for (int i = 0; i < splits.length; i++) {
            if (splits[i].length() > longestLength) {
                longestLength = splits[i].length();
                longestIdx = i;
            }
        }
        return splits[longestIdx];
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int textLength = readInt(reader);
            String text = reader.readLine();
            String longestWord = getLongestWord(text);
            System.out.println(longestWord);
            System.out.println(longestWord.length());
        }

    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

}
