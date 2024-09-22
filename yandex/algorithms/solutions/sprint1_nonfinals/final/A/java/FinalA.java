// https://contest.yandex.ru/contest/22450/run-report/90082374/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

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

    int counter = housesLine.length;

    for (int i = 0; i < housesLine.length; i++) {
      if (housesLine[i] == 0) {
        distanceBetweenHousesArray[i] = 0;
        counter = 0;
      } else {
        counter++;
        distanceBetweenHousesArray[i] = counter;
      }
    }

    counter = housesLine.length;

    for (int i = housesLine.length - 1; i >= 0; i--) {
      if (housesLine[i] == 0) {
        counter = 0;
      } else {
        counter++;
        if (counter < distanceBetweenHousesArray[i]) {
          distanceBetweenHousesArray[i] = counter;
        }
      }
    }

    return distanceBetweenHousesArray;
  }
}
