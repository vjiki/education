import java.util.Scanner;

public class Bank {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line;

        int m;
        line = scanner.nextLine();
        m = Integer.parseInt(line);

        int n;
        line = scanner.nextLine();
        n = Integer.parseInt(line);

        int[] coins = new int[n + 1];
        line = scanner.nextLine();
        String[] values = line.split(" ");
        int value;
        for (int i = 0; i < n; i++) {
            value = Integer.parseInt(values[i]);
            coins[i + 1] = value;
        }

        long[][] dp = new long[n + 1][m + 1];

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
}

