// https://contest.yandex.ru/contest/25597/run-report/102285006/

/*
-- ПРИНЦИП РАБОТЫ --
Описание алгоритма - https://en.wikipedia.org/wiki/Levenshtein_distance
Я использую вариант с полной матрицей, который использует динамику.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Доказательство алгоритма - https://en.wikipedia.org/wiki/Levenshtein_distance

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
O(n*m), где n и m — длины строк.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
O(n*m) для хранения матрицы, где n,m - длины строк.
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Levenshtein {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            char[] firstRow = reader.readLine().toCharArray();
            char[] secondRow = reader.readLine().toCharArray();

            if (firstRow.length == 0 && secondRow.length ==0) {
                System.out.println(0);
            } else if (firstRow.length == 0) {
                System.out.println(secondRow.length);
            } else if (secondRow.length == 0) {
                System.out.println(firstRow.length);
            } else {
                if (firstRow.length < secondRow.length) {
                    getMaxCommonPart(firstRow, secondRow);
                } else {
                    getMaxCommonPart(secondRow, firstRow);
                }
            }
        }
    }

    public static void getMaxCommonPart(char[] firstRow, char[] secondRow) {
        int m = firstRow.length;
        int n = secondRow.length;
        int[][] dp = new int[m + 1][n+1];
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }
        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= m; i++) {
                int substitutionCost = firstRow[i-1] == secondRow[j-1] ? 0 : 1;
                int dist = Math.min(dp[i-1][j] + 1, dp[i][j - 1] + 1);
                dp[i][j] = Math.min(dist,
                        dp[i-1][j - 1] + substitutionCost);
            }
        }
        System.out.println(dp[m][n]);
    }
}
