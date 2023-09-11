// https://contest.yandex.ru/contest/22781/run-report/90377075/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 0 0 0 0 0 0 0 0 0 0
// 1 0 0 0 0 0 0 0 0 0
// tail -> 1
// head -> 0
// 1 2
// head -> 0
// tail -> 2
// 1 2 0 0 0 0 0 0 0 0
//

public class DoubleQueueSized {

  private static final String SIZE = "size";
  private static final String POP_FRONT = "pop_front";
  private static final String POP_BACK = "pop_back";
  private static final String ERROR = "error";
  private static final String PUSH_BACK = "push_back";
  private static final String PUSH_FRONT = "push_front";

  private final int max_size;
  private final Integer[] queue;
  private int size;
  private int head;
  private int tail;

  public DoubleQueueSized(int maxSize) {
    max_size = maxSize;
    queue = new Integer[max_size];
    size = 0;
    head = 0;
    tail = 0;
  }

  public Integer push_back(int x) {
    if (size >= max_size) {
      return null;
    }
    queue[tail] = x;
    tail = (tail +1) % max_size;
    size++;

   // Arrays.stream(queue).forEach(System.out::println);
    return x;
  }

  public Integer push_front(int x) {
    if (size >= max_size) {
      return null;
    }
    if(head == tail) {
      push_back(x);
    } else if (head == 0) {
      head = max_size - 1;
      queue[head] = x;
      size++;
    } else {
      head = (head - 1) % max_size;
      queue[head] = x;
      size++;
    }

   // Arrays.stream(queue).forEach(System.out::println);
    return x;
  }

  public String pop_back() {
    if (size == 0) {
      return ERROR;
    }
    int index;
    if (tail == 0) {
      index = max_size - 1;
    } else {
      index = tail - 1;
    }
    Integer tailValue = queue[index];
    queue[index] = null;
    if (tail == 0) {
      tail = index;
    } else {
      tail = (tail - 1) % max_size;
    }

    //System.out.println(tail);
    size--;

    //Arrays.stream(queue).forEach(System.out::println);
    return String.valueOf(tailValue);
  }

  public String pop_front() {
    if (size == 0) {
      return ERROR;
    }
    Integer headValue = queue[head];
    queue[head] = null;
    head = (head +1) % max_size;
    size--;

  //  Arrays.stream(queue).forEach(System.out::println);
    return String.valueOf(headValue);
  }

  public Integer size() {
    return size;
  }

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      StringBuilder sb = new StringBuilder();
      int commandsSize = readInt(reader);
      int maxDQueueSize = readInt(reader);
      DoubleQueueSized doubleQueueSized = new DoubleQueueSized(maxDQueueSize);

      for (int i = 0; i < commandsSize; i++) {
        String cmd = reader.readLine();
        if (cmd.contains(PUSH_BACK)) {
          String[] values = cmd.split(" ");
          Integer result = doubleQueueSized.push_back(Integer.parseInt(values[1]));
          if(result == null) {
            sb.append(ERROR).append("\n");
          }
        } else if (cmd.contains(PUSH_FRONT)) {
          String[] values = cmd.split(" ");
          Integer result = doubleQueueSized.push_front(Integer.parseInt(values[1]));
          if(result == null) {
            sb.append(ERROR).append("\n");
          }
        } else if (POP_FRONT.equals(cmd)) {
            sb.append(doubleQueueSized.pop_front()).append("\n");
        } else if (POP_BACK.equals(cmd)) {
          sb.append(doubleQueueSized.pop_back()).append("\n");
        } else if (SIZE.equals(cmd)) {
          sb.append(doubleQueueSized.size()).append("\n");
        }
      }
      System.out.println(sb);
    }
  }

  private static int readInt(BufferedReader reader) throws IOException {
    return Integer.parseInt(reader.readLine());
  }

}
