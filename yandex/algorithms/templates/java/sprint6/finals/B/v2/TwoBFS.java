import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

class TwoBFS {

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);

            List<List<Pair<Integer, Boolean>>> graph = new ArrayList<>();

            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < n-1; i++) {
                char[] row = reader.readLine().toCharArray();
                for (int j = 0; j < row.length; j++) {
                    if(row[j] == 'R') {
                        graph.get(i).add(new Pair<>(j+1+i, true));
                    }
                    if(row[j] == 'B') {
                        graph.get(i).add(new Pair<>(j+1+i, false));
                    }
                }
            }

//            System.out.println(graph);
//            System.out.println(graphB);

            for (int i = 0; i < n; i++) {
                for (int j = i+1; j < n; j++) {
                    if (isTwoPathExists(i, j, n, graph)) {
                        System.out.println("NO");
                        return;
                    }
                }
            }

            System.out.println("YES");
        }
    }

    static boolean isTwoPathExists(int startPoint, int endPoint, int n, List<List<Pair<Integer, Boolean>>> graph) {
        int[] colorR = new int[n];
        int[] colorB = new int[n];
        Queue<Integer> plannedR = new LinkedList<>();
        Queue<Integer> plannedB = new LinkedList<>();
        plannedR.add(startPoint);
        plannedB.add(startPoint);
        colorR[startPoint] = 1;
        colorB[startPoint] = 1;
        boolean isRPathExists = false;
        boolean isBPathExists = false;
        while (!plannedR.isEmpty()) {
            int u = plannedR.poll(); // Возьмём вершину из очереди
            for (Pair<Integer, Boolean> v : graph.get(u).stream().filter(e -> e.getValue()).collect(Collectors.toList())) { // adjList - список смежности графа.
                if (v.getValue()) {
                    if (colorR[v.getKey()] == 0 && v.getKey() > startPoint) { // Серые и чёрные вершины уже
                        if (endPoint == v.getKey()) {
                            isRPathExists = true;
                        }
                        // либо в очереди, либо обработаны.
                        colorR[v.getKey()] = 1;
                        plannedR.add(v.getKey()); // Запланируем посещение вершины.
                    }
                }
            }
            colorR[u] = 2; // Теперь вершина считается обработанной.
        }
        while (!plannedB.isEmpty()) {
            int u = plannedB.poll(); // Возьмём вершину из очереди
            for (Pair<Integer, Boolean> v : graph.get(u).stream().filter(e -> !e.getValue()).collect(Collectors.toList())) { // adjList - список смежности графа.
                    if (colorB[v.getKey()] == 0 && v.getKey() > startPoint) { // Серые и чёрные вершины уже
                        if (endPoint == v.getKey()) {
                            isBPathExists = true;
                        }
                        // либо в очереди, либо обработаны.
                        colorB[v.getKey()] = 1;
                        plannedB.add(v.getKey()); // Запланируем посещение вершины.
                    }
            }
            colorB[u] = 2; // Теперь вершина считается обработанной.
        }
        return isBPathExists && isRPathExists;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    static class Pair<K, V> {
        private final K key;
        private final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}