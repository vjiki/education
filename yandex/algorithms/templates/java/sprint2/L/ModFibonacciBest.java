import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ModFibonacciBest {

  public static int codeSize(int n, int k) {
    if (n == 0 || n == 1) {
      return 1;
    }

    int n2 = 1;
    int n1 = 1;
    int fibanachi = 0;
    for (int i = 0; i < n - 1; i++) {
      fibanachi = (n1 + n2) % pow(k);
      n2 = n1;
      n1 = fibanachi;
    }
    return fibanachi;
  }

  private static int pow(int k) {
    int decimal = 1;
    for (int i = 0; i < k; i++) {
      decimal *= 10;
    }
    return decimal;
  }


  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      String cmd = reader.readLine();
      String[] values = cmd.split(" ");
      int n = Integer.parseInt(values[0]);
      int k = Integer.parseInt(values[1]);
      System.out.println(codeSize(n, k));
    }
  }
}
