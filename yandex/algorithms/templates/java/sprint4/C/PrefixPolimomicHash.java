import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PrefixPolimomicHash {

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      int a = readInt(reader);
      int m = readInt(reader);

      String row = reader.readLine();
      long[] prefixes = getPrefixes(a, m, row.length());
      int t = readInt(reader);
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < t; i++) {
        int[] arr = readArray(reader);
        sb.append(getHash(m, row.substring(arr[0] - 1, arr[1]).toCharArray(), prefixes)).append("\n");
      }
      System.out.println(sb);
    }
  }

  private static int readInt(BufferedReader reader) throws IOException {
    return Integer.parseInt(reader.readLine());
  }

  private static int[] readArray(BufferedReader reader) throws IOException {
    return Arrays.stream(reader.readLine().split(" "))
        .map(Integer::parseInt)
        .mapToInt(Integer::intValue).toArray();
  }

  static long[] getPrefixes(int a, int mod, int length) {
    long[] result = new long[length+1];
    result[0] = 1;

    for (int i = 0; i < length; i++) {
      result[i + 1] = (result[i] * a) % mod;
    }

    return result;
  }

  static int getHash(int m, char[] row, long[] prefix) {
    int result = 0;
    int count = 0;
    for (int i = row.length - 1; i >= 0; i--) {
      result = (int) (result + (int) row[i] * prefix[count]) % m;
      count++;
    }
    return result;
  }
}
