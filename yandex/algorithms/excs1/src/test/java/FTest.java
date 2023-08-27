import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class FTest {

  private static boolean isPalindromeV0(String text) {
    char[] array = text.toCharArray();

    int j = array.length - 1;
    for (int i = 0; i < array.length; i++) {
      if (array[i] == array[j]) {
        if (i == j) {
          return true;
        }
      } else {
        return false;
      }
      j--;
    }

    // Ваше решение
    return false;
  }

  private static boolean isPalindrome(String text) {
    String strippedText = text.strip().toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
    System.out.println(strippedText);

    if(strippedText.isEmpty()) {
      return false;
    }
    if (strippedText.length() == 1) {
      return true;
    }
    if (strippedText.length() == 2 && strippedText.substring(0,1).equals(strippedText.substring(1,2))) {
      return true;
    }

    String part1 = strippedText.substring(0,strippedText.length()/2).strip().toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
    String part2 = strippedText.substring(strippedText.length()/2 + strippedText.length()%2).strip().toLowerCase();
    System.out.println(part1);
    System.out.println(part2);
    char[] chars = part2.toCharArray();
    char[] reversed = new char[chars.length];
    int j = 0;
    for (int i = part2.length() - 1; i >= 0; i--) {
      reversed[j] = chars[i];
      j++;
    }
    System.out.println(String.valueOf(reversed));
    return part1.equals(String.valueOf(reversed));
  }

  @Test
  void successTest1() {
    String text = "a ,%";

    assertTrue(isPalindrome(text));
  }

  @Test
  void successTest7() {
    String text = "ab";

    assertFalse(isPalindrome(text));
  }

  @Test
  void successTest2() {
    String text = "frog jumps from river";

    assertFalse(isPalindrome(text));
  }


  @Test
  void successTest3() {
    String text = "abba";

    assertTrue(isPalindrome(text));
  }

  @Test
  void successTest4() {
    String text = "abbba";

    assertTrue(isPalindrome(text));
  }

  @Test
  void successTest5() {
    String text = "a bbb a";

    assertTrue(isPalindrome(text));
  }

  @Test
  void successTest6() {
    String text = "a bb a";

    assertTrue(isPalindrome(text));
  }

  @Test
  void successTest8() {
    String text = "A man, a plan, a canal: Panama";

    assertTrue(isPalindrome(text));
  }

}
