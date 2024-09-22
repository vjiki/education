import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Algos {

  static boolean isTwoParts(int[] competitionsScores) {

    Arrays.sort(competitionsScores);
    long leftSum = 0;
    long rightSum = 0;
    for (int i = competitionsScores.length-1; i >= 0; i--) {
      if(leftSum >= rightSum) {
        rightSum += competitionsScores[i];
      } else {
        leftSum += competitionsScores[i];
      }
    }
      return leftSum == rightSum;
  }

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      int listLength = readInt(reader);
      if (listLength <= 1 ) {
        System.out.println("False");
      } else {
        int[] competitionsScores = readArray(reader);
        System.out.println(isTwoParts(competitionsScores) ? "True" : "False");
      }
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
}
