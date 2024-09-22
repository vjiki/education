import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class TimeToExit {

  private static final List<String> color = new ArrayList<>();
  private static final List<Integer> entry = new ArrayList<>();
  private static final List<Integer> leave = new ArrayList<>();
  private static final Map<Integer, TreeSet<Integer>> newMatrix = new HashMap<>();
  //List<String> color = new ArrayList<>(Arrays.asList("white", "white", ...));
  private static int time = 0;
  //List<Integer> entry = new ArrayList<>(Arrays.asList(null, null, ...));
  //List<Integer> leave = new ArrayList<>(Arrays.asList(null, null, ...));

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

      initializeColor(n);
      initializeTime(n);

      DFS(1);
      StringBuilder sb = new StringBuilder();
      for(int i = 1; i <= n; i++) {
        sb.append(entry.get(i-1)-1).append(" ").append(leave.get(i-1)-1).append("\n");
      }

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

  private static void initializeTime(int numVertices) {
    for (int i = 0; i < numVertices; i++) {
      entry.add(null);
      leave.add(null);
    }
  }


  private static void DFS(int v) {
    time += 1;
    entry.set(v - 1, time);
    color.set(v - 1, "gray"); // Вершина посещена, но ещё не обработана.
    TreeSet<Integer> outgoingEdges = outgoingEdges(v);

    if (outgoingEdges != null) {
      for (int w : outgoingEdges) {
        if (color.get(w - 1).equals("white")) { // Если вершина не посещена, то
          DFS(w); // запустим обход от найденной смежной вершины.
        }
      }
    }
    time += 1;
    leave.set(v - 1, time);
    color.set(v - 1, "black"); // Теперь вершина обработана.
  }

  private static TreeSet<Integer> outgoingEdges(int v) {
    // Реализация получения исходящих рёбер для вершины v
    return newMatrix.get(v);
  }
}
