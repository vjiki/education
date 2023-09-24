package sprint2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

// 1 2 3
// 4 5 6

// 1 4
// 2 5
// 3 6

public class ATest {

  private static List<List<Integer>> rotateMatrix(List<List<Integer>> matrix) {
    List<List<Integer>> newMatrix = new ArrayList<>();
    for (int i = 0; i < matrix.get(0).size(); i++) {
      newMatrix.add(new ArrayList<>());
    }

    for (List<Integer> row : matrix) {
      List<Integer> newRow;
      for (int j = 0; j < row.size(); j++) {
        newRow = newMatrix.get(j);
        newRow.add(row.get(j));
      }
    }

    return newMatrix;
  }

  @Test
  void successTest1() {
    List<Integer> firstRow = new ArrayList<>();
    firstRow.add(1);
    firstRow.add(2);
    firstRow.add(3);
    List<Integer> secondRow = new ArrayList<>();
    secondRow.add(0);
    secondRow.add(2);
    secondRow.add(6);
    List<Integer> thirdRow = new ArrayList<>();
    thirdRow.add(7);
    thirdRow.add(4);
    thirdRow.add(1);
    List<Integer> forthRow = new ArrayList<>();
    forthRow.add(2);
    forthRow.add(7);
    forthRow.add(0);

    List<List<Integer>> matrix = new ArrayList<>(3);
    matrix.add(firstRow);
    matrix.add(secondRow);
    matrix.add(thirdRow);
    matrix.add(forthRow);

    List<List<Integer>> result = rotateMatrix(matrix);

    assertEquals(0,result.get(0).get(1));
    assertEquals(4,result.get(1).get(2));
  }

  @Test
  void successTest2() {
    List<Integer> firstRow = new ArrayList<>();
    firstRow.add(-7);
    firstRow.add(-1);
    firstRow.add(0);
    firstRow.add(-4);
    firstRow.add(-9);
    List<Integer> secondRow = new ArrayList<>();
    secondRow.add(5);
    secondRow.add(-1);
    secondRow.add(2);
    secondRow.add(2);
    secondRow.add(9);
    List<Integer> thirdRow = new ArrayList<>();
    thirdRow.add(3);
    thirdRow.add(1);
    thirdRow.add(-8);
    thirdRow.add(-1);
    thirdRow.add(-7);
    List<Integer> forthRow = new ArrayList<>();
    forthRow.add(9);
    forthRow.add(0);
    forthRow.add(8);
    forthRow.add(-8);
    forthRow.add(-1);

    List<List<Integer>> matrix = new ArrayList<>(5);
    matrix.add(firstRow);
    matrix.add(secondRow);
    matrix.add(thirdRow);
    matrix.add(forthRow);

    List<List<Integer>> result = rotateMatrix(matrix);

    assertEquals(3,result.get(0).get(2));
    assertEquals(0,result.get(1).get(3));
  }

  @Test
  void successTest3() {
    List<Integer> firstRow = new ArrayList<>();
    firstRow.add(0);
    List<Integer> secondRow = new ArrayList<>();
    secondRow.add(0);

    List<List<Integer>> matrix = new ArrayList<>(2);
    matrix.add(firstRow);
    matrix.add(secondRow);
    List<List<Integer>> result = rotateMatrix(matrix);

    assertEquals(0,result.get(0).get(0));
    assertEquals(0,result.get(0).get(1));
  }
}
