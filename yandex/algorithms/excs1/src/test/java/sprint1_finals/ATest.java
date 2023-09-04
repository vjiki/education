package sprint1_finals;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ATest {

  int[] getDistanceBetweenHousesV0(int[] housesLine) {

    int[] distanceBetweenHousesArray = new int[housesLine.length];

    for (int i = 0; i < housesLine.length; i++) {
      if (housesLine[i] == 0) {
        distanceBetweenHousesArray[i] = 0;
        continue;
      }

      int firstPointer = i + 1;
      int secondPointer = i - 1;

      int minDistanceLeft = 1;
      int minDistanceRight = 1;

      if (firstPointer >= housesLine.length) {
        firstPointer = housesLine.length - 1;
      }
      if (secondPointer <= 0) {
        secondPointer = housesLine.length - 1;
      }

      while (firstPointer != secondPointer) {

        if (firstPointer == housesLine.length) {
          firstPointer = 0;
        }
        if (secondPointer <= 0) {
          secondPointer = housesLine.length - 1;
        }

        if (housesLine[firstPointer] == 0) {
          distanceBetweenHousesArray[i] = minDistanceRight;
          break;
        } else {
          minDistanceRight++;
          firstPointer++;
        }

        if (housesLine[secondPointer] == 0) {
          distanceBetweenHousesArray[i] = minDistanceLeft;
          break;
        } else {
          minDistanceLeft++;
          secondPointer--;
        }
      }
    }

    return distanceBetweenHousesArray;

  }

  int[] getDistanceBetweenHousesV1(int[] housesLine) {

    int[] distanceBetweenHousesArray = new int[housesLine.length];

    for (int i = 0; i < housesLine.length; i++) {
      if (housesLine[i] == 0) {
        distanceBetweenHousesArray[i] = 0;
        continue;
      }

      int firstPointer = i + 1;
      int secondPointer = i - 1;
      int minDistanceLeft = 1;
      int minDistanceRight = 1;
      boolean foundRight = false;
      boolean foundLeft = false;

      for (int j = firstPointer; j < housesLine.length; j++) {
        if (housesLine[j] == 0) {
          foundRight = true;
          break;
        } else {
          minDistanceRight++;
        }
      }

      for (int j = secondPointer; j >= 0; j--) {
        if (housesLine[j] == 0) {
          foundLeft = true;
          break;
        } else {
          minDistanceLeft++;
        }
      }
      if (foundLeft && foundRight) {
        distanceBetweenHousesArray[i] = Math.min(minDistanceLeft, minDistanceRight);
      } else if (foundLeft) {
        distanceBetweenHousesArray[i] = minDistanceLeft;
      } else {
        distanceBetweenHousesArray[i] = minDistanceRight;
      }
    }

    return distanceBetweenHousesArray;
  }

  //     int[] housesLine = {0, 7, 9, 4, 8, 20};
  // 0 7 9 4 7 20 0 10 0 22 0
  // 0 1 1 1 1 1 0 1 0 1 0

  // 0 5 4 3 2 1 0 1 0 1 0
  // 0 1 2 3 4 5 0 1 0 1 0

  // 0 1 2 3 2 1 0 1 0 1 0


  int[] getDistanceBetweenHousesNotWorking(int[] housesLine) {

    int[] distanceBetweenHousesArrayDirect = new int[housesLine.length];
    int[] distanceBetweenHousesArrayReverse = new int[housesLine.length];
    int[] distanceBetweenHousesArray = new int[housesLine.length];
    int firstIndex = 0;

    if (housesLine[0] == 0) {
      distanceBetweenHousesArrayDirect[0] = 0;
      firstIndex = 1;
    }
    int minDistanceLeft = 1;

    for (int i = 1; i < housesLine.length; i++) {
      if (housesLine[i] == 0) {
        distanceBetweenHousesArrayDirect[i] = 0;
        for (int j = firstIndex; j < i; j++) {
          distanceBetweenHousesArrayDirect[j] = minDistanceLeft;
          minDistanceLeft--;
          firstIndex = i + 1;
        }
        if (housesLine[0] == 0) {
          distanceBetweenHousesArrayDirect[0] = 0;
        }
      } else {
        distanceBetweenHousesArrayDirect[i] = housesLine.length - 1;
        minDistanceLeft++;
      }
    }

    int minDistanceRight = 1;
    int secondIndex = housesLine.length - 1;


    for (int i = housesLine.length -2; i >= 0; i--) {
      if (housesLine[i] == 0) {
        distanceBetweenHousesArrayReverse[i] = 0;
        for (int j = secondIndex; j > i; j--) {
          distanceBetweenHousesArrayReverse[j] = minDistanceRight;
          minDistanceRight--;
          secondIndex = i - 1;
        }
      } else {
        distanceBetweenHousesArrayReverse[i] = housesLine.length - 1;
        minDistanceRight++;
      }
    }

    System.out.println(Arrays.toString(distanceBetweenHousesArrayDirect));
    System.out.println(Arrays.toString(distanceBetweenHousesArrayReverse));

    for (int i = 0; i < housesLine.length; i++) {
      distanceBetweenHousesArray[i] = Math.min(distanceBetweenHousesArrayDirect[i], distanceBetweenHousesArrayReverse[i]);
    }

    return distanceBetweenHousesArray;
  }

  int[] getDistanceBetweenHouses(int[] housesLine) {

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

      // 0 1 4 9 0 1
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


  @Test
  void successTest1() {
    // 0 1 4 9 0
    int[] housesLine = {0, 1, 4, 9, 0};
    int[] expectedDistanceArray = {0, 1, 2, 1, 0};

    int[] result = getDistanceBetweenHouses(housesLine);

    System.out.println(Arrays.toString(expectedDistanceArray));
    System.out.println(Arrays.toString(result));


    assertTrue(Arrays.equals(expectedDistanceArray, result));
  }


  @Test
  void successTest2() {
    // 0 7 9 4 8 20
    int[] housesLine = {0, 7, 9, 4, 8, 20};
    int[] expectedDistanceArray = {0, 1, 2, 3, 4, 5};

    int[] result = getDistanceBetweenHouses(housesLine);

    System.out.println(Arrays.toString(expectedDistanceArray));
    System.out.println(Arrays.toString(result));


    assertTrue(Arrays.equals(expectedDistanceArray, result));
  }

// 98 0 10 77 0 59 28 0 94

  // 1 0 1 1 0 1 1 0 1

  @Test
  void successTest3() {
    // 0 7 9 4 8 20
    int[] housesLine = {98, 0, 10, 77, 0, 59, 28, 0, 94};
    int[] expectedDistanceArray = {1, 0, 1, 1, 0, 1, 1, 0, 1};

    int[] result = getDistanceBetweenHouses(housesLine);

    System.out.println(Arrays.toString(expectedDistanceArray));
    System.out.println(Arrays.toString(result));


    assertTrue(Arrays.equals(expectedDistanceArray, result));
  }

  // 0 3 41 0 0 0 0 0 49 0 0 56 0 88

  @Test
  void successTest4() {
    int[] housesLine = {0, 3, 41, 0, 0, 0, 0, 0, 49, 0, 0, 56, 0, 88};
    // 0 1 1 0 0 0 0 0 1 0 0 1 0 1
    int[] expectedDistanceArray = {0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1};

    int[] result = getDistanceBetweenHouses(housesLine);

    System.out.println(Arrays.toString(expectedDistanceArray));
    System.out.println(Arrays.toString(result));


    assertTrue(Arrays.equals(expectedDistanceArray, result));
  }


  @Test
  void successTest5() {
    // 0 7 9 4 8 20
    int[] housesLine = {20, 7, 9, 4, 8, 0};
    int[] expectedDistanceArray = {5, 4, 3, 2, 1, 0};

    int[] result = getDistanceBetweenHouses(housesLine);

    System.out.println(Arrays.toString(expectedDistanceArray));
    System.out.println(Arrays.toString(result));


    assertTrue(Arrays.equals(expectedDistanceArray, result));
  }

  @Test
  void successTest6() {
    int[] housesLine = {20, 7, 9, 0, 8, 1};
    int[] expectedDistanceArray = {3, 2, 1, 0, 1, 2};

    int[] result = getDistanceBetweenHouses(housesLine);

    System.out.println(Arrays.toString(expectedDistanceArray));
    System.out.println(Arrays.toString(result));


    assertTrue(Arrays.equals(expectedDistanceArray, result));
  }

  @Test
  void successTest7() {
    int[] housesLine = {20, 0, 9, 5, 8, 1};
    int[] expectedDistanceArray = {1, 0, 1, 2, 3, 4};

    int[] result = getDistanceBetweenHouses(housesLine);

    System.out.println(Arrays.toString(expectedDistanceArray));
    System.out.println(Arrays.toString(result));


    assertTrue(Arrays.equals(expectedDistanceArray, result));
  }

}
