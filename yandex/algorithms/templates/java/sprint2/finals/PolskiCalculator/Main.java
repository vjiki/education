// https://contest.yandex.ru/contest/22781/run-report/90411231/

/*
-- ПРИНЦИП РАБОТЫ --
Для работы с польской нотацией используется стек.
При встрече символа арифметической операции из стека вынимаются два элемента с вершины стека и высчитывается
результат, результат кладётся в стек.
Полный алгоритм описан в задании.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
При работе с польской нотацией важно иметь быстрый доступ к последним добавленным элементам.
При использовании стека мы гарантируем сохранность всех временных данных в нужном порядке при прохождении строки слева направо.
При этом для вычисления мы можем быстро извлечь два верхних элемента и положить результат на вершину стека.
В результате на вершине окажется финальный результат вычислений.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
За один проход по строке выполняются все операции. То есть сложность O(n) если рассматривать целиком всю строку.
Сложность одной операции O(1)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Стек, содержащий n элементов, занимает O(n) памяти, если в худшем случае не будет ни одной арифметической операции
и строка будет содержать только операнды.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


  private final static String ADDITION = "+";
  private final static String SUBTRACTION = "-";
  private final static String MULTIPLICATION = "*";
  private final static String DIVISION = "/";
  private final static int STACK_CAPACITY = 50000;

  static int addition(int oneBeforeLast, int last) {
    return oneBeforeLast + last;
  }

  static int subtraction(int oneBeforeLast, int last) {
    return oneBeforeLast - last;
  }

  static int multiplication(int oneBeforeLast, int last) {
    return oneBeforeLast * last;
  }

  static int division(int oneBeforeLast, int last) {
    return Math.floorDiv(oneBeforeLast,last);
  }

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      String cmd = reader.readLine();
      String[] values = cmd.split(" ");
      MyStack myStack = new MyStack(STACK_CAPACITY);
      for (String value : values) {
        if(ADDITION.equals(value)) {
          int last = myStack.pop();
          int oneBeforeLast = myStack.pop();
          int sum = addition(oneBeforeLast, last);
          myStack.push(sum);
        } else if (SUBTRACTION.equals(value)) {
          int last = myStack.pop();
          int oneBeforeLast = myStack.pop();
          int sub = subtraction(oneBeforeLast, last);
          myStack.push(sub);
        } else if (MULTIPLICATION.equals(value)) {
          int last = myStack.pop();
          int oneBeforeLast = myStack.pop();
          int mul = multiplication(oneBeforeLast, last);
          myStack.push(mul);
        } else if (DIVISION.equals(value)) {
          int last = myStack.pop();
          int oneBeforeLast = myStack.pop();
          int div = division(oneBeforeLast, last);
          myStack.push(div);
        } else {
          int n = Integer.parseInt(value);
          myStack.push(n);
        }
      }
      System.out.println(myStack.pop());
    }
  }
}
