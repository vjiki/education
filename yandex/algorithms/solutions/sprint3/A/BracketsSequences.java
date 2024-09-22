import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BracketsSequences {

  static boolean isPair(char bracketFirst, char bracketSecond) {
    return bracketFirst == '(' && bracketSecond == ')';
  }

  static boolean is_correct_bracket_seq(char[] bracketSequence) {
    if (bracketSequence.length == 0) {
      return true;
    }
    if(bracketSequence.length % 2 == 0) {
      char[] stack = new char[bracketSequence.length];
      int leftLastIndex = 0;
      for (char c : bracketSequence) {
        if (c == '(') {
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
      return leftLastIndex == 0;
    } else {
      return false;
    }
  }

  static void getBrackets(int variantsLength, String prefix) {
      if (variantsLength == 0) {
        if (is_correct_bracket_seq(prefix.toCharArray())) {
          System.out.println(prefix);
        }
      } else {
        getBrackets(variantsLength - 1, prefix + "(");
        getBrackets(variantsLength - 1, prefix + ")");
      }
  }


  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      int variantsLength = readInt(reader) * 2;
      if (variantsLength == 0) {
        System.out.println();
      } else {
        getBrackets(variantsLength, "");
      }
    }
  }

  private static int readInt(BufferedReader reader) throws IOException {
    return Integer.parseInt(reader.readLine());
  }
}
