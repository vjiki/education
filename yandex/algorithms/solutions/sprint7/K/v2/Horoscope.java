import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Horoscope {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int n = readInt(reader);

            int[] firstRow = readArray(reader);
            int m = readInt(reader);

            int[] secondRow = readArray(reader);
            getMaxCommonPart(firstRow, secondRow, n , m);
        }
    }

    private static void getMaxCommonPart(int[] firstRow,
                                         int[] secondRow,
                                         int n,
                                         int m) {
        int[][] dp = new int[n+1][m+1];
        StringBuilder sbFirstRow = new StringBuilder();
        StringBuilder sbSecondRow = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (firstRow[i] == secondRow[j]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        int i = n;
        int j = m;
        while (i > 0 && j > 0) {
            if (firstRow[i] == secondRow[j]) {
                sbFirstRow.append(i).append(" ");
                sbSecondRow.append(j).append(" ");
                i--;
                j--;
            } else if (dp[i][j] == dp[i-1][j]) {
                i--;
            } else {
                j--;
            }
        }

        System.out.println(dp[n][m]);
        if (sbFirstRow.length() != 0) {
            System.out.println(sbFirstRow.reverse().deleteCharAt(0));
            System.out.println(sbSecondRow.reverse().deleteCharAt(0));
        }
    }

    private static int[] readArray(BufferedReader reader) throws IOException {
        String[] rows = reader.readLine().split(" ");
        int[] array = new int[rows.length+1];
        for (int i = 1; i < rows.length+1; i++) {
            array[i] = Integer.parseInt(rows[i-1]);
        }
        return array;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
