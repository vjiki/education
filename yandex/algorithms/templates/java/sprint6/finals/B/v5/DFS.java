import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeSet;
import java.util.stream.Collectors;

class DFS {
    private static final Map<Integer, TreeSet<Integer>> newMatrix = new HashMap<>();

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);

            for (int i = 0; i < n - 1; i++) {
                char[] row = reader.readLine().toCharArray();
                for (int j = 0; j < row.length; j++) {
                    if (row[j] == 'R') {
                        TreeSet<Integer> val;
                        if (newMatrix.containsKey(i)) {
                            val = newMatrix.get(i);
                        } else {
                            val = new TreeSet<>();
                        }
                        val.add(j + 1 + i);
                        newMatrix.put(i, val);
                    }
                    if (row[j] == 'B') {
                        TreeSet<Integer> val;
                        if (newMatrix.containsKey(i)) {
                            val = newMatrix.get(i);
                        } else {
                            val = new TreeSet<>();
                        }
                        val.add(j + 1 + i);
                        newMatrix.put(i, val);
                    }
                }
            }

            System.out.println(newMatrix);

            for (int i = 0; i < n; i++) {
                int[] color = new int[n];
                if (isGraphCycled(i, color)) {
                    System.out.println("NO");
                    return;
                }
            }

            System.out.println("YES");
        }
    }

    static boolean isGraphCycled(int startPoint, int[] color) {

        color[startPoint] = 1; // Вершина посещена, но ещё не обработана.
        Queue<Integer> planned = new LinkedList<>();
        planned.add(startPoint);

        while (!planned.isEmpty()) {
            int u = planned.poll(); // Возьмём вершину из очереди.
            TreeSet<Integer> outgoingEdges = outgoingEdges(u);
            for (int v : outgoingEdges.stream().filter(g -> g > startPoint).collect(Collectors.toList())) { // adjList - список смежности графа.
                if (color[v] == 0) { // Серые и чёрные вершины уже
                    // либо в очереди, либо обработаны.
                    color[v] = 1;
                    planned.add(v); // Запланируем посещение вершины.
                } else if (color[v] == 1) {
                    return true;
                }
            }
            color[u] = 2; // Теперь вершина считается обработанной.
        }


//        if (outgoingEdges != null) {
//            for (int w : outgoingEdges) { // отфильтровать вершины с большими номерами
//                if (color[w] == 0) { // Если вершина не посещена, то
//                    isGraphCycled(w, color); // запустим обход от найденной смежной вершины.
//                } else if (color[w] == 1) {
//                    return true;
//                }
//            }
//        }
//        color[startPoint] = 2;
        return false;
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

    private static TreeSet<Integer> outgoingEdges(int v) {
        // Реализация получения исходящих рёбер для вершины v
        return newMatrix.get(v);
    }
}