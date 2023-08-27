import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ATest {

  private int evaluateFunction(int a, int b, int c, int x) {
    return a*x*x + b*x + c;
  }

  @Test
  void successTest1() {
    int result = evaluateFunction(-8,-2, 7, -5);

    assertEquals(-183, result);
  }

  @Test
  void successTest2() {
    int result = evaluateFunction(8, 9, -10, 2);

    assertEquals(40, result);
  }
}
