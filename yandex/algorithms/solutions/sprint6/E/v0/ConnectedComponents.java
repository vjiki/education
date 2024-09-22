import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class ConnectedComponents {

  private static int[] color = null;
  private static int component_count = 1;
  private static final Map<Integer, TreeSet<Integer>> newMatrix = new HashMap<>();

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      int[] firstLine = readArray(reader);
      int n = firstLine[0];
      int m = firstLine[1];

      for (int i = 0; i < m; i++) {
        int[] row = readArray(reader);
        {
          TreeSet<Integer> val;
          if (newMatrix.containsKey(row[0])) {
            val = newMatrix.get(row[0]);
          } else {
            val = new TreeSet<>();
          }
          val.add(row[1]);
          newMatrix.put(row[0], val);
        }
        {
          TreeSet<Integer> val;
          if (newMatrix.containsKey(row[1])) {
            val = newMatrix.get(row[1]);
          } else {
            val = new TreeSet<>();
          }
          val.add(row[0]);
          newMatrix.put(row[1], val);
        }
      }

      color = new int[n];
      for (int i = 0; i < n; i++) {
        color[i] = -1;
      }

      for (int i = 1; i <= n; i++) {
        if (color[i-1] == -1) {
          DFS(i);
          component_count++;
        }
      }

      System.out.println(component_count-1);
      StringBuilder sb = new StringBuilder();
      for (int i = 1; i < component_count; i++) {
        for (int j = 0; j < n; j++) {
          if (color[j] == i) {
            sb.append(j+1).append(" ");
          }
        }
        if (i < component_count-1) {
          sb.append("\n");
        }
      }
      System.out.println(sb);
    }
  }

  private static int[] readArray(BufferedReader reader) throws IOException {
    return Arrays.stream(reader.readLine().split(" "))
        .map(Integer::parseInt)
        .mapToInt(Integer::intValue).toArray();
  }

  private static void DFS(int v) {
    color[v - 1] = 1; // Вершина посещена, но ещё не обработана.
    TreeSet<Integer> outgoingEdges = outgoingEdges(v);

    if (outgoingEdges != null) {
      for (int w : outgoingEdges) {
        if (color[w - 1] == -1) { // Если вершина не посещена, то
          DFS(w); // запустим обход от найденной смежной вершины.
        }
      }
    }
    color[v - 1] = component_count; // Теперь вершина обработана.
  }

  private static TreeSet<Integer> outgoingEdges(int v) {
    // Реализация получения исходящих рёбер для вершины v
    return newMatrix.get(v);
  }

}
