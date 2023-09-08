import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StackMax {

  private final List<Integer> stack = new ArrayList<>();

  public void push(int x) {
    stack.add(x);
  }

  public Integer pop() {
    if(stack.isEmpty()) {
      System.out.println("error");
      return null;
    } else {
      Integer last = stack.get(stack.size()-1);
      stack.remove(stack.size() - 1);
      return last;
    }
  }

  public Integer get_max() {
    Optional<Integer> optionalMax =  stack.stream().max(Integer::compareTo);
    if (optionalMax.isEmpty()) {
      System.out.println("None");
      return null;
    } else {
      System.out.println(optionalMax.get());
      return optionalMax.get();
    }
  }

  public static void main(String[] args) throws IOException {
    StackMax stackMax = new StackMax();
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      int n = readInt(reader);
      for (int i = 0; i < n; i++) {
        String cmd = reader.readLine();
        if (cmd.contains("get_max")) {
          stackMax.get_max();
        } else if (cmd.contains("push")) {
          String[] values = cmd.split(" ");
          stackMax.push(Integer.parseInt(values[1]));
        } else if (cmd.contains("pop")) {
          stackMax.pop();
        }
      }
    }
  }

  private static int readInt(BufferedReader reader) throws IOException {
    return Integer.parseInt(reader.readLine());
  }
}
