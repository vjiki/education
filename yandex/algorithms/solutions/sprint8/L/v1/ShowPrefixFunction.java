import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShowPrefixFunction {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String row = reader.readLine();
            prefixFunction(row);
        }
    }

    public static void prefixFunction(String s) {
        StringBuilder sb = new StringBuilder();
        int N = s.length();
        int[] prefix = new int[N];
        sb.append(0).append(" ");
        for (int i = 1; i < N; i++) {
            int k = prefix[i-1];
            while (k > 0 && s.charAt(k) != s.charAt(i)) {
                k = prefix[k-1];
            }
            if (s.charAt(k) == s.charAt(i)) {
                k++;
            }
            prefix[i] = k;
            sb.append(k).append(" ");
        }
        System.out.println(sb.deleteCharAt(sb.length()-1));
    }
}
