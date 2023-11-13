import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Matrix {

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

      int[] firstLine = readArray(reader);
      int n = firstLine[0];
      int m = firstLine[1];
      Map<Integer, TreeSet<Integer>> newMatrix = new HashMap<>();
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
      StringBuilder sb = new StringBuilder();

      for(int i = 1; i <= n; i ++) {
        if (newMatrix.containsKey(i)) {
          TreeSet<Integer> val = newMatrix.get(i);
          sb.append(val.size());
          for (int v: val) {
            sb.append(" ").append(v);
          }
          sb.append("\n");
        } else {
          sb.append(0).append("\n");
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
}
