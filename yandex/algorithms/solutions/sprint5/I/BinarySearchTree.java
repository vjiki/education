import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class BinarySearchTree {

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      int n = readInt(reader);
      BigInteger result = factorial(2L * n).divide ((factorial(n).multiply(factorial(n + 1))));
      System.out.println(result);
    }
  }

  private static int readInt(BufferedReader reader) throws IOException {
    return Integer.parseInt(reader.readLine());
  }

  public static BigInteger factorial(long n) {
    BigInteger factorial = BigInteger.ONE;
    for(int i = 1; i <= n; ++i)
    {
      factorial = factorial.multiply(BigInteger.valueOf(i));
    }
    return factorial;
  }

}
