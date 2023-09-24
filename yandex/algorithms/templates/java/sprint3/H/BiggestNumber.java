import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BiggestNumber {

  static String biggestNumber(List<String> numbers, Comparator<String> less) {
    numbers.sort(less);

    System.out.println(numbers);

    return "";
  }


  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      BigNumberComparator bigNumberComparator = new BigNumberComparator();
      int listLength = readInt(reader);
      List<String> numbers = readArray(reader);
      System.out.println(biggestNumber(numbers, bigNumberComparator));
    }
  }

  private static int readInt(BufferedReader reader) throws IOException {
    return Integer.parseInt(reader.readLine());
  }

  private static List<String> readArray(BufferedReader reader) throws IOException {
    return Arrays.stream(reader.readLine().split(" ")).collect(Collectors.toList());
  }

  public static class BigNumberComparator implements Comparator<String> {

    // Returns a negative integer, zero,
// or a positive integer as the first argument is less than, equal to, or greater than the second.
    @Override
    public int compare(String firstNumber, String secondNumber) {
      System.out.println(firstNumber + " " + secondNumber);
      if (firstNumber.equals(secondNumber)) {
        return 0;
      } else {
        char[] firstNumberArray = firstNumber.toCharArray();
        char[] secondNumberArray = secondNumber.toCharArray();

        int minLength = Math.min(firstNumberArray.length, secondNumberArray.length);

        for (int i = 0; i < minLength; i++) {
          System.out.println(Integer.parseInt(String.valueOf(firstNumberArray[i])));
          System.out.println(Integer.parseInt(String.valueOf(secondNumberArray[i])));
          if (Integer.parseInt(String.valueOf(firstNumberArray[i])) > Integer.parseInt(
              String.valueOf(secondNumberArray[i]))) {
            return 1;
          }
        }

        return -1;
      }
    }
  }
}
