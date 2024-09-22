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

    // function LevenshteinDistance(char s[0..m-1], char t[0..n-1]):
    //    // create two work vectors of integer distances
    //    declare int v0[n + 1]
    //    declare int v1[n + 1]
    //
    //    // initialize v0 (the previous row of distances)
    //    // this row is A[0][i]: edit distance from an empty s to t;
    //    // that distance is the number of characters to append to  s to make t.
    //    for i from 0 to n:
    //        v0[i] = i
    //
    //    for i from 0 to m - 1:
    //        // calculate v1 (current row distances) from the previous row v0
    //
    //        // first element of v1 is A[i + 1][0]
    //        //   edit distance is delete (i + 1) chars from s to match empty t
    //        v1[0] = i + 1
    //
    //        // use formula to fill in the rest of the row
    //        for j from 0 to n - 1:
    //            // calculating costs for A[i + 1][j + 1]
    //            deletionCost := v0[j + 1] + 1
    //            insertionCost := v1[j] + 1
    //            if s[i] = t[j]:
    //                substitutionCost := v0[j]
    //            else:
    //                substitutionCost := v0[j] + 1
    //
    //            v1[j + 1] := minimum(deletionCost, insertionCost, substitutionCost)
    //
    //        // copy v1 (current row) to v0 (previous row) for next iteration
    //        // since data in v1 is always invalidated, a swap without copy could be more efficient
    //        swap v0 with v1
    //    // after the last swap, the results of v1 are now in v0
    //    return v0[n]


    //    for i from 0 to m - 1:
    //        // calculate v1 (current row distances) from the previous row v0

    //        // first element of v1 is A[i + 1][0]
    //        //   edit distance is delete (i + 1) chars from s to match empty t
    //        v1[0] = i + 1

    //        // use formula to fill in the rest of the row
    //        for j from 0 to n - 1:
    //            // calculating costs for A[i + 1][j + 1]
    //            deletionCost := v0[j + 1] + 1
    //            insertionCost := v1[j] + 1
    //            if s[i] = t[j]:
    //                substitutionCost := v0[j]
    //            else:
    //                substitutionCost := v0[j] + 1

    public static void getMaxCommonPart(char[] firstRow, char[] secondRow) {
        int m = firstRow.length;
        int n = secondRow.length;
        int[] v0 = new int[n+1];

        for (int i = 0; i <= n; i++) {
           v0[i] = i;
        }


        for (int i = 1; i <= m; i++) {
            int[] v1 = v0;
            v0 = new int[n + 1];
            v0[0] = i;
            for (int j = 1; j <= n; j++) {
                int deletionCost = v0[j - 1] + 1;
                int insertionCost = v1[j] + 1;
                int minDelInsCost = Math.min(deletionCost, insertionCost);
                int substitutionCost;
                if (firstRow[i-1] == secondRow[j-1]) {
                    substitutionCost = v1[j-1];
                } else {
                    substitutionCost = v1[j-1] + 1;
                }
                v1[j] = Math.min(minDelInsCost, substitutionCost);
            }
        }

        System.out.println(v0[n]);
    }
}
