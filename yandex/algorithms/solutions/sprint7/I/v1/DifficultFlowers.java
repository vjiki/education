import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class DifficultFlowers {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int[] firstLine = readArray(reader);
            int n = firstLine[0];
            int m = firstLine[1];
            int[][] matrix = new int[n + 1][m + 1];

            for (int i = 1; i <= n; i++) {
                String row = reader.readLine();
                for (int g = 0; g < m; g++) {
                    if (row.charAt(g) == '1') {
                        matrix[i][g + 1] = 1;
                    }
                }
            }
            getMaxFlowers(matrix, n, m);
        }
    }

    public static void getMaxFlowers(int[][] matrix, int n, int m) {

        int[][] dp = new int[n + 2][m + 1];

        for (int i = n; i > 0; i--) {
            for (int g = 1; g <= m; g++) {
                dp[i][g] = Math.max(dp[i + 1][g], dp[i][g - 1]) + matrix[i][g];
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = 1;
        int j = m;
        while (i <= n && j > 0) {
                if (dp[i + 1][j] > dp[i][j - 1]) {
                    sb.append("U");
                    i++;
                } else {
                    sb.append("R");
                    j--;
                }
        }

        System.out.println(dp[1][m]);
        if (sb.length() > 0 ) {
        System.out.println(sb.reverse());
        }
    }

    private static int[] readArray(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .mapToInt(Integer::intValue).toArray();
    }
}

