import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GTest {

  private static String getBinaryNumber(int n) {
    if ( n > 10000 || n < 0) {
      return "";
    }
    if (n == 0) {
      return "0";
    }

    int bitCheck = 1;
    boolean skip = true;
    StringBuilder sb = new StringBuilder();

    for (int i = 13; i >=0; i--) {
      bitCheck = 1 << i;
      if ((n & bitCheck) > 0) {
        sb.append("1");
        skip = false;
      } else {
        if(!skip) {
          sb.append("0");
        }
      }
    }
    
    return sb.toString();
  }

  @Test
  void successTest1() {
    assertEquals("101",getBinaryNumber(5));
  }

  @Test
  void successTest2() {

    assertEquals("1110",getBinaryNumber(14));
  }

  @Test
  void successTest3() {

    assertEquals("",getBinaryNumber(10001));
  }

  @Test
  void successTest4() {

    assertEquals("0",getBinaryNumber(0));
  }


}
