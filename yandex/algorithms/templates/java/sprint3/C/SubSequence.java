import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SubSequence {

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      char[] firstRowArray = reader.readLine().toCharArray();
      String secondRow = reader.readLine();
      System.out.println(isSubSequence(firstRowArray, secondRow));
    }
  }
  static String isSubSequence(char[] first, String second) {

    if (first.length == 0) {
      return "True";
    }
    
    int position = -1;

    for (int i = 0; i < first.length; i++) {
      position = second.indexOf(first[i], position + 1);
      if (position == -1) {
        return "False";
      }
    }

    return "True";
  }

}
