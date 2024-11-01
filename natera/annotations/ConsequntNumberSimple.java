import java.util.ArrayList;
import java.util.List;

public class ConsequntNumberSimple {


  //    List<String> result = new ArrayList<>();
//    for (int i = 0; i < sortedNumericAnnotations.size(); i++) {
//      int start = i;
//      int size = 1;
//      int end = start;
//      if (i < sortedNumericAnnotations.size() - 2) {
//        end = start + 1;
//        while (sortedNumericAnnotations.get(end) == sortedNumericAnnotations.get(end - 1) + 1) {
//          size++;
//          end++;
//          if (end >= sortedNumericAnnotations.size()) {
//            break;
//          }
//        }
//      }
//      if (size >= MIN_LENGTH_OF_CONSECUTIVE_NUMBERS) {
//        result.add(sortedNumericAnnotations.get(start) + "-" + sortedNumericAnnotations.get(end - 1));
//        i = end - 1;
//      } else {
//        result.add("" + sortedNumericAnnotations.get(i));
//      }
//    }

  // Join the result list with commas
//    return String.join(",", result);


  /**
   * Helper method to process numbers and convert to ranges if needed
   *
   * @param result         current state with resulting annotations
   * @param currentNumbers ordered list with numeric symbols
   */
  private static void processNumbers(List<String> result, List<Integer> currentNumbers) {
    // If there are 3 or more consecutive numbers, create a range
    if (currentNumbers.size() >= 3) {
      // Extract consecutive numbers into ranges
      // Keep track of the current index in the numbers list
      int numIndex = 0;
      int start = currentNumbers.get(numIndex);
      int prev = start;
      int count = 1; // Keep track of how many consecutive numbers we have

      while (numIndex + 1 < currentNumbers.size() && (currentNumbers.get(numIndex + 1) == prev + 1)) {
//          ((currentNumbers.get(numIndex + 1) == prev + 1)) || ((currentNumbers.get(numIndex + 1) == prev))) {
//        if ((currentNumbers.get(numIndex + 1) == prev)) {
//          prev = currentNumbers.get(++numIndex);
//          continue;
//        }
        prev = currentNumbers.get(++numIndex);
        count++;
      }

      if (count >= 3) {
        // If there are 3 or more consecutive numbers, make it a range
        result.add(start + "-" + prev);
        if (count < currentNumbers.size()) {
          processNumbers(result, currentNumbers.subList(count, currentNumbers.size()));
        }
      } else {
        // If there are fewer than 3 consecutive numbers, add them individually
        if (currentNumbers.size() - count >= 3) {
          for (Integer currentNumber : currentNumbers.subList(0, count)) {
            result.add(String.valueOf(currentNumber));
          }
          processNumbers(result, currentNumbers.subList(count, currentNumbers.size()));
        } else {
          for (Integer currentNumber : currentNumbers) {
            result.add(String.valueOf(currentNumber));
          }
        }
      }
    } else {
      // Otherwise, add each number individually
      for (Integer num : currentNumbers) {
        result.add(num.toString());
      }
    }
  }

  static String getConsequence(List<Integer> inputList) {
    List<Integer> list = inputList.stream().distinct().sorted().toList();
    List<String> altered = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      int start = i;
      int size = 1;
      int end = start;
      if (i < list.size() - 2) {
        end = start + 1;
        while (list.get(end) == list.get(end - 1) + 1) {
          size++;
          end++;
          if (end >= list.size()) {
            break;
          }
        }
      }
      if (size >= 3) {
        altered.add(list.get(start) + "-" + list.get(end - 1));
        i = end - 1;
      } else {
        altered.add("" + list.get(i));
      }
    }
    return String.join(",", altered);
  }

  public static void main(String[] args) {
//    System.out.println(
//        getConsequence(List.of(-2, -1, 1, 2, 3, 4, 5, 6, 8, 1000, 9, 10, 12, 13, 14, 15, 16, 17, 18, 19, 20, 23, 25, 999,10003)));
    System.out.println(
        getConsequence(List.of(1,2)));
  }
}
