// https://contest.yandex.ru/contest/25597/run-report/102499115/

/*
-- ПРИНЦИП РАБОТЫ --
Описание алгоритма - https://en.wikipedia.org/wiki/Levenshtein_distance
Использую только две строки предидущую и текущую

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Доказательство алгоритма - https://en.wikipedia.org/wiki/Levenshtein_distance

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
O(n*m), где n и m — длины строк.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
O(n), где n - длина наименьшей строки.
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
        int n = firstRow.length;
        int m = secondRow.length;
        int[] current = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            current[i] = i;
        }
        for (int i = 1; i <= m; i++) {
            int[] previous = current;
            current = new int[n + 1];
            current[0] = i;
            for (int j = 1; j <= n; j++) {
                int dist = Math.min(previous[j] + 1, current[j - 1] + 1);
                current[j] = Math.min(dist,
                        previous[j - 1] + (firstRow[j - 1] != secondRow[i - 1] ? 1 : 0)
                );
            }
        }
        System.out.println(current[n]);
    }
}
