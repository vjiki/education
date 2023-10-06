import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class BiggestNumberFinal {

  static String biggestNumber(String[] numbers, Comparator<String> less) {
    Arrays.sort(numbers, less);

    StringBuilder sb = new StringBuilder();

    for (String number : numbers) {
      sb.append(number);
    }

    return sb.toString();
  }


  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      BigNumberComparator bigNumberComparator = new BigNumberComparator();
      int listLength = readInt(reader);
      String[] numbers = readArray(reader);
      System.out.println(biggestNumber(numbers, bigNumberComparator));
    }
  }

  private static int readInt(BufferedReader reader) throws IOException {
    return Integer.parseInt(reader.readLine());
  }

  private static String[] readArray(BufferedReader reader) throws IOException {
    return Arrays.stream(reader.readLine().split(" ")).toArray(String[]::new);
  }

  public static class BigNumberComparator implements Comparator<String> {

    @Override
    public int compare(String firstNumber, String secondNumber) {
      if (firstNumber.equals(secondNumber)) {
        return 0;
      } else {
        if (firstNumber.length() == secondNumber.length()) {
          return Integer.compare(Integer.parseInt(secondNumber), Integer.parseInt(firstNumber));
        }

        String leftFirst = firstNumber + secondNumber;
        String secondFirst = secondNumber + firstNumber;

        return Integer.compare(Integer.parseInt(secondFirst), Integer.parseInt(leftFirst));
      }
    }
  }
}