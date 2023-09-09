import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ModFibonacci {
  public static int codeSize(int n) {
    if (n == 0 || n == 1) return 1;

    return codeSize(n-1) + codeSize(n-2);
  }

  public static int mod(int lines, int n) {
    return 0;
  }

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      String cmd = reader.readLine();
      String[] values = cmd.split(" ");
      int n = Integer.parseInt(values[0]);
      int k = Integer.parseInt(values[1]);
      int lines =  codeSize(n);
      System.out.println(mod(lines, k));
    }
  }
}
