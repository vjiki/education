import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TwoBFS {

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);

            List<List<Integer>> graphR = new ArrayList<>();
            List<List<Integer>> graphB = new ArrayList<>();

            for (int i = 0; i <= n; i++) {
                graphR.add(new ArrayList<>());
                graphB.add(new ArrayList<>());
            }

            for (int i = 0; i < n-1; i++) {
                char[] row = reader.readLine().toCharArray();
                for (int j = 0; j < row.length; j++) {
                    if(row[j] == 'R') {
                       graphR.get(i).add(j+1+i);
                    }
                    if(row[j] == 'B') {
                        graphB.get(i).add(j+1+i);
                    }
                }
            }

//            System.out.println(graphR);
//            System.out.println(graphB);

            for (int i = 0; i < n; i++) {
                for (int j = i+1; j < n; j++) {
                    if (isPathExist(i, j, n, graphR) && isPathExist(i, j, n, graphB)) {
                        System.out.println("NO");
                        return;
                    }
                }
            }

            System.out.println("YES");
        }
    }

    static boolean isPathExist(int startPoint, int endPoint, int n, List<List<Integer>> graph) {
        int[] color = new int[n];

        Queue<Integer> planned = new LinkedList<>();
        planned.add(startPoint);
        color[startPoint] = 1;
        while (!planned.isEmpty()) {
            int u = planned.poll(); // Возьмём вершину из очереди.
            for (int v : graph.get(u)) { // adjList - список смежности графа.
                if (color[v] == 0 && v > startPoint) { // Серые и чёрные вершины уже
                    if (endPoint == v) {
                        return true;
                    }
                    // либо в очереди, либо обработаны.
                    color[v] = 1;
                    planned.add(v); // Запланируем посещение вершины.
                }
            }
            color[u] = 2; // Теперь вершина считается обработанной.
        }
        return false;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}