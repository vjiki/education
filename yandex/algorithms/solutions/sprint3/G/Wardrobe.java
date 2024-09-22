import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.io.InputStreamReader;

public class Wardrobe {

  static void sortByColor(int[] numbers) {

    int pink = 0;
    int yellow = 0;
    int purple = 0;

    for (int i = 0; i < numbers.length; i++) {
      if (numbers[i] == 0) {
        pink++;
      } else if (numbers[i] == 1) {
        yellow++;
      } else {
        purple++;
      }
    }

    StringBuilder sb = new StringBuilder();
    while (pink != 0) {
      sb.append(0).append(" ");
      pink--;
    }

    while (yellow != 0) {
      sb.append(1).append(" ");
      yellow--;
    }

    while (purple != 0) {
      sb.append(2).append(" ");
      purple--;
    }

    sb.deleteCharAt(sb.length()-1);
    System.out.println(sb);

  }

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      int lineLengths = readInt(reader);
      if (lineLengths > 0) {
        int[] numbers = readArray(reader);
        sortByColor(numbers);
      } else {
        System.out.println();
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
