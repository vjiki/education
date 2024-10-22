import java.util.ArrayList;
import java.util.List;

public class NumberRangeConverter {

  // Method to convert comma-separated numbers into a range format
  public static String convertToRange(String input) {
    // Split the input string by commas to extract the numbers
    String[] numbers = input.split(",");
    List<String> result = new ArrayList<>();

    // Convert the extracted strings to integers for easier manipulation
    List<Integer> nums = new ArrayList<>();
    for (String num : numbers) {
      nums.add(Integer.parseInt(num.trim()));
    }

    // Traverse the numbers and group consecutive numbers into ranges
    int start = nums.get(0);
    int prev = start;

    for (int i = 1; i < nums.size(); i++) {
      if (nums.get(i) == prev + 1) {
        // Continue the range if the current number is consecutive
        prev = nums.get(i);
      } else {
        // End the current range and add it to the result
        if (start == prev) {
          result.add(String.valueOf(start));
        } else {
          result.add(start + "-" + prev);
        }
        // Start a new range
        start = nums.get(i);
        prev = start;
      }
    }

    // Handle the final range
    if (start == prev) {
      result.add(String.valueOf(start));
    } else {
      result.add(start + "-" + prev);
    }

    // Join the result list into a string with commas
    return String.join(",", result);
  }

  // Main method to test the program
  public static void main(String[] args) {
    String input = "1,2,3,7,8,10";
    String output = convertToRange(input);
    System.out.println("Input: " + input);
    System.out.println("Output: " + output);
  }
}
