import java.util.ArrayList;
import java.util.List;

public class ConsecutiveNumberRangeConverter {

  // Method to convert consecutive numbers into ranges if 3 or more consecutive numbers are found
  public static String convertToRange(String input) {
    // Split the input on commas
    String[] parts = input.split(",");

    // List to hold the result
    List<String> result = new ArrayList<>();

    // List to temporarily store numbers for range processing
    List<Integer> currentNumbers = new ArrayList<>();

    for (String part : parts) {
      // Try to parse the current part as a number
      try {
        int number = Integer.parseInt(part.trim());
        currentNumbers.add(number);
      } catch (NumberFormatException e) {
        // If the part is not a number, check if we have a sequence of numbers to process
        if (currentNumbers.size() >= 3) {
          result.add(currentNumbers.get(0) + "-" + currentNumbers.get(currentNumbers.size() - 1));
        } else {
          for (Integer num : currentNumbers) {
            result.add(num.toString());
          }
        }
        currentNumbers.clear();  // Clear the numbers list for the next batch
        result.add(part.trim()); // Add the non-numeric part (e.g., *, **, ***)
      }
    }

    // Handle any remaining numbers after the loop
    if (currentNumbers.size() >= 3) {
      result.add(currentNumbers.get(0) + "-" + currentNumbers.get(currentNumbers.size() - 1));
    } else {
      for (Integer num : currentNumbers) {
        result.add(num.toString());
      }
    }

    // Join the result list with commas
    return String.join(",", result);
  }

  // Main method to test the application
  public static void main(String[] args) {
    String input = "1,2,3,4,5,*,**,***,8,9,10,90,1120";
    String output = convertToRange(input);
    System.out.println("Input: " + input);
    System.out.println("Output: " + output);
  }
}
