import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BTest {
  private static final int MIN_RANGE = -1000000000;
  private static final int MAX_RANGE = 1000000000;

  public static boolean betweenInclusive(int x, int min, int max)
  {
    return x>=min && x<=max;
  }

  private static boolean checkParity(int a, int b, int c) {
    if (betweenInclusive(a, MIN_RANGE, MAX_RANGE) &&
        betweenInclusive(b, MIN_RANGE, MAX_RANGE) &&
        betweenInclusive(c, MIN_RANGE, MAX_RANGE)) {
        boolean aParity = a % 2 == 0;
        boolean bParity = b % 2 == 0;
        boolean cParity = c % 2 == 0;

      return aParity == bParity && bParity == cParity;
    }
    return false;
  }

  @Test
  void successTest1() {
    boolean result = checkParity(1,2, -3);

    assertFalse(result);
  }

  @Test
  void successTest2() {
    boolean result = checkParity(7,11, 7);

    assertTrue(result);
  }

  @Test
  void successTest3() {
    boolean result = checkParity(6,-2, 0);

    assertTrue(result);
  }


}
