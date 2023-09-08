import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BracketSequenceV0 {

  static boolean isPair(char bracketFirst, char bracketSecond) {
    if (bracketFirst == '[' && bracketSecond == ']') {
      return true;
    }
    if (bracketFirst == '(' && bracketSecond == ')') {
      return true;
    }
    return bracketFirst == '{' && bracketSecond == '}';
  }

  static boolean isCorrect(char[] bracketSequence) {
    System.out.println(bracketSequence);
    int firstIndex = 0;
    int secondIndex = bracketSequence.length-1;
    while(firstIndex != bracketSequence.length/2) {
      boolean isBracket = isPair(bracketSequence[firstIndex], bracketSequence[secondIndex]);
      if (!isBracket) {
        return false;
      }
      firstIndex++;
      secondIndex--;
    }
    System.out.println(true);
    return true;
  }

  //(()){}[]
  static boolean is_correct_bracket_seq(char[] bracketSequence) {
    if (bracketSequence.length == 0) {
      return true;
    }
    if(bracketSequence.length % 2 == 0) {
      boolean isSeq = true;
      int start = 0;
      int pointer = 0;
      while (pointer != bracketSequence.length/2) {
       if (isPair(bracketSequence[pointer], bracketSequence[pointer+1])) {
         isSeq = isCorrect(Arrays.copyOfRange(bracketSequence, start, (pointer+1)*2));
         if (!isSeq) {
           System.out.println(false);
           return false;
         }
         pointer= pointer+2;
       } else {
         pointer++;
       }
      }
      return isSeq;
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
