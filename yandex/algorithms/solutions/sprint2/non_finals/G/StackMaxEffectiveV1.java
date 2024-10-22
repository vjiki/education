import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class StackMaxEffectiveV1 {

  private static final String GET_MAX = "get_max";
  private static final String POP = "pop";
  private static final String ERROR = "error";
  private static final String NONE = "None";
  private static final String PUSH = "push";
  private final List<Integer> stack = new LinkedList<>();
  private Integer max = Integer.MIN_VALUE;

  public static void main(String[] args) throws IOException {
    StackMaxEffectiveV1 stackMax = new StackMaxEffectiveV1();
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
    stack.add(x);
    if (max < x) {
      max = x;
    }
  }

  public Integer pop() {
    if (stack.isEmpty()) {
      return null;
    } else {
      Integer removed = stack.remove(stack.size() - 1);
      int newMax = Integer.MIN_VALUE;
      boolean contains = false;
      for (Integer i : stack) {
        if (i.equals(removed)) {
          contains = true;
        }
        if (i > newMax) {
          newMax = i;
        }
      }
      max = contains ? max : newMax;
      return removed;
    }
  }

  public String get_max() {
    if (stack.isEmpty()) {
      return NONE;
    } else {
      return max.toString();
    }
  }
}
