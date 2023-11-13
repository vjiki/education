import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ToMatrix {

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

      int[] firstLine = readArray(reader);
      int n = firstLine[0];
      int m = firstLine[1];
      int[][] newMatrix = new int[n][n];
      for (int i = 0; i < m; i++) {
        int[] row = readArray(reader);
        newMatrix[row[0]-1][row[1]-1] = 1;
      }
      StringBuilder sb = new StringBuilder();

      for(int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          if (j==0) {
            sb.append(newMatrix[i][j]);
          } else {
            sb.append(" ").append(newMatrix[i][j]);
          }
        }
        sb.append("\n");
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
