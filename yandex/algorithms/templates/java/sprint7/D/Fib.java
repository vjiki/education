import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Fib {

    public static int codeSize(int n) {
        int[] dp = new int[n+1];
        if (n == 0 || n == 1) {
            return 1;
        }
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
                dp[i] = (dp[i-2] + dp[i-1]) % pow(9);
        }

        return dp[n];
    }

    private static int pow(int k) {
        int decimal = 1;
        for (int i = 0; i < k; i++) {
            decimal *= 10;
        }
        return decimal + 7;
    }


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String cmd = reader.readLine();
            String[] values = cmd.split(" ");
            int n = Integer.parseInt(values[0]);
            System.out.println(codeSize(n));
        }
    }
}