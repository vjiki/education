import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Fibonachy {

  public static int codeSize(int n) {
    if (n == 0 || n == 1) return 1;

    return codeSize(n-1) + codeSize(n-2);
  }

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      int n = readInt(reader);
      System.out.println(codeSize(n));
    }
  }

  private static int readInt(BufferedReader reader) throws IOException {
    return Integer.parseInt(reader.readLine());
  }
}
