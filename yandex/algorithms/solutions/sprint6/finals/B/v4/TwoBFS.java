import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

class TwoBFS {

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);

//            List<List<Integer>> graphR = new ArrayList<>();
            Map<Integer, List<Integer>> graphR = new HashMap<>();
//            List<List<Integer>> graphB = new ArrayList<>();
            Map<Integer, List<Integer>> graphB = new HashMap<>();

//            for (int i = 0; i <= n; i++) {
//                graphR.put(i,new ArrayList<>());
//                graphB.put(new ArrayList<>());
//            }

            for (int i = 0; i < n-1; i++) {
                char[] row = reader.readLine().toCharArray();
                for (int j = 0; j < row.length; j++) {
                    if(row[j] == 'R') {
                        if (!graphR.containsKey(i)) {
                            List<Integer> val = new ArrayList<>();
                            val.add(j+1+i);
                            graphR.put(i, val);
                        } else {
                            List<Integer> val = graphR.get(i);
                            val.add(j + 1 + i);
                            graphR.put(i, val);
                        }
                    }
                    if(row[j] == 'B') {
                        if (!graphB.containsKey(i)) {
                            List<Integer> val = new ArrayList<>();
                            val.add(j+1+i);
                            graphB.put(i, val);
                        } else {
                            List<Integer> val = graphB.get(i);
                            val.add(j + 1 + i);
                            graphB.put(i, val);
                        }
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

    static boolean isPathExist(int startPoint, int endPoint, int n, Map<Integer, List<Integer>> graph) {
        int[] color = new int[n];

        Queue<Integer> planned = new LinkedList<>();
        planned.add(startPoint);
        color[startPoint] = 1;
        while (!planned.isEmpty()) {
            int u = planned.poll(); // Возьмём вершину из очереди.
            if (graph.containsKey(u)) {
                for (int v : graph.get(u).stream().filter(g -> g > startPoint).collect(Collectors.toList())) { // adjList - список смежности графа.
                    if (color[v] == 0) { // Серые и чёрные вершины уже
                        if (endPoint == v) {
                            return true;
                        }
                        // либо в очереди, либо обработаны.
                        color[v] = 1;
                        planned.add(v); // Запланируем посещение вершины.
                    }
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