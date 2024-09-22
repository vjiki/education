import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bank {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int expectedSum = readInt(reader);
            int numCoins = readInt(reader);
            int[] coins = readArray(reader);
            getCoins(coins, expectedSum, numCoins);
        }
    }

     static void getCoins(int[] coins, int m, int n) {
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (j - coins[i] >= 0) {
                    dp[i][j] = dp[i][j - coins[i]] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        System.out.print(dp[n][m]);
    }


    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static int[] readArray(BufferedReader reader) throws IOException {
        String[] rows = reader.readLine().split(" ");
        int[] array = new int[rows.length+1];
        for (int i = 1; i < rows.length+1; i++) {
            array[i] = Integer.parseInt(rows[i-1]);
        }
        return array;
    }

}

