import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Competitions {

  static int getLongestSegmentWithDraw(int[] competitionsScores) {
    int sum = 0;
    Map<Integer, Integer> counts = new HashMap<>();
    int result = 0;

    for (int i = 0; i < competitionsScores.length; i++) {
      if (competitionsScores[i] == 0) {
        sum -= 1;
      } else {
        sum += 1;
      }
      if (sum == 0) {
        result = Math.max(result, i+1);
      } else if (!counts.containsKey(sum)) {
        counts.put(sum, i);
      } else {
        result = Math.max(result, i - counts.get(sum));
      }
    }

    return result;
  }

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      int listLength = readInt(reader);
      if (listLength <= 0 ) {
        System.out.println(listLength);
      } else {
        int[] competitionsScores = readArray(reader);
        System.out.println(getLongestSegmentWithDraw(competitionsScores));
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
