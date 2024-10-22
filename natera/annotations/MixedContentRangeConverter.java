import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MixedContentRangeConverter {

  // Method to convert comma- or space-separated numbers into ranges and handle mixed content
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
    List<String> result = new ArrayList<>();

    // Split the input on both commas and spaces while keeping other parts (e.g., text)
    String[] parts = input.split("[,\\s]+");  // Split on both commas and spaces

    // Keep track of the current index in the numbers list
    int numIndex = 0;

    // Temporarily hold text chunks to be joined with spaces
    List<String> textChunk = new ArrayList<>();

    // Flag to track if a number was just added, to control if we need a space before the text
    boolean lastWasNumber = false;

    // Process each part of the input
    for (String part : parts) {
      // If the part contains a number, process it
      if (part.matches(".*\\d.*")) {
        // Before processing numbers, check if there's any text to flush
        if (!textChunk.isEmpty()) {
          result.add(String.join(" ", textChunk));
          textChunk.clear();  // Clear the text chunk list
        }

        if (numIndex >= numbers.size()) {
          continue;
        }
        // Extract consecutive numbers into ranges
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
        lastWasNumber = true;  // Mark that the last element was a number/range
      } else {
        // If the part is text, check if it should be separated by space after a number
        if (lastWasNumber) {
          result.add("");  // Add a space separator between numbers and text
        }
        textChunk.add(part);  // Collect text for eventual joining by spaces
        lastWasNumber = false;
      }
    }

    // After loop, flush any remaining text to the result
    if (!textChunk.isEmpty()) {
      result.add(String.join(" ", textChunk));
    }

    // Join numeric ranges and single numbers with commas, and use space when switching from numbers to text
    return String.join(",", result).replaceAll(", ,", " ");
  }

  // Main method to test the program
  public static void main(String[] args) {
    // Test input where numbers and text can be separated by commas or spaces
//    String input = "1 2 3 hello world 7 8 9 10,how,are,you,12 14";
    //    String input = "1,2,3,hello,7,8,9,10,world,12,14";
//    String input = "1,3,5";
//    String input = "1,2,3,4,5,";
    String input = "1 sasas 5,6,7,8,9";
    String output = convertToRange(input);
    System.out.println("Input: " + input);
    System.out.println("Output: " + output);
  }
}
