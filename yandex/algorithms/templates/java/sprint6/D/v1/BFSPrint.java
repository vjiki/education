import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeSet;

public class BFSPrint {


  static void BFS(int s, StringBuilder sb, Color[] colors, Map<Integer, TreeSet<Integer>> newMatrix) {
    // Создадим очередь вершин и положим туда стартовую вершину.
    Queue<Integer> planned = new LinkedList<>();
    planned.add(s);
    colors[s] = Color.GRAY;
    while (!planned.isEmpty()) {
      int u = planned.poll(); // Возьмём вершину из очереди.
      sb.append(u).append(" ");
      TreeSet<Integer> outgoingEdges = outgoingEdges(u, newMatrix);
      if (outgoingEdges != null) {
        for (int v : outgoingEdges) { // adjList - список смежности графа.
          if (colors[v] == Color.WHITE) { // Серые и чёрные вершины уже
            // либо в очереди, либо обработаны.
            colors[v] = Color.GRAY;
            planned.add(v); // Запланируем посещение вершины.
          }
        }
      }
      colors[u] = Color.BLACK; // Теперь вершина считается обработанной.
    }
  }

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

      int[] firstLine = readArray(reader);
      int n = firstLine[0];
      int m = firstLine[1];

      Map<Integer, TreeSet<Integer>> newMatrix = new HashMap<>();

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
      int s = readInt(reader);

      Color[] colors = new Color[n+1];
      Arrays.fill(colors, Color.WHITE);

      StringBuilder sb = new StringBuilder();
      BFS(s, sb, colors, newMatrix);
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

  private static TreeSet<Integer> outgoingEdges(int v, Map<Integer, TreeSet<Integer>> newMatrix) {
    // Реализация получения исходящих рёбер для вершины v
    return newMatrix.get(v);
  }

  enum Color {
    WHITE,
    GRAY,
    BLACK;
  }
}
