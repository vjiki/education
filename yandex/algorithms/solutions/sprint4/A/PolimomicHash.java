import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PolimomicHash {

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      int a = readInt(reader);
      int m = readInt(reader);

      char[] row = reader.readLine().toCharArray();
      System.out.println(getHash(a, m, row));
    }
  }

  private static int readInt(BufferedReader reader) throws IOException {
    return Integer.parseInt(reader.readLine());
  }

  static long getHash(int a, int mod, char[] row) {
    if (row.length == 0) {
      return 0;
    }

    long hash = 0;
    for (int i = 0; i < row.length; i++) {
      hash = (hash*a + row[i]) % mod;
    }

    return hash;
  }
}
