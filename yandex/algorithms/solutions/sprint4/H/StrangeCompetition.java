import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class StrangeCompetition {

  private final static String YES = "YES";
  private final static String NO = "NO";

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      char[] firstRowArray = reader.readLine().toCharArray();
      char[] secondRowArray = reader.readLine().toCharArray();
      System.out.println(isEquals(firstRowArray, secondRowArray));
    }
  }
  static String isEquals(char[] first, char[] second) {

    if (first.length == 0 && second.length == 0) {
      return YES;
    }

    if (first.length != second.length) {
      return NO;
    }

    Map<Character, Character> symbolsMap = new HashMap<>();

    for (int i = 0; i < first.length; i++) {
      if (!symbolsMap.containsKey(first[i])) {
        for (char c: symbolsMap.values()) {
          if (c == second[i]) {
            return NO;
          }
        }
        symbolsMap.put(first[i], second[i]);
      } else {
        char expectedCharacter = symbolsMap.get(first[i]);
        if (second[i] != expectedCharacter) {
          return NO;
        }
      }
    }

    return YES;
  }
}
