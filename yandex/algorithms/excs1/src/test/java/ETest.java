import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ETest {
  private static String getLongestWord(String text) {
    String[] splits = text.split(" ");
    if (splits.length == 0) {
      return "";
    }
    int longestIdx = 0;
    int longestLength = 0;
    for (int i = 0; i < splits.length; i++) {
      if (splits[i].length() > longestLength) {
        longestLength = splits[i].length();
        longestIdx = i;
      }
    }
    return splits[longestIdx];
  }

  @Test
  void successTest1() {
    String text = "i love segment tree";

    assertEquals("segment", getLongestWord(text));
  }

  @Test
  void successTest2() {
    String text = "frog jumps from river";

    assertEquals("jumps", getLongestWord(text));
  }

  @Test
  void successTest3() {
    String text = "frog";

    assertEquals("frog", getLongestWord(text));
  }


}
