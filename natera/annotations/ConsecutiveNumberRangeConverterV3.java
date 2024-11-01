import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ConsecutiveNumberRangeConverterV3 {

  // Method to convert consecutive numbers into ranges if 3 or more consecutive numbers are found
  public static String convertToRange(LinkedList<String> inputList) {

    // List to hold the result
    List<String> result = new ArrayList<>();

    // List to temporarily store numbers for range processing
    List<Integer> currentNumbers = new ArrayList<>();

    // Process each part from the LinkedList
    for (String part : inputList) {
      // Try to parse the current part as a number
      try {
        int number = Integer.parseInt(part.trim());
        currentNumbers.add(number);
      } catch (NumberFormatException e) {
        // If the part is not a number, check if we have a sequence of numbers to process
        processNumbers(result, currentNumbers);
        currentNumbers.clear();  // Clear the numbers list for the next batch
        result.add(part.trim()); // Add the non-numeric part (e.g., *, **, ***)
      }
    }

    // Handle any remaining numbers after the loop
    processNumbers(result, currentNumbers);

    // Join the result list with commas
    return String.join(",", result);
  }

  // Helper method to process numbers and convert to ranges if needed
  private static void processNumbers(List<String> result, List<Integer> currentNumbers) {
    // If there are 3 or more consecutive numbers, create a range
    if (currentNumbers.size() >= 3) {
      // Extract consecutive numbers into ranges
      // Keep track of the current index in the numbers list
      int numIndex = 0;
      int start = currentNumbers.get(numIndex);
      int prev = start;
      int count = 1; // Keep track of how many consecutive numbers we have

      while (numIndex + 1 < currentNumbers.size() && currentNumbers.get(numIndex + 1) == prev + 1) {
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

  // Main method to test the application
  public static void main(String[] args) {
    // Create a LinkedList with the input
    //     String input = "1,2,3,4,5,*,**,***,8,9,10,90,1120";
    //    String input = "1,3,5";
    //    String input = "1,2,3,4,5,";
    LinkedList<String> inputList = new LinkedList<>();
    inputList.add("1");
    inputList.add("2");
//    inputList.add("1");
//    inputList.add("2");
//    inputList.add("3");
//    inputList.add("*");
//    inputList.add("7");
//    inputList.add("8");
//    inputList.add("9");
//    inputList.add("10");
//    inputList.add("15");
//    inputList.add("20");

    //1,1,1,2,1,2,3,4,5,

    inputList.add("1");
    inputList.add("2");
    inputList.add("3");
    inputList.add("4");
    inputList.add("99");
    inputList.add("5");
    inputList.add("*");
    inputList.add("**");
    inputList.add("***");
    inputList.add("8");
    inputList.add("9");
    inputList.add("10");
    inputList.add("90");
    inputList.add("1120");

    // Call the conversion method
    String output = convertToRange(inputList);
    System.out.println("Output: " + output);
  }
}
