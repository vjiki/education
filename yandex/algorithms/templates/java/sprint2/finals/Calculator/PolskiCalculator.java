// https://contest.yandex.ru/contest/22781/run-report/90411231/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PolskiCalculator {


  private final static String ADDITION = "+";
  private final static String SUBTRACTION = "-";
  private final static String MULTIPLICATION = "*";
  private final static String DIVISION = "/";

  private static final int[] stack = new int[50000];
  private static int lastIndex = 0;


  public static void push(int x) {
    stack[lastIndex] = x;
    lastIndex++;
  }

  public static int pop() {
      int removed = stack[lastIndex - 1];
      lastIndex--;
      return removed;
  }

  static void addition() {
    int last = pop();
    int oneBeforeLast = pop();
    push(oneBeforeLast + last);
  }

  static void subtraction() {
    int last = pop();
    int oneBeforeLast = pop();
    push(oneBeforeLast - last);
  }

  static void multiplication() {
    int last = pop();
    int oneBeforeLast = pop();
    push(oneBeforeLast * last);
  }

  static void division() {
    int last = pop();
    int oneBeforeLast = pop();
    if (oneBeforeLast < 0 && last > 0 && (oneBeforeLast % last) != 0) {
      if (-oneBeforeLast < last) {
        push(-1);
      } else {
        push(oneBeforeLast/last - 1);
      }
    } else if (oneBeforeLast > 0 && last < 0 && (oneBeforeLast % last) != 0) {
      if (oneBeforeLast < -last) {
        push(-1);
      } else {
        push(oneBeforeLast/last - 1);
      }
    } else {
      push(oneBeforeLast / last);
    }
  }

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      String cmd = reader.readLine();
      String[] values = cmd.split(" ");
      for (String value : values) {
        if(ADDITION.equals(value)) {
          addition();
        } else if (SUBTRACTION.equals(value)) {
          subtraction();
        } else if (MULTIPLICATION.equals(value)) {
          multiplication();
        } else if (DIVISION.equals(value)) {
          division();
        } else {
          int n = Integer.parseInt(value);
          push(n);
        }
      }
      System.out.println(pop());
    }
  }
}
