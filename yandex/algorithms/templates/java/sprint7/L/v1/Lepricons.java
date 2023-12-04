import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Lepricons {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int[] firstLine = readArray(reader);
            int n = firstLine[0];
            int maxBackpackSize = firstLine[1];

            int[] golds = readArray(reader);
            System.out.println(getMaxFlowers(golds, n, maxBackpackSize));
        }
    }

    public static int getMaxFlowers(int[] golds, int n, int maxBackpackSize) {

        int[][] dp = new int[n+1][maxBackpackSize];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < maxBackpackSize; j++) {
                if (j - golds[i-1] <= 0) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], golds[i-1] + dp[i - 1][j - golds[i-1]]);
                }
            }
        }

        return dp[n-1][maxBackpackSize-1];
    }

    private static int[] readArray(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .mapToInt(Integer::intValue).toArray();
    }

    public static int getMaxWeight(int[] golds, int n, int maxBackpackSize)  {

        Heap previous = new Heap(0, maxBackpackSize + 1);
        Heap goldHeap = new Heap(0, maxBackpackSize + 1);

        for (int i = 0; i < n; i++) {
            int weight = golds[i];
            goldHeap = new Heap(weight, maxBackpackSize + 1);
            for (int j = 0; j < maxBackpackSize + 1; j++) {
                if (j - weight >= 0) {
                    goldHeap.cap[j] = Math.max(previous.cap[j], weight + previous.cap[j - weight]);
                } else {
                    goldHeap.cap[j] = previous.cap[j];
                }
            }
            previous = goldHeap;
        }
        return goldHeap.cap[maxBackpackSize];
    }
}

