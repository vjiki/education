// https://contest.yandex.ru/contest/22450/run-report/90053684/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FinalA {

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
      int length = readInt(reader);
      int[] numberArray = readIntArray(reader);
      int[] housesDistanceArray = getDistanceBetweenHouses(numberArray);
      for (int elem : housesDistanceArray) {
        writer.write(elem + " ");
      }
    }
  }

  private static int[] readIntArray(BufferedReader reader) throws IOException {
    return Arrays.stream(reader.readLine().split(" "))
        .mapToInt(Integer::parseInt)
        .toArray();
  }

  private static int readInt(BufferedReader reader) throws IOException {
    return Integer.parseInt(reader.readLine());
  }

  static int[] getDistanceBetweenHouses(int[] housesLine) {

    int[] distanceBetweenHousesArray = new int[housesLine.length];
    List<Integer> indexesOfZeros = new ArrayList<>();

    for (int i = 0; i < housesLine.length; i++) {
      if (housesLine[i] == 0) {
        distanceBetweenHousesArray[i] = 0;
        indexesOfZeros.add(i);
      } else {
        distanceBetweenHousesArray[i] = housesLine.length - 1;
      }
    }

    if (indexesOfZeros.size() == 1) {
      int zeroIndex = indexesOfZeros.get(0);
      for (int i = 0; i < housesLine.length; i++) {
        if (housesLine[i] != 0) {
          distanceBetweenHousesArray[i] = Math.abs(i - zeroIndex);
        }
      }
    } else {
      int indexLeft = 0;
      int indexRight = 1;
      int countZeroes = 0;

      for (int i = 0; i < housesLine.length; i++) {
        if (housesLine[i] != 0) {
          int zeroIndexLeft = indexesOfZeros.get(indexLeft);
          int zeroIndexRight = indexesOfZeros.get(indexRight);
          int distanceLeft = Math.abs(i - zeroIndexLeft);
          int distanceRight = Math.abs(i - zeroIndexRight);
          distanceBetweenHousesArray[i] = Math.min(distanceLeft, distanceRight);
        } else {
          countZeroes++;
          if (countZeroes >= 2) {
            if (indexRight < indexesOfZeros.size() -1) {
              indexLeft++;
              indexRight++;
            }
          }
        }
      }
    }
    return distanceBetweenHousesArray;
  }
}
