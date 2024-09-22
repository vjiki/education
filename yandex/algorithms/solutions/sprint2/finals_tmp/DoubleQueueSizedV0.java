import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class DoubleQueueSizedV0 {

  private static final String SIZE = "size";
  private static final String POP_FRONT = "pop_front";
  private static final String POP_BACK = "pop_back";
  private static final String ERROR = "error";
  private static final String PUSH_BACK = "push_back";
  private static final String PUSH_FRONT = "push_front";

  private final int max_size;
  private final Integer[] queueFront;
  private final Integer[] queueBack;
  private int size;
  private int headFront;
  private int tailFront;
  private int headBack;
  private int tailBack;

  public DoubleQueueSizedV0(int maxSize) {
    max_size = maxSize;
    queueFront = new Integer[max_size];
    queueBack = new Integer[max_size];
    size = 0;
    headFront = 0;
    tailFront = 0;
    headBack = 0;
    tailBack = 0;
  }

  // 1 1 1
  public Integer push_back(int x) {
    if (size >= max_size) {
      return null;
    }
    queueBack[tailBack] = x;
    tailBack = (tailBack +1) % max_size;
    size++;

   // Arrays.stream(queue).forEach(System.out::println);
    return x;
  }

  public Integer push_front(int x) {
    if (size >= max_size) {
      return null;
    }
//    System.out.println(headFront);
//    System.out.println(x);
    queueFront[headFront] = x;
    headFront = (headFront +1) % max_size;
    size++;

    return x;
  }

  public String pop_back() {
    if (size == 0) {
      return ERROR;
    }
    Integer tailValue;
    int newTailIndex = max_size - (max_size-(tailBack-1));
    if (tailBack > 0 &&  queueBack[newTailIndex] != null) {
      tailValue = queueBack[newTailIndex];
      queueBack[newTailIndex] = null;
      tailBack = max_size - (max_size-(tailBack-1) - 1);
    }
    else {
      tailValue = queueFront[tailFront];
      queueFront[tailFront] = null;
      tailFront = (tailFront +1) % max_size;
    }
    size--;
    return String.valueOf(tailValue);
  }


  // можно индекс последнего элемента запоминать
  public String pop_front() {
    if (size == 0) {
      return ERROR;
    }
    Integer headValue;
//    System.out.println(headFront);
    if (headFront > 0 &&  queueFront[headFront-1] != null) {
//      System.out.println(headFront);
//      System.out.println(Arrays.toString(queueFront));
      headValue = queueFront[headFront - 1];
      queueFront[headFront - 1] = null;
      headFront -= 1;
    } else {
      headValue = queueBack[headBack];
      queueBack[headBack] = null;
      headBack = (headBack +1) % max_size;
    }
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
      DoubleQueueSizedV0 doubleQueueSized = new DoubleQueueSizedV0(maxDQueueSize);

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

// 842
//576
//-236
//123
//-853
//-711
//-7
//347
//740
//840
//-767
//540
//-215
//-293
//error
//error
//-575
//error
//error
//error
//92
//-356