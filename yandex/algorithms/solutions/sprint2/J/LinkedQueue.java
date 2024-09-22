import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class LinkedQueue {
  private static final String GET = "get";
  private static final String SIZE = "size";
  private static final String PUT = "put";
  private static final String ERROR = "error";

  LinkedList<Integer> queue = new LinkedList<>();

  public Integer get() {
    if(queue.isEmpty()) {
      return null;
    }
    return queue.pop();
  }

  public void put(Integer x) {
    queue.addLast(x);
  }

  public Integer size() {
    return queue.size();
  }


  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      StringBuilder sb = new StringBuilder();
      int commandsSize = readInt(reader);
      LinkedQueue linkedQueue = new LinkedQueue();

      for (int i = 0; i < commandsSize; i++) {
        String cmd = reader.readLine();
        if (GET.equals(cmd)) {
          Integer result = linkedQueue.get();
          if(result == null) {
            sb.append(ERROR).append("\n");
          } else {
            sb.append(result).append("\n");
          }
        } else if (cmd.contains(PUT)) {
          String[] values = cmd.split(" ");
          linkedQueue.put(Integer.parseInt(values[1]));
        } else if (SIZE.equals(cmd)) {
          sb.append(linkedQueue.size()).append("\n");
        }
      }
      System.out.println(sb);
    }
  }

  private static int readInt(BufferedReader reader) throws IOException {
    return Integer.parseInt(reader.readLine());
  }

}
