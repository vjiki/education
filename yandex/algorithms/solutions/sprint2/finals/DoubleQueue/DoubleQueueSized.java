public class DoubleQueueSized {

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

  public boolean push_back(Integer x) {
    if (size >= max_size) {
      return false;
    }
    queue[tail] = x;
    tail = (tail +1) % max_size;
    size++;

    return true;
  }

  public boolean push_front(Integer x) {
    if (size >= max_size) {
      return false;
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

    return true;
  }

  public Integer pop_back() {
    if (size == 0) {
      return null;
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

    size--;

    return tailValue;
  }

  public Integer pop_front() {
    if (size == 0) {
      return null;
    }
    Integer headValue = queue[head];
    queue[head] = null;
    head = (head +1) % max_size;
    size--;

    return headValue;
  }

  public Integer size() {
    return size;
  }
}
