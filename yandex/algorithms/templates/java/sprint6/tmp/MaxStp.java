import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

public class MaxStp {
    private static final List<String> color = new ArrayList<>();
    private static final Map<Integer, TreeSet<Pair>> newMatrix = new HashMap<>();

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int[] firstLine = readArray(reader);
            int n = firstLine[0];
            int m = firstLine[1];

            for (int i = 0; i < m; i++) {
                int[] row = readArray(reader);
                {
                    TreeSet<Pair> val;
                    if (newMatrix.containsKey(row[0])) {
                        val = newMatrix.get(row[0]);
                    } else {
                        val = new TreeSet<>();
                    }
                    val.add(new Pair(row[1], row[2]));
                    newMatrix.put(row[0], val);
                }
                {
                    TreeSet<Pair> val;
                    if (newMatrix.containsKey(row[1])) {
                        val = newMatrix.get(row[1]);
                    } else {
                        val = new TreeSet<>();
                    }
                    val.add(new Pair(row[0], row[2]));
                    newMatrix.put(row[1], val);
                }
            }

            initializeColor(n);

            StringBuilder sb = new StringBuilder();
            DFS(1, sb);
            System.out.println(sb);
        }
    }

    private static int[] readArray(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .mapToInt(Integer::intValue).toArray();
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static void initializeColor(int numVertices) {
        for (int i = 0; i < numVertices; i++) {
            color.add("white");
        }
    }

    private static void DFS(int v, StringBuilder sb) {
        color.set(v - 1, "gray"); // Вершина посещена, но ещё не обработана.
        sb.append(v).append(" ");
        TreeSet<Pair> outgoingEdges = outgoingEdges(v);

        if (outgoingEdges != null) {
            for (Pair w : outgoingEdges) {
                if (color.get(w.getKey() - 1).equals("white")) { // Если вершина не посещена, то
                    DFS(w.getKey(), sb); // запустим обход от найденной смежной вершины.
                }
            }
        }
        color.set(v - 1, "black"); // Теперь вершина обработана.
    }

    private static TreeSet<Pair> outgoingEdges(int v) {
        // Реализация получения исходящих рёбер для вершины v
        return newMatrix.get(v);
    }


    // TODO: make it comparable
    public static class Pair implements Comparable<Pair> {
        private final Integer key;
        private final Integer value;

        public Integer getKey() {
            return this.key;
        }

        public Integer getValue() {
            return this.value;
        }

        public Pair(Integer paramK, Integer paramV) {
            this.key = paramK;
            this.value = paramV;
        }

        public String toString() {
            return this.key + "=" + this.value;
        }

        public int hashCode() {
            return this.key.hashCode() * 13 + (this.value == null ? 0 : this.value.hashCode());
        }

        public boolean equals(Object paramObject) {
            if (this == paramObject) return true;
            if ((paramObject instanceof Pair)) {
                Pair localPair = (Pair) paramObject;
                if (!Objects.equals(this.key, localPair.key)) return false;
                return Objects.equals(this.value, localPair.value);
            }
            return false;
        }

        @Override
        public int compareTo(Pair o) {
            return this.getValue().compareTo(o.getValue());
        }
    }
}
