import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyQueueSized {

  private static final String PEEK = "peek";
  private static final String SIZE = "size";
  private static final String POP = "pop";
  private static final String ERROR = "error";
  private static final String NONE = "None";
  private static final String PUSH = "push";

  private final int max_size;
  private Integer[] queue;
  private int size;
  private int head;
  private int tail;

  public MyQueueSized(int maxSize) {
    max_size = maxSize;
    queue = new Integer[max_size];
    size = 0;
    head = 0;
    tail = 0;
  }

  public Integer push(int x) {
    if (size >= max_size) {
      return null;
    }
    queue[tail] = x;
    tail = (tail +1) % max_size;
    size++;

    return x;
  }

  public String pop() {
    if (size == 0) {
      return NONE;
    }
    Integer headValue = queue[head];
    queue[head] = null;
    head = (head +1) % max_size;
    size--;

    return String.valueOf(headValue);
  }

  public String peek() {
    if (size == 0) {
      return NONE;
    }

    return String.valueOf(queue[head % max_size]);
  }

  public Integer size() {
    return size;
  }

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      StringBuilder sb = new StringBuilder();
      int commandsSize = readInt(reader);
      int maxQueueSize = readInt(reader);
      MyQueueSized myQueueSized = new MyQueueSized(maxQueueSize);

      for (int i = 0; i < commandsSize; i++) {
        String cmd = reader.readLine();
        if (PEEK.equals(cmd)) {
          sb.append(myQueueSized.peek()).append("\n");
        } else if (cmd.contains(PUSH)) {
          String[] values = cmd.split(" ");
          Integer result = myQueueSized.push(Integer.parseInt(values[1]));
          if(result == null) {
            sb.append(ERROR).append("\n");
          }
        } else if (POP.equals(cmd)) {
            sb.append(myQueueSized.pop()).append("\n");
        } else if (SIZE.equals(cmd)) {
          sb.append(myQueueSized.size()).append("\n");
        }
      }
      System.out.println(sb);
    }
  }

  private static int readInt(BufferedReader reader) throws IOException {
    return Integer.parseInt(reader.readLine());
  }

}
