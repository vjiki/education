import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MixedContentRangeConverterV0 {

  // Method to convert comma-separated numbers into a range format and handle mixed content
  public static String convertToRange(String input) {
    // Use a regular expression to find all numbers in the input string
    Pattern pattern = Pattern.compile("\\d+");
    Matcher matcher = pattern.matcher(input);

    if(input.isBlank()) {
      return input;
    }

    // List to hold the numbers extracted from the input
    List<Integer> numbers = new ArrayList<>();
    while (matcher.find()) {
      numbers.add(Integer.parseInt(matcher.group()));
    }

    if (numbers.isEmpty() || numbers.size() <= 2) {
      return input;
    }

    // List to hold the final result after replacing number sequences with ranges
    List<String> result = new LinkedList<>();

    // Split the input on commas or spaces while keeping other parts (e.g., text)
    String[] parts = input.split("[ ,]+");

    // Keep track of the current index in the numbers list
    int numIndex = 0;

    // Process each part of the input
    for (String part : parts) {
      // If the part contains a number, process it
      if (part.matches(".*\\d.*")) {
        // Extract consecutive numbers into ranges
        if (numIndex >= numbers.size()) {
          continue;
        }
        int start = numbers.get(numIndex);
        int prev = start;
        int count = 1; // Keep track of how many consecutive numbers we have

        while (numIndex + 1 < numbers.size() && numbers.get(numIndex + 1) == prev + 1) {
          prev = numbers.get(++numIndex);
          count++;
        }

        if (count >= 3) {
          // If there are 3 or more consecutive numbers, make it a range
          result.add(start + "-" + prev);
        } else {
          // If there are fewer than 3 consecutive numbers, add them individually
          for (int i = start; i <= prev; i++) {
            result.add(String.valueOf(i));
          }
        }
        numIndex++;
      } else {
        // If the part does not contain a number, leave it as is
        result.add(part);
      }
    }

//    StringBuilder sb = new StringBuilder();
//    for (String part : result) {
//      if (part.matches(".*\\d.*")) {
//        if(part.matches("[0-9]")) {
//          sb.append(part).append(",");
//        } else {
//          sb.append(part).append(" ");
//        }
//      } else {
//        sb.append(part).append(" ");
//      }
//    }

    // Join the result with commas
//    return String.join(",", result);
    return String.join(",", result);
  }

  // Main method to test the program
  public static void main(String[] args) {
//    String input = "1,2,3,hello,7,8,9,10,world,12,14";
//    String input = "1,3,5";
    String input = "1,2,3,4,5,*,**,***,8,9,10,90,1120";
//    String input = "1 sasas 5,6,7,8,9";
    String output = convertToRange(input);
    System.out.println("Input: " + input);
    System.out.println("Output: " + output);
  }
}
