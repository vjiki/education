import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class TopologySort {

  private static int[] color = null;
  // 0 - white
  // 1 - gray
  // 2 - black
  private static MyStack order = null;
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
      order = new MyStack(n);

      for (int i = 1; i <= n; i++) {
        if (color[i-1] == 0) {
          DFS(i);
        }
      }

      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < n; i++) {
        sb.append(order.pop()).append(" ");
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
        if (color[w - 1] == 0) { // Если вершина не посещена, то
          DFS(w); // запустим обход от найденной смежной вершины.
        }
      }
    }
    order.push(v);
    color[v - 1] = 2; // Теперь вершина обработана.
  }

  private static TreeSet<Integer> outgoingEdges(int v) {
    // Реализация получения исходящих рёбер для вершины v
    return newMatrix.get(v);
  }


  static class MyStack {
    private final int[] stack;
    private int lastIndex = 0;

    public MyStack(int capacity) {
      stack = new int[capacity];
      lastIndex = 0;
    }

    public void push(int x) {
      stack[lastIndex] = x;
      lastIndex++;
    }

    public int pop() {
      int removed = stack[lastIndex - 1];
      lastIndex--;
      return removed;
    }
  }

}
