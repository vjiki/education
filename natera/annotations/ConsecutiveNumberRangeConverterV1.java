import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ConsecutiveNumberRangeConverterV1 {

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
        if (currentNumbers.size() >= 3) {
          // Create a range if there are 3 or more consecutive numbers
          result.add(currentNumbers.get(0) + "-" + currentNumbers.get(currentNumbers.size() - 1));
        } else {
          // Otherwise, add numbers individually
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
    // Create a LinkedList with the input
    //    String input = "1,2,3,hello,7,8,9,10,world,12,14";
//    String input = "1,3,5";
//    String input = "1,2,3,4,5,*,**,***,8,9,10,90,1120";
//    String input = "1 sasas 5,6,7,8,9";
    LinkedList<String> inputList = new LinkedList<>();
    inputList.add("1");
    inputList.add("3");
    inputList.add("5");
//    inputList.add("4");
//    inputList.add("5");
//    inputList.add("*");
//    inputList.add("**");
//    inputList.add("***");
//    inputList.add("8");
//    inputList.add("9");
//    inputList.add("10");
//    inputList.add("90");
//    inputList.add("1120");

    // Call the conversion method
    String output = convertToRange(inputList);
    System.out.println("Output: " + output);
  }
}
