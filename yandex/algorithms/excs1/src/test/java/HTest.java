import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HTest {

  // O N
  private static String sumOfBinaries(String a, String b) {
    if (a.isEmpty() || b.isEmpty()) {
      return "";
    }

    if (a.length() > b.length()) {
      StringBuilder sb = new StringBuilder();
      int length = a.length() - b.length();
      sb.append(b);
      for (int i = 0; i < length; i++) {
        sb.insert(0, "0");
      }
      return sumOfBinaries(a, sb.toString());
    } else if (a.length() < b.length()) {
      return sumOfBinaries(b, a);
    }

    StringBuilder sb = new StringBuilder();
    boolean nextReg = false;
    for (int i = a.length() - 1; i >= 0; i--) {
      if (a.charAt(i) == '1' && b.charAt(i) == '1') {
        if (nextReg) {
          sb.append("1");
        } else {
          sb.append("0");
        }
        nextReg = true;
      } else if (a.charAt(i) == '1' || b.charAt(i) == '1') {
        if (nextReg) {
          sb.append("0");
        } else {
          sb.append("1");
          nextReg = false;
        }
      } else {
        if (nextReg) {
          sb.append("1");
          nextReg = false;
        } else {
          sb.append("0");
        }
      }
    }
    if (nextReg) {
      sb.append("1");
    }

    return sb.reverse().toString();
  }

  @Test
  void successTest1() {
    assertEquals("10101", sumOfBinaries("1010", "1011"));
  }

  @Test
  void successTest2() {
    assertEquals("10", sumOfBinaries("1", "1"));
  }

  @Test
  void successTest3() {
    assertEquals("0", sumOfBinaries("0", "0"));
  }

  @Test
  void successTest4() {
    assertEquals("10", sumOfBinaries("10", "0"));
  }

  @Test
  void successTest5() {
    assertEquals("110", sumOfBinaries("11", "11"));
  }

  @Test
  void successTest6() {
    assertEquals("1101", sumOfBinaries("1010", "11"));
  }

  @Test
  void successTest7() {
    assertEquals("10100000000011", sumOfBinaries("10100000000000", "11"));
  }
  //             //001100
  //             //101111
  // 111011
  @Test
  void successTest8() {
    assertEquals("111011", sumOfBinaries("1100", "101111"));
  }

  @Test
  void successTest9() {
    assertEquals("1001011", sumOfBinaries("11100", "101111"));
  }

}
