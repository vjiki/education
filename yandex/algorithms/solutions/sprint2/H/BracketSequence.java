import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BracketSequence {

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
      char[] stack = new char[bracketSequence.length/2];
      int leftLastIndex = 0;
      for (char c : bracketSequence) {
        if (c == '[' || c == '{' || c == '(') {
          stack[leftLastIndex] = c;
          leftLastIndex++;
        } else {
          if (leftLastIndex > 0) {
            if (!isPair(stack[leftLastIndex - 1], c)) {
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
