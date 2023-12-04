package v1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Flowers {

    static int getMaxFlowers(int[][] flowersField, int n, int m) {
        int currentX = 0;
        int currentY = 0;
        int maxPoints = flowersField[0][0];

        // можно попробовать реализовать с двумя циклами
        // выбирать вверх или вправо идти на основе того где больше получим
        // два цикла и вроде хороший вариант

        while (currentX < n && currentY < m) {

            int currentMaxX = 0;
            for (int i = currentX; i < n; i++) {
                if (flowersField[i][currentY] == 1) {
                    currentMaxX++;
                }
            }

            int currentMaxY = 0;
            for (int j = currentY; j < m; j++) {
                if (flowersField[currentX][j] == 1) {
                    currentMaxY++;
                }
            }

            if (currentMaxX >= currentMaxY) {
                currentX++;
            } else {
                currentY++;
            }
            if (currentX < n && currentY < m) {
                if (flowersField[currentX][currentY] == 1) {
                    maxPoints++;
                }
            }
        }


//        while (currentX < n && currentY < m) {
//            if (currentX+1 < n && flowersField[currentX+1][currentY] == 1) {
//                maxPoints++;
//                currentX++;
//                continue;
//            }
//            if (currentY +1 < m && flowersField[currentX][currentY+1] == 1) {
//                maxPoints++;
//                currentY++;
//                continue;
//            }
//            if (currentX +1 < n) {
//                currentX++;
//                continue;
//            }
//            if (currentY+1 < m) {
//                currentY++;
//                continue;
//            }
//            currentX++;
//        }

        return maxPoints;
    }

    static int getMaxFlowers2(int[][] flowersField, int n, int m) {
        int currentX = 0;
        int currentY = 0;
        int maxPoints = flowersField[0][0];

        // можно попробовать реализовать с двумя циклами
        // выбирать вверх или вправо идти на основе того где больше получим
        // два цикла и вроде хороший вариант

        while (currentX < n && currentY < m) {

            int currentMaxX = 0;
            for (int i = currentX; i < n; i++) {
                if (flowersField[i][currentY] == 1) {
                    currentMaxX++;
                }
            }

            int currentMaxY = 0;
            for (int j = currentY; j < m; j++) {
                if (flowersField[currentX][j] == 1) {
                    currentMaxY++;
                }
            }

            if (currentMaxX > currentMaxY) {
                currentX++;
            } else {
                currentY++;
            }
            if (currentX < n && currentY < m) {
                if (flowersField[currentX][currentY] == 1) {
                    maxPoints++;
                }
            }
        }

        return maxPoints;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int[] firstLine = readArray(reader);
            int n = firstLine[0];
            int m = firstLine[1];
            int[][] flowersField = new int[n][m];
            for (int i = n-1; i >= 0; i--) {
                String row = reader.readLine();
                for (int j = 0; j < m; j++) {
                    if (row.charAt(j) == '1') {
                        flowersField[i][j] = 1;
                    }
                }
            }
//            StringBuilder sb = new StringBuilder();
//
//            for(int i = 0; i < n; i++) {
//                for (int j = 0; j < m; j++) {
//                    if (j==0) {
//                        sb.append(flowersField[i][j]);
//                    } else {
//                        sb.append(" ").append(flowersField[i][j]);
//                    }
//                }
//                sb.append("\n");
//            }
//
//            System.out.println(sb);

            System.out.println(Math.max(getMaxFlowers(flowersField, n, m), getMaxFlowers2(flowersField, n, m)));
        }
    }

    private static int[] readArray(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .mapToInt(Integer::intValue).toArray();
    }
}
