import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ModFibonacciV0 {

  final static int DECIMAL_POW_6 = 1_000_000;
  final static int DECIMAL_POW_8 = 100_000_000;

  static int[] cache = new int[DECIMAL_POW_6];

  public ModFibonacciV0() {
    cache[0] = 1;
    cache[1] = 1;
  }

  public static int codeSize(int n) {
    if (n == 0 || n == 1) {
      return 1;
    }
    if (cache[n] != 0) {
      return cache[n];
    }

    int fibanachi = ( codeSize(n-1) + codeSize(n-2)) % DECIMAL_POW_8;
    cache[n] = fibanachi;
    return fibanachi;
  }

  public static int mod(int lines, int k) {
//    System.out.println(lines);
//    System.out.println(k);
    return (int) (lines % Math.pow(10,k));
  }
  // 1 6558 0141
  // 2 6791 4296
  // 4 3349 4437
  // 7 0140 8733


// 65580141   - 40
// 67914296   - 41
// 33494437   - 42
// 140 8733  - 43
  // 11 34903170
  // 18 36311903

  //    public static int factorial(int n) {
  //        int accumulator = 1;
  //        int i = n;
  //        while (i > 1) {
  //            accumulator *= i;
  //            i--;
  //        }
  //        return accumulator;
  //    }

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      String cmd = reader.readLine();
      String[] values = cmd.split(" ");
      int n = Integer.parseInt(values[0]);
      int k = Integer.parseInt(values[1]);
      int lines =  codeSize(n);
      // mod(lines, k)
      System.out.println(lines);
    }
  }
}
