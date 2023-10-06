import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


public class FlowerBedsV1 {

  static void mergeBeds(int[][] allNumbers) {

    Arrays.sort(allNumbers, Comparator.comparingInt(c -> c[0]));

    List<int[]> result = new ArrayList<>();
    int index = 0;
    int left = allNumbers[0][0];
    int right = allNumbers[0][1];
    index++;

    for (int i = 0; i < allNumbers.length-1; i++) {
      if (left <= allNumbers[index][0] && allNumbers[index][0] <= right) {
        int currRight = allNumbers[index][1];
        index++;
        if (currRight > right) {
          right = currRight;
        }
      } else {
        result.add(new int[]{left, right});
        left = allNumbers[index][0];
        right = allNumbers[index][1];
        index++;
      }
    }
    result.add(new int[]{left, right});

    StringBuilder sb = new StringBuilder();
    for (int[] arr: result) {
      sb.append(arr[0]).append(" ").append(arr[1]).append("\n");
    }
    System.out.println(sb);

  }

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      int lineLengths = readInt(reader);
      int[][] allNumbers = new int[lineLengths][];
      for (int i = 0; i < lineLengths; i++) {
        int[] numbers = readArray(reader);
        allNumbers[i] = numbers;
      }


      mergeBeds(allNumbers);
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
