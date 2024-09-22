package v1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Horoscope {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int n = readInt(reader);

            int[] firstRow = readArray(reader);
            int m = readInt(reader);

            int[] secondRow = readArray(reader);
            getMaxCommonPartV2(firstRow, secondRow, n , m);
        }
    }

    private static void getMaxCommonPart(int[] firstRow,
                                         int[] secondRow,
                                         int n,
                                         int m) {
        int[][] dp = new int[n+1][m+1];
        boolean[] used = new boolean[m+1];
        StringBuilder sbFirstRow = new StringBuilder();
        StringBuilder sbSecondRow = new StringBuilder();
//        System.out.println(Arrays.toString(used));

        // n = len(A)
        // m = len(B)
        // F = [[0] * (m + 1) for i in range(n + 1)]
        // for i in range(1, n + 1):
        //    for j in range(1, m + 1):
        //        if A[i - 1] == B[j - 1]:
        //            F[i][j] = F[i - 1][j - 1] + 1
        //        else:
        //            F[i][j] = max(F[i - 1][j], F[i][j - 1])
        //print(F[n][m])

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (firstRow[i] == secondRow[j]) {
                    if (!used[j]) {
                        dp[i][j] = dp[i - 1][j] + 1;
                        if (dp[i][j] > dp[i][j-1]) {

                            used[j] = true;
                        }
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

//        System.out.println(Arrays.toString(used));

        for (int i = n; i > 0; i--) {
            for (int j = m; j > 0; j--) {
                if (dp[i][j] == dp[i-1][j]) {
                    continue;
                }
                if (dp[i][j] == dp[i][j-1]) {
                    continue;
                }
                if (firstRow[i] == secondRow[j] && used[j]) {
                    sbFirstRow.append(i).append(" ");
                    sbSecondRow.append(j).append(" ");
                }
            }
        }


        System.out.println(dp[n][m]);
        System.out.println(sbFirstRow);
        System.out.println(sbSecondRow);
    }

    private static void getMaxCommonPartV2(int[] firstRow,
                                         int[] secondRow,
                                         int n,
                                         int m) {
        int[][] dp = new int[n+1][m+1];
        boolean[] used = new boolean[m+1];
        StringBuilder sbFirstRow = new StringBuilder();
        StringBuilder sbSecondRow = new StringBuilder();
//        System.out.println(Arrays.toString(used));

        // n = len(A)
        // m = len(B)
        // F = [[0] * (m + 1) for i in range(n + 1)]
        // for i in range(1, n + 1):
        //    for j in range(1, m + 1):
        //        if A[i - 1] == B[j - 1]:
        //            F[i][j] = F[i - 1][j - 1] + 1
        //        else:
        //            F[i][j] = max(F[i - 1][j], F[i][j - 1])
        //print(F[n][m])

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (firstRow[i] == secondRow[j]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

//        System.out.println(Arrays.toString(used));

        // Ans = []
        // i = n
        // j = m
        // while i > 0 and j > 0:
        //    if A[i - 1] == B[j - 1]:
        //        Ans.append(A[i - 1])
        //        i -= 1
        //        j -= 1
        //    elif F[i - 1][j] == F[i][j]:
        //        i -= 1
        //    else:
        //        j -= 1
        //Ans = Ans[::-1]

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

//        for (int i = n; i > 0; i--) {
//            for (int j = m; j > 0; j--) {
//                if (dp[i][j] == dp[i-1][j]) {
//                    j--;
//                    continue;
//                }
//                if (dp[i][j] == dp[i][j-1]) {
//                    continue;
//                }
//                if (firstRow[i] == secondRow[j] && used[j]) {
//                    sbFirstRow.append(i).append(" ");
//                    sbSecondRow.append(j).append(" ");
//                }
//            }
//        }


        System.out.println(dp[n][m]);
        if (!sbFirstRow.isEmpty()) {
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
