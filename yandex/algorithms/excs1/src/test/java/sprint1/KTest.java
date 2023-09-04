package sprint1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KTest {

  static List<Integer> getSum(List<Integer> maxList, List<Integer> minList) {
    int indexK = minList.size() - 1;
    List<Integer> result = new ArrayList<>();

    int extraSum = 0;
    for (int i = maxList.size() - 1; i >= 0; i--) {
      if (indexK >= 0) {
        int sum = maxList.get(i) + minList.get(indexK) + extraSum;
        char[] sumChars = String.valueOf(sum).toCharArray();
        int lastChar = Integer.parseInt(String.valueOf(sumChars[0]));
        if (sumChars.length == 1) {
          extraSum = 0;
          result.add(0, lastChar);
        } else {
          extraSum = lastChar;
          result.add(0, Integer.parseInt(String.valueOf(sumChars[1])));
        }
        indexK--;
      } else {
        result.add(0, maxList.get(i) + extraSum);
        extraSum = 0;
      }
    }
    if (extraSum != 0) {
      result.add(0, extraSum);
    }

    return result;
  }

  private static List<Integer> getSum(List<Integer> numberList, int k) {

    char[] chars = String.valueOf(k).toCharArray();

    List<Integer> secondList = new ArrayList<>();
    for (char aChar : chars) {
      secondList.add(Integer.parseInt(String.valueOf(aChar)));
    }

    if (numberList.size() >= secondList.size()) {
      return getSum(numberList, secondList);
    } else {
      return getSum(secondList, numberList);
    }
  }

  @Test
  void successTest1() {
    assertEquals(List.of(1, 2, 3, 4), getSum(List.of(1, 2, 0, 0), 34));
  }

  @Test
  void successTest2() {
    assertEquals(List.of(1, 1, 2), getSum(List.of(9, 5), 17));
  }

  @Test
  void successTest3() {
    assertEquals(List.of(7, 9, 9, 4), getSum(List.of(7, 9, 9, 1), 3));
  }

  @Test
  void successTest4() {
    assertEquals(List.of(1, 3, 6), getSum(List.of(1, 1), 125));
  }

  // 5 6
  //7285

  @Test
  void successTest5() {
    assertEquals(List.of(7, 3, 4, 1), getSum(List.of(5, 6), 7285));
  }

  // 1 4 2 7 5 1 7 9 7 9 0 8 6 9 5 9 8 6 6 2 0 1 1 6 2 1 2 8 7 2 6 9 8 4 9 2 6 1 0 9
  // 1 4 2 7 5 1 7 9 7 9 0 8 6 9 5 9 8 6 6 2 0 1 1 6 2 1 2 8 7 2 6 9 8 4 9 2 6 1 7 2
}
