import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StackMaxEffective {

  private static final String GET_MAX = "get_max";
  private static final String POP = "pop";
  private static final String ERROR = "error";
  private static final String NONE = "None";
  private static final String PUSH = "push";
  private final int[] stack = new int[100000];
  private int lastIndex = 0;
  private int max = Integer.MIN_VALUE;

  public static void main(String[] args) throws IOException {
    StackMaxEffective stackMax = new StackMaxEffective();
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      StringBuilder sb = new StringBuilder();
      int n = readInt(reader);
      for (int i = 0; i < n; i++) {
        String cmd = reader.readLine();
        if (GET_MAX.equals(cmd)) {
          sb.append(stackMax.get_max()).append("\n");
        } else if (cmd.contains(PUSH)) {
          String[] values = cmd.split(" ");
          stackMax.push(Integer.parseInt(values[1]));
        } else if (POP.equals(cmd)) {
          if (stackMax.pop() == null) {
            sb.append(ERROR).append("\n");
          }
        }
      }
      System.out.println(sb);
    }
  }

  private static int readInt(BufferedReader reader) throws IOException {
    return Integer.parseInt(reader.readLine());
  }

  public void push(int x) {
    stack[lastIndex] = x;
    if (lastIndex == 0) {
      max = x;
    } else if (max < x) {
      max = x;
    }
    lastIndex++;
  }

  public Integer pop() {
    if (lastIndex == 0) {
      return null;
    } else {
      int removed = stack[stack.length - 1];
      lastIndex--;
      int newMax = Integer.MIN_VALUE;
      for (int i = 0; i <= lastIndex - 1; i++) {
        if (stack[i] > newMax) {
          newMax = stack[i];
        }
      }
      max = newMax;
      return removed;
    }
  }

  public String get_max() {
    if (lastIndex == 0) {
      return NONE;
    } else {
      return String.valueOf(max);
    }
  }
}
