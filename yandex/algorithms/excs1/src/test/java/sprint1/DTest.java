package sprint1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class DTest {

  private static int getWeatherRandomness(List<Integer> temperatures) {
    // Ваше решение
    if (temperatures.isEmpty()) {
      return 0;
    }
    if(temperatures.size() == 1) {
      return 1;
    }
    if (temperatures.size() == 2) {
      return temperatures.get(0).equals(temperatures.get(1)) ? 0 : 1;
    }

    int haoticDays = 0;
    if(temperatures.get(0) > temperatures.get(1)) {
      haoticDays++;
    }
    if (temperatures.get(temperatures.size() -1) > temperatures.get(temperatures.size()-2)) {
      haoticDays++;
    }

    for (int i = 1; i < temperatures.size() - 1; i++) {
      if (temperatures.get(i) > temperatures.get(i-1) && temperatures.get(i) > temperatures.get(i+1) ) {
          haoticDays++;
      }
    }

    return haoticDays;
  }


  @Test
  void successTest1() {
    // 7
    //-1 -10 -8 0 2 0 5
    List<Integer> integerList = new ArrayList<>();
    integerList.add(-1);
    integerList.add(-10);
    integerList.add(-8);
    integerList.add(0);
    integerList.add(2);
    integerList.add(0);
    integerList.add(5);

    int result = getWeatherRandomness(integerList);

    assertEquals(3, result);
  }

  @Test
  void successTest2() {
    List<Integer> integerList = new ArrayList<>();
    integerList.add(1);
    integerList.add(2);
    integerList.add(5);
    integerList.add(4);
    integerList.add(8);

    int result = getWeatherRandomness(integerList);

    assertEquals(2, result);
  }
}
