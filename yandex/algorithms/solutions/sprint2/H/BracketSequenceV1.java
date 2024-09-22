import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BracketSequenceV1 {

  static boolean isPair(char bracketFirst, char bracketSecond) {
    if (bracketFirst == '[' && bracketSecond == ']') {
      return true;
    }
    if (bracketFirst == '(' && bracketSecond == ')') {
      return true;
    }
    return bracketFirst == '{' && bracketSecond == '}';
  }
  static boolean is_correct_bracket_seq(char[] bracketSequence) {
    if (bracketSequence.length == 0) {
      return true;
    }
    if(bracketSequence.length % 2 == 0) {
      int leftPairCounter = 0;
      int leftLastIndex = 0;
      boolean switchBrackets = false;
      for (int i = 0; i < bracketSequence.length; i++) {
        if (bracketSequence[i] == '[' || bracketSequence[i] == '{' || bracketSequence[i] == '(') {
            if (switchBrackets) {
              switchBrackets = false;
              leftPairCounter = 1;
            } else {
              leftPairCounter++;
            }
          leftLastIndex = i;
        } else {
            switchBrackets = true;
            if (leftPairCounter > 0) {
              if (!isPair(bracketSequence[leftLastIndex], bracketSequence[i])) {
                return false;
              }
              leftLastIndex--;
            } else {
              return false;
            }
        }
      }
      return true;

    } else {
      return false;
    }
  }

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      String s = reader.readLine();
      char[] brackets = s.toCharArray();
      System.out.println(is_correct_bracket_seq(brackets) ? "True" : "False");
    }
  }
}
