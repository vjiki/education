import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] firstLine = readArray(reader);
            int n = firstLine[0];
            int m = firstLine[1];

            int[][] graph = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    graph[i][j] = Integer.MAX_VALUE;
                }
            }
            for (int it = 0; it < m; it++) {
                int[] row = readArray(reader);

                int r = row[0];
                int c = row[1];
                int w = row[2];
                if (graph[r - 1][c - 1] == Integer.MAX_VALUE) {
                    graph[r - 1][c - 1] = graph[c - 1][r - 1] = w;
                }
            }
            for (int i = 0; i < n; i++) {
                graph[i][i] = 0;
            }
            getShortMatrix(n, graph);
        }
    }

    static void getShortMatrix(int n, int[][] graph) {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (graph[i][k] != Integer.MAX_VALUE && graph[k][j] != Integer.MAX_VALUE) {
                        graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int[] row : graph) {
            for (int item : row) {
                sb.append(item != Integer.MAX_VALUE ? item : -1).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static int[] readArray(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .mapToInt(Integer::intValue).toArray();
    }
}

