import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class BFSPrint {

  static void BFS(int s, StringBuilder sb, List<TreeSet<Integer>> graph) {
    // Создадим очередь вершин и положим туда стартовую вершину.
    Queue<Integer> planned = new LinkedList<>();
    Set<Integer> visited = new HashSet<>();
    planned.add(s);
    visited.add(s);
    while (!planned.isEmpty()) {
      int u = planned.poll(); // Возьмём вершину из очереди.
      sb.append(u).append(" ");
        for (int v : graph.get(u)) { // adjList - список смежности графа.
          if (!visited.contains(v)) {
            visited.add(v);
            planned.add(v); // Запланируем посещение вершины.
          }
      }
    }
  }

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

      int[] firstLine = readArray(reader);
      int n = firstLine[0];
      int m = firstLine[1];

      List<TreeSet<Integer>> graph = new ArrayList<>();
      for (int i = 0; i <= n; i++) {
        graph.add(new TreeSet<>());
      }
      for (int i = 0; i < m; i++) {
        int[] row = readArray(reader);
        int r = row[0];
        int c = row[1];
        graph.get(r).add(c);
        graph.get(c).add(r);
      }
      int s = readInt(reader);

      StringBuilder sb = new StringBuilder();
      BFS(s, sb, graph);
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
}
