import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PhoneButtons {

  static char[] getSymbols(int digit) {
    switch (digit) {
      case 2: {
        return new char[]{'a', 'b', 'c'};
      }
      case 3: {
        return new char[]{'d', 'e', 'f'};
      }
      case 4: {
        return new char[]{'g', 'h', 'i'};
      }
      case 5: {
        return new char[]{'j', 'k', 'l'};
      }
      case 6: {
        return new char[]{'m', 'n', 'o'};
      }
      case 7: {
        return new char[]{'p', 'q', 'r', 's'};
      }
      case 8: {
        return new char[]{'t', 'u', 'v'};
      }
      case 9: {
        return new char[]{'w', 'x', 'y', 'z'};
      }
      default: {
        return new char[0];
      }
    }
  }

  public static String symbolVariants(String digits) {
    if (digits == null || digits.isEmpty()) {
      return "";
    }
    StringBuilder sbResult = new StringBuilder();
    getVariants(sbResult, new StringBuilder(), digits, 0);

    sbResult.deleteCharAt(sbResult.length()-1);
    return sbResult.toString();
  }

  static void getVariants(StringBuilder sbResult, StringBuilder prefix, String digits, int index) {
    if (prefix.length() == digits.length()) {
        sbResult.append(prefix).append(" ");
        return;
    }

    char[] symbols = getSymbols(Integer.parseInt(String.valueOf(digits.charAt(index))));

    for (char c : symbols) {
      prefix.append(c);
      getVariants(sbResult, prefix, digits, index + 1);
      prefix.deleteCharAt(prefix.length() - 1);
    }
  }

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      String digits = reader.readLine();
      String variants = symbolVariants(digits);
      System.out.println(variants);
    }
  }
}
