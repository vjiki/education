import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

class Main {
    public static void addVertex(int vertex,
                                 List<List<Pair<Integer,
                                 Integer>>> graph,
                                 boolean[] added,
                                 PriorityQueue<Pair<Integer, Integer>> edges) {
        added[vertex] = true;
        for (Pair<Integer, Integer> edge : graph.get(vertex)) {
            int edgeVertex = edge.getKey();
            int weight = edge.getValue();
            if (!added[edgeVertex]) {
                edges.add(new Pair<>(-weight, edgeVertex));
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        List<List<Pair<Integer, Integer>>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int f = scanner.nextInt();
            int t = scanner.nextInt();
            int w = scanner.nextInt();
            graph.get(f).add(new Pair<>(t, w));
            graph.get(t).add(new Pair<>(f, w));
        }
        boolean[] added = new boolean[n + 1];
//        PriorityQueue<Pair<Integer, Integer>> edges =
//                new PriorityQueue<>((a, b) -> a.getKey() - b.getKey());
        PriorityQueue<Pair<Integer, Integer>> edges =
                new PriorityQueue<>(Comparator.comparingInt(Pair::getKey));

        int maximumSpanningTree = 0;
        added[0] = true;
        addVertex(1, graph, added, edges);
        while (!allAdded(added) && !edges.isEmpty()) {
            Pair<Integer, Integer> edge = edges.poll();
            int weight = -edge.getKey();
            int vertex = edge.getValue();
            if (!added[vertex]) {
                maximumSpanningTree += weight;
                addVertex(vertex, graph, added, edges);
            }
        }
        System.out.println(allAdded(added) ? maximumSpanningTree : "Oops! I did it again");
    }

    public static boolean allAdded(boolean[] added) {
        for (boolean b : added) {
            if (!b) {
                return false;
            }
        }
        return true;
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