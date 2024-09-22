public class MyStack {
  private final int[] stack;
  private int lastIndex = 0;

  public MyStack(int capacity) {
    stack = new int[capacity];
    lastIndex = 0;
  }

  public void push(int x) {
    stack[lastIndex] = x;
    lastIndex++;
  }

  public int pop() {
    int removed = stack[lastIndex - 1];
    lastIndex--;
    return removed;
  }
}
