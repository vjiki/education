package sprint3.L;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LTest {

  int getEnoughDaysForBicycle(int[] housesLine, int left, int right, int cost) {
    if (right <= left) {
      if (housesLine[left] >= cost) {
        return left;
      }
      return -1;
    }

    int mid = (left + right) / 2;
    if (housesLine[mid] >= cost && housesLine[mid-1] < cost) {
      return mid;
    } else if (housesLine[mid]  >= cost) {
      return getEnoughDaysForBicycle(housesLine, left, mid, cost);
    } else {
      return getEnoughDaysForBicycle(housesLine,mid + 1, right, cost);
    }
  }



  @Test
  void successTest1() {
    int[] housesLine = {1, 2, 4, 4, 6, 8};

    int firstDay = getEnoughDaysForBicycle(housesLine, 0, housesLine.length-1, 3);
    int secondDay = getEnoughDaysForBicycle(housesLine, 0, housesLine.length-1, 6);

    if (firstDay != -1) {
      firstDay++;
    }
    if (secondDay != -1) {
      secondDay++;
    }

    assertEquals(3, firstDay);
    assertEquals(5, secondDay);
  }

  @Test
  void successTest2() {
    int[] housesLine = {1, 2, 4, 4, 4, 4};

    int firstDay = getEnoughDaysForBicycle(housesLine, 0, housesLine.length-1, 3);
    int secondDay = getEnoughDaysForBicycle(housesLine, 0, housesLine.length-1, 6);

    if (firstDay != -1) {
      firstDay++;
    }
    if (secondDay != -1) {
      secondDay++;
    }
    assertEquals(3, firstDay);
    assertEquals(-1, secondDay);
  }

  @Test
  void successTest3() {
    int[] housesLine = {1, 2, 4, 4, 4, 4};

    int firstDay = getEnoughDaysForBicycle(housesLine, 0, housesLine.length-1, 10);
    int secondDay = getEnoughDaysForBicycle(housesLine, 0, housesLine.length-1, 20);

    if (firstDay != -1) {
      firstDay++;
    }
    if (secondDay != -1) {
      secondDay++;
    }

    assertEquals(-1, firstDay);
    assertEquals(-1, secondDay);
  }
}
