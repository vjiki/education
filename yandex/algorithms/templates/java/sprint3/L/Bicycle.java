import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Bicycle {

  static int getEnoughDaysForBicycle(int[] housesLine, int left, int right, int cost) {
    if (right <= left) {
      if (housesLine[left] >= cost) {
        return left;
      }
      return -1;
    }

    int mid = (left + right) / 2;
    if (housesLine[mid] >= cost && mid -1 >= 0 && housesLine[mid-1] < cost) {
      return mid;
    } else if (housesLine[mid]  >= cost) {
      return getEnoughDaysForBicycle(housesLine, left, mid, cost);
    } else {
      return getEnoughDaysForBicycle(housesLine,mid + 1, right, cost);
    }
  }



  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
      int numberLength = readInt(reader);
      int[] numberArray = readArray(reader);
      int k = readInt(reader);
      int firstDay = getEnoughDaysForBicycle(numberArray, 0, numberArray.length-1, k);
      int secondDay = getEnoughDaysForBicycle(numberArray, 0, numberArray.length-1, k*2);

      if (firstDay != -1) {
        firstDay++;
      }
      if (secondDay != -1) {
        secondDay++;
      }

      writer.write(firstDay + " " + secondDay);
    }
  }

  private static int[] readArray(BufferedReader reader) throws IOException {
    return Arrays.stream(reader.readLine().split(" "))
        .map(Integer::parseInt)
        .mapToInt(Integer::intValue).toArray();
  }

  private static int readInt(BufferedReader reader) throws IOException {
    return Integer.parseInt(reader.readLine());
  }
}
