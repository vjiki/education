import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Lepricons {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int[] firstLine = readArray(reader);
            int n = firstLine[0];
            int maxBackpackSize = firstLine[1];

            int[] golds = readArray(reader);
            System.out.println(getMaxWeight(golds, n, maxBackpackSize));
        }
    }

    public static int getMaxWeight(int[] golds, int n, int maxBackpackSize)  {

        int[] previous = new int[maxBackpackSize + 1];
        int[] current = null;

        for (int i = 0; i < n; i++) {
            int weight = golds[i];
            current = new int[maxBackpackSize + 1];
            for (int j = 0; j < maxBackpackSize + 1; j++) {
                if (j - weight >= 0) {
                    current[j] = Math.max(previous[j], weight + previous[j - weight]);
                } else {
                    current[j] = previous[j];
                }
            }
            previous = current;
        }
        return current[maxBackpackSize];
    }

    private static int[] readArray(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .mapToInt(Integer::intValue).toArray();
    }

}

