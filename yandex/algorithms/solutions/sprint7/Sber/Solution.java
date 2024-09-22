public class Solution {


  int getValue(char c) {
    return switch (c) {
      case 'I' -> 1;
      case 'V' -> 5;
      case 'X' -> 10;
      case 'L' -> 50;
      case 'C' -> 100;
      case 'D' -> 500;
      case 'M' -> 1000;
      default -> 0;
    };
  }


  public int romanToInt(String s) {

    char[] arr = s.toCharArray();

    int sum = 0;

    int previousMax = 0;

    for (int i = arr.length-1; i >= 0; i--) {
      int next = getValue(arr[i]);
      if (next >= previousMax) {
        sum += next;
      } else {
        sum -= next;
      }
      previousMax = next;
    }
    return sum;
  }

  // I обозначает 1, V обозначает 5, X — 10, L — 50, C — 100, D — 500, M — 1000.

  public static void main(String[] args) {
    Solution solution = new Solution();

    String s1 = "III";

    if (solution.romanToInt(s1) != 3) {
      extracted(s1);
    }

    String s2 = "LVIII";

    if (solution.romanToInt(s2) != 58) {
      extracted(s2);
    }

    String s3 = "MMIX";
    if (solution.romanToInt(s3) != 2009) {
      extracted(s3);
    }
  }

  private static void extracted(String s1) {
    System.err.println("Ошибка в " + s1);
  }
}