import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class F {


    private static boolean isPalindrome(String text) {
        String strippedText = text.strip().toLowerCase().replaceAll("[^a-zA-Z0-9]", "");

        if(strippedText.isEmpty()) {
            return false;
        }
        if (strippedText.length() == 1) {
            return true;
        }
        if (strippedText.length() == 2 && strippedText.substring(0,1).equals(strippedText.substring(1,2))) {
            return true;
        }

        String part1 = strippedText.substring(0,strippedText.length()/2).strip().toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
        String part2 = strippedText.substring(strippedText.length()/2 + strippedText.length()%2).strip().toLowerCase();
        char[] chars = part2.toCharArray();
        char[] reversed = new char[chars.length];
        int j = 0;
        for (int i = part2.length() - 1; i >= 0; i--) {
            reversed[j] = chars[i];
            j++;
        }
        return part1.equals(String.valueOf(reversed));
    }

    public static void main(String[] args) throws IOException{
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String text = reader.readLine();
            if (isPalindrome(text)) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }
        }
    }
}
