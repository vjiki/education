import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class StackMaxEffectiveV0 {
  private final List<Integer> stack = new ArrayList<>();
  private final TreeSet<Integer> sortedStack = new TreeSet<>();

  public void push(int x) {
    stack.add(x);
    sortedStack.add(x);
  }

  public Integer pop() {
    if(stack.isEmpty()) {
      return null;
    } else {
      Integer removed = stack.remove(stack.size() - 1);
      if(!stack.contains(removed)) {
        sortedStack.remove(removed);
      }
      return removed;
    }
  }

  public String get_max() {
    if (stack.isEmpty()) {
      return "None";
    } else {
      Integer max = sortedStack.last();
      return max.toString();
    }
  }

  public static void main(String[] args) throws IOException {
    StackMaxEffectiveV0 stackMax = new StackMaxEffectiveV0();
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      StringBuilder sb = new StringBuilder();
      int n = readInt(reader);
      for (int i = 0; i < n; i++) {
        String cmd = reader.readLine();
        if (cmd.contains("get_max")) {
          sb.append(stackMax.get_max()).append("\n");
        } else if (cmd.contains("push")) {
          String[] values = cmd.split(" ");
          stackMax.push(Integer.parseInt(values[1]));
        } else if (cmd.contains("pop")) {

          if (stackMax.pop() == null) {
            sb.append("error").append("\n");
          }
        }
      }
      System.out.println(sb);
    }
  }

  private static int readInt(BufferedReader reader) throws IOException {
    return Integer.parseInt(reader.readLine());
  }

  //  public Integer pop() {
  //    if (lastIndex == 0) {
  //      return null;
  //    } else {
  //      int removed = stack[stack.length-1];
  //      lastIndex--;
  //      int newMax = Integer.MIN_VALUE;
  ////      boolean contains = false;
  //      for (int i = 0; i <= lastIndex; i++) {
  ////        if (i == removed) {
  ////          contains = true;
  ////          System.out.println(contains);
  ////        }
  //        if (stack[i] > newMax) {
  //          newMax = stack[i];
  //        }
  ////        System.out.println(stack[i]);
  ////        System.out.println(lastIndex);
  //      }
  ////      System.out.println(newMax);
  ////      max = contains ? max : newMax;
  //      max = newMax;
  //      return removed;
  //    }
  //  }
}
