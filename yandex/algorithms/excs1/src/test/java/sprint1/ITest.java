package sprint1;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ITest {
  private static boolean isPowerOfFour(int n) {
    return (n | 0x1554) == 0x1554 || n == 1;
  }

  @Test
  void successTest1() {
    assertTrue(isPowerOfFour(4));
  }

  @Test
  void successTest2() {
    assertFalse(isPowerOfFour(15));
  }

  @Test
  void successTest3() {
    assertTrue(isPowerOfFour(64));
  }

  @Test
  void successTest6() {
    assertFalse(isPowerOfFour(5));
  }
}
