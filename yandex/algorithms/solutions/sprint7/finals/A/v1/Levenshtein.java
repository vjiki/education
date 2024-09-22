import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Levenshtein {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            char[] firstRow = reader.readLine().toCharArray();

            char[] secondRow = reader.readLine().toCharArray();
            getMaxCommonPart(firstRow, secondRow);
        }
    }

    private static void getMaxCommonPart(char[] firstRow,
                                         char[] secondRow) {
        int[][] dp = new int[firstRow.length+1][secondRow.length+1];

        for (int i = 1; i <= firstRow.length; i++) {
            for (int j = 1; j <= secondRow.length; j++) {
                if (firstRow[i-1] == secondRow[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        System.out.println(Math.max(firstRow.length, secondRow.length) - dp[firstRow.length][secondRow.length]);
    }
}
