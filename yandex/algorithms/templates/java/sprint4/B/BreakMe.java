import java.io.IOException;
import java.util.Random;

public class BreakMe {

  public static int getRandomNumberUsingNextInt(int min, int max) {
    Random random = new Random();
    return random.nextInt(max - min) + min;
  }

  public static String generateString(int targetStringLength) {
    int leftLimit = 97; // letter 'a'
    int rightLimit = 122; // letter 'z'
    Random random = new Random();

    return random.ints(leftLimit, rightLimit + 1)
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();

  }

  public static void main(String[] args) throws IOException {
      int a = 1000;
      int m = 123987123;


      while (true) {
        int length = getRandomNumberUsingNextInt(1,100);
        String row1 = generateString(length);
        String row2 = generateString(length);

        if (!row1.equals(row2)) {
          boolean res = getHash(a, m, row1.toCharArray()) == getHash(a, m, row2.toCharArray());
          if (res) {
            System.out.println(row1);
            System.out.println(row2);
            break;
          }
        }
      }
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
