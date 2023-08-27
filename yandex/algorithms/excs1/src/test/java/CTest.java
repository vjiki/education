import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class CTest {

  //   //4
  //  //3
  //  //1 2 3
  //  //0 2 6
  //  //7 4 1
  //  //2 7 0
  //  //3
  //  //0



  private static List<Integer> getNeighbours(List<List<Integer>> matrix, int row, int col) {
    List<Integer> currentRow = matrix.get(row);
    List<Integer> result = new ArrayList<>();

    if(matrix.isEmpty() || row > matrix.size() || col > currentRow.size()) {
      return Collections.emptyList();
    }

    if (row + 1 < matrix.size()) {
      result.add(matrix.get(row + 1).get(col));
    }
    if (col + 1 < currentRow.size()) {
      result.add(matrix.get(row).get(col+1));
    }
    if (row > 0) {
      result.add(matrix.get(row-1).get(col));
    }
    if (col - 1 >= 0) {
      result.add(matrix.get(row).get(col-1));
    }
    result.sort(Integer::compareTo);

    return result;
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

    List<Integer> result = getNeighbours(matrix,3, 0);

    assertEquals(7,result.get(0));
    assertEquals(7,result.get(1));
  }

  @Test
  void successTest2() {
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

    List<List<Integer>> matrix = new ArrayList<>(4);
    matrix.add(firstRow);
    matrix.add(secondRow);
    matrix.add(thirdRow);
    matrix.add(forthRow);

    List<Integer> result = getNeighbours(matrix,0, 0);

    assertEquals(0,result.get(0));
    assertEquals(2,result.get(1));
  }

  @Test
  void successTest3() {
    List<Integer> firstRow = new ArrayList<>();
    firstRow.add(4);
    firstRow.add(-10);
    firstRow.add(4);
    List<Integer> secondRow = new ArrayList<>();
    secondRow.add(-3);
    secondRow.add(0);
    secondRow.add(-1);
    List<Integer> thirdRow = new ArrayList<>();
    thirdRow.add(-1);
    thirdRow.add(-5);
    thirdRow.add(1);

    List<List<Integer>> matrix = new ArrayList<>(3);
    matrix.add(firstRow);
    matrix.add(secondRow);
    matrix.add(thirdRow);

    List<Integer> result = getNeighbours(matrix,1, 0);

    assertEquals(-1,result.get(0));
    assertEquals(0,result.get(1));
    assertEquals(4,result.get(2));
  }

  // 3
  //10
  //4 -10 4 -9 9 5 -7 1 4 -3
  //-3 0 -1 -6 -6 2 3 3 4 0
  //-1 -5 1 -9 -9 -6 3 -1 -10 -7
  //1
  //0

  // -1 0 4

  // 8
  //9
  //3 3 -9 7 -5 8 -6 -10 -4
  //5 -2 -6 -9 8 -4 5 -5 0
  //-9 -3 3 2 1 -4 -6 3 -9
  //-7 1 -2 4 -2 1 -5 4 -8
  //-2 5 5 7 -7 2 3 -4 -4
  //-1 7 -10 7 4 5 -7 1 5
  //-1 3 0 -8 -10 -2 5 1 7
  //10 4 -9 5 3 -1 7 10 -5
  //3
  //0

  // -2 1 -9
  //Правильный ответ
  //-9 -2 1
}
