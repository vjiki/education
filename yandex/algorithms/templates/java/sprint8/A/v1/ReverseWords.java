import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReverseWords {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            String[] wordsList = reader.readLine().split(" ");

            StringBuilder sb = new StringBuilder();
            for (int i = wordsList.length-1; i >= 0; i--) {
                sb.append(wordsList[i]).append(" ");
            }
            System.out.println(sb);
        }
    }
}
