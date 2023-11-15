import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class TopologySort {

  private static int[] color = null;
  private static final Map<Integer, TreeSet<Integer>> newMatrix = new HashMap<>();

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

      int[] firstLine = readArray(reader);
      int n = firstLine[0];
      int m = firstLine[1];

      for (int i = 0; i < m; i++) {
        int[] row = readArray(reader);
        TreeSet<Integer> val;
        if (newMatrix.containsKey(row[0])) {
          val = newMatrix.get(row[0]);
        } else {
          val = new TreeSet<>();
        }
        val.add(row[1]);
        newMatrix.put(row[0], val);
      }

      color = new int[n];

      StringBuilder sb = new StringBuilder();
      for (int i = 1; i <= n; i++) {
        if (color[i-1] == 0) {
          DFS(i, sb);
        }
      }

      System.out.println(sb.reverse().deleteCharAt(0));
    }
  }

  private static int[] readArray(BufferedReader reader) throws IOException {
    return Arrays.stream(reader.readLine().split(" "))
        .map(Integer::parseInt)
        .mapToInt(Integer::intValue).toArray();
  }

  private static void DFS(int v, StringBuilder sb) {
    color[v - 1] = 1; // Вершина посещена, но ещё не обработана.
    TreeSet<Integer> outgoingEdges = outgoingEdges(v);

    if (outgoingEdges != null) {
      for (int w : outgoingEdges) {
        if (color[w - 1] == 0) { // Если вершина не посещена, то
          DFS(w, sb); // запустим обход от найденной смежной вершины.
        }
      }
    }

    sb.append(v).append(" ");
    color[v - 1] = 2; // Теперь вершина обработана.
  }

  private static TreeSet<Integer> outgoingEdges(int v) {
    // Реализация получения исходящих рёбер для вершины v
    return newMatrix.get(v);
  }

}
