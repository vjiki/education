package sprint1_finals;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BTest {

  // 3
  //1231
  //2..2
  //2..2
  //2..2

  // output
  // 2

  private static final int GAME_FIELD_SIZE = 4;
  private static final int NUMBER_OF_PLAYERS = 2;

  private static int getMaxPoints(int k, char[][] gameField) {
    int maxCountKeys = NUMBER_OF_PLAYERS * k;
    int points = 0;
    for (int t = 1; t <= 9; t++) {

      int roundCount = 0;
      for (char[] chars : gameField) {
        for (char symbol : chars) {
          if (Character.isDigit(symbol) && Integer.parseInt(String.valueOf(symbol)) == t) {
            roundCount++;
          }
        }
      }
      if (roundCount <= maxCountKeys && roundCount != 0) {
        points++;
      }
    }
    return points;
  }

  @Test
  void successTest1() {
    char[][] gameField = new char[4][4];

    gameField[0][0] = '1';
    gameField[0][1] = '2';
    gameField[0][2] = '3';
    gameField[0][3] = '1';

    //
    gameField[1][0] = '2';
    gameField[1][1] = '.';
    gameField[1][2] = '.';
    gameField[1][3] = '2';

    //
    gameField[2][0] = '2';
    gameField[2][1] = '.';
    gameField[2][2] = '.';
    gameField[2][3] = '2';

    //
    gameField[3][0] = '2';
    gameField[3][1] = '.';
    gameField[3][2] = '.';
    gameField[3][3] = '2';

    assertEquals(2, getMaxPoints(3, gameField));
  }

  @Test
  void successTest2() {
    char[][] gameField = new char[4][4];

    gameField[0][0] = '1';
    gameField[0][1] = '1';
    gameField[0][2] = '1';
    gameField[0][3] = '1';

    //
    gameField[1][0] = '9';
    gameField[1][1] = '9';
    gameField[1][2] = '9';
    gameField[1][3] = '9';

    //
    gameField[2][0] = '1';
    gameField[2][1] = '1';
    gameField[2][2] = '1';
    gameField[2][3] = '1';

    //
    gameField[3][0] = '9';
    gameField[3][1] = '9';
    gameField[3][2] = '1';
    gameField[3][3] = '1';

    assertEquals(1, getMaxPoints(4, gameField));
  }

  @Test
  void successTest3() {

    char[][] gameField = new char[4][4];

    gameField[0][0] = '1';
    gameField[0][1] = '1';
    gameField[0][2] = '1';
    gameField[0][3] = '1';

    //
    gameField[1][0] = '1';
    gameField[1][1] = '1';
    gameField[1][2] = '1';
    gameField[1][3] = '1';

    //
    gameField[2][0] = '1';
    gameField[2][1] = '1';
    gameField[2][2] = '1';
    gameField[2][3] = '1';

    //
    gameField[3][0] = '1';
    gameField[3][1] = '1';
    gameField[3][2] = '1';
    gameField[3][3] = '1';

    assertEquals(0, getMaxPoints(4, gameField));
  }
}
