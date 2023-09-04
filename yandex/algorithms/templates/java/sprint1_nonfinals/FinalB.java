// https://contest.yandex.ru/contest/22450/run-report/90019542/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FinalB {

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

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      int k = readInt(reader);
      char[][] gameField = readRows(reader);

      int maxPoints = getMaxPoints(k, gameField);
      System.out.println(maxPoints);
    }
  }

  private static int readInt(BufferedReader reader) throws IOException {
    return Integer.parseInt(reader.readLine());
  }

  private static char[][] readRows(BufferedReader reader) throws IOException {
    char[][] gameField = new char[GAME_FIELD_SIZE][GAME_FIELD_SIZE];
    for (int i = 0; i < GAME_FIELD_SIZE; i++) {
      String line = reader.readLine();
      for (int j = 0; j < GAME_FIELD_SIZE; j++) {
        gameField[i][j] = line.charAt(j);
      }
    }
    return gameField;
  }
}
