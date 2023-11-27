// https://contest.yandex.ru/contest/25070/run-report/98067424/

/*
-- ПРИНЦИП РАБОТЫ --
Я реализовал поиск максимального веса остовного дерева в неориентированном графе:
 - начинаем обходить граф с первой вершины
 - обходим граф пока все элементы не добавлены или пока в priority queue еще есть элементы.
 - проверяем, что вершины этого ребра нет во множестве вершин, добавленных в остов, к значению
    максимального остовного дерева maximum_spanning_tree прибавляем вес максимального ребра и добавляем в остов.
 - Я использую PriorityQueue для добавления рёбер в остов. Я добавляю в PriorityQueue ребро меняя вес на
   отридцательный чтобы на верху лежали максимальные значения.
 - Если все вершины в массиве added то тогда вернем вес максимального остовного дерева, если нет то значит у нас
   несколько компонент связности и вернем ошибку

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Мы всегда выбираем максимальное из возможных ребер так что в итоге мы получим максимальное остовное дерево.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
O(E*logV), где E - количество рёбер в графе, а V - количество вершин.
Я использую приоритетную очередь. Для поиска максимального элемента мы затрачиваем О(logV) времени.
Таким образом если вместе с ребром в подграф добавляется новая вершина, то это ребро добавляется в остов.
Если ребро соединяет две вершины, уже присутствующее в подмножестве остова, мы отбрасываем его из дальнейшего
рассмотрения и из кучи в том числе.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Хранение очереди - O(E), где E - колчиство ребер. В очереди храняться Pair вес W + вершина V.
Список смежности - O(E*V), где E - количество ребер, V - количество вершин.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class MaxStp {
    public static void addToStp(int vertex,
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

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] firstLine = readArray(reader);
            int n = firstLine[0];
            int m = firstLine[1];

            // TODO: rewrite to avoid nested lists
            List<List<Pair<Integer, Integer>>> graph = new ArrayList<>();

            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                int[] row = readArray(reader);
                int u = row[0];
                int v = row[1];
                int w = row[2];
                graph.get(u).add(new Pair<>(v, w));
                graph.get(v).add(new Pair<>(u, w));
            }
            boolean[] added = new boolean[n + 1];
            PriorityQueue<Pair<Integer, Integer>> edges =
                    new PriorityQueue<>(Comparator.comparingInt(Pair::getKey));

            int maximumSpanningTree = 0;
            added[0] = true;
            addToStp(1, graph, added, edges);
            while (!allAdded(added) && !edges.isEmpty()) {
                Pair<Integer, Integer> edge = edges.poll();
                int weight = -edge.getKey();
                int vertex = edge.getValue();
                if (!added[vertex]) {
                    maximumSpanningTree += weight;
                    addToStp(vertex, graph, added, edges);
                }
            }
            System.out.println(allAdded(added) ? maximumSpanningTree : "Oops! I did it again");
        }
    }

    private static int[] readArray(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .mapToInt(Integer::intValue).toArray();
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