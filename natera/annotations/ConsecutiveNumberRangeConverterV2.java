import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ConsecutiveNumberRangeConverterV2 {

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
    if (currentNumbers.size() >= 3) {
      // If there are 3 or more consecutive numbers, create a range
      result.add(currentNumbers.get(0) + "-" + currentNumbers.get(currentNumbers.size() - 1));
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
    LinkedList<String> inputList = new LinkedList<>();
    inputList.add("1");
    inputList.add("3");
    inputList.add("5");
    inputList.add("*");
    inputList.add("7");
    inputList.add("8");
    inputList.add("9");
    inputList.add("10");
    inputList.add("15");
    inputList.add("20");

    // Call the conversion method
    String output = convertToRange(inputList);
    System.out.println("Output: " + output);
  }
}
