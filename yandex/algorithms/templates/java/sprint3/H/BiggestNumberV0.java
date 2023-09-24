import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.io.InputStreamReader;
import java.util.Comparator;

public class BiggestNumberV0 {

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

        char[] firstNumberArray = firstNumber.toCharArray();
        char[] secondNumberArray = secondNumber.toCharArray();

        int minLength = Math.min(firstNumberArray.length, secondNumberArray.length);

        for (int i = 0; i < minLength; i++) {
          int i1 = Integer.parseInt(String.valueOf(firstNumberArray[i]));
          int i2 = Integer.parseInt(
              String.valueOf(secondNumberArray[i]));
          if (i1 > i2) {
            return -1;
          } else if (i1 < i2) {
            return 1;
          }
        }
        if (firstNumber.substring(0, minLength).equals(secondNumber.substring(0, minLength))
            && (firstNumberArray.length < secondNumberArray.length)) {
          StringBuilder sb = new StringBuilder();
          sb.append(firstNumber);
          for (int i = 0; i < secondNumberArray.length - minLength; i++) {
            sb.append(firstNumberArray[minLength - 1]);
          }
          String newFirstNumber = sb.toString();
          if (newFirstNumber.equals(secondNumber)) {
            if (Integer.parseInt(String.valueOf(firstNumberArray[minLength-1])) >
                Integer.parseInt(String.valueOf(secondNumberArray[0]))) {
              return 1;
            } else {
              return -1;
            }
          }
//          System.out.println("first: " + newFirstNumber + " " + secondNumber);
          return Integer.compare(Integer.parseInt(secondNumber), Integer.parseInt(newFirstNumber));
        } else {
          StringBuilder sb = new StringBuilder();
          sb.append(secondNumber);
          for (int i = 0; i < firstNumberArray.length - minLength; i++) {
            sb.append(secondNumberArray[minLength - 1]);
          }
          String newSecondNumber = sb.toString();
          if (newSecondNumber.equals(firstNumber)) {
            if (Integer.parseInt(String.valueOf(secondNumberArray[minLength-1])) >
                Integer.parseInt(String.valueOf(firstNumberArray[0]))) {
              return -1;
            } else {
              return 1;
            }
          }
//          System.out.println("second: " + newSecondNumber + " " + firstNumber);
          return Integer.compare(Integer.parseInt(newSecondNumber), Integer.parseInt(firstNumber));
        }
      }
    }
  }
}

// 99898098096495954947944943941937921919894868861857839838318288282582381079678577975674874873070770069679674649642620587576575755655305155095014974864744704684474434364244194184093923893843483403243193163029129028727126926326326251242222132111971931921811741601501481221041000100010001000100010001000
// 99898098096495954947944943941937921919894868861857839838318288282582381079678577975674874873070770069679674649642620587576575755655305155095014974864744704684474434364244194184093923893843483403243193163029129028727126926326326251242222132111971931921811741601501481221041000100010001000100010001000