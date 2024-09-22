// https://contest.yandex.ru/contest/22781/run-report/90377075/

/*
-- ПРИНЦИП РАБОТЫ --
Я реализовал двухстороннюю очередь на круговом массиве с фиксированным количеством элементов:
 - при добавлении в начало очереди новый элемент добавляется в свободный индекс слева от самого первого элемента
 - при добавлении в конец очереди новый элемент добавляется в свободный индекс справа от последнего добавленного вконец элемента


-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Мы храним индексы на голову и хвост очереди и это позволяет нам правильно добавлять в начало и конец очереди

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Добавление в очередь стоит O(1), так как мы заранее знаем в какой индекс массива будем добавлять элемент.

Извлечение из очереди стоит O(1), так как мы знаем индекс хвоста и начала чтобы быстро извлечь элемент.

За один проход по входным данным выполняются все операции. То есть сложность O(N) если рассматривать все входные N команд.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Амортизационная сложность O(1) то есть мы не выделяем дополнительной памяти для хранения каждого элемента.
Дек будет занимать в памяти всегда фиксированное значение.
Если максимальный размер дека M, то в памяти он будет занимать константу O(M).

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  private static final String SIZE = "size";
  private static final String POP_FRONT = "pop_front";
  private static final String POP_BACK = "pop_back";
  private static final String ERROR = "error";
  private static final String PUSH_BACK = "push_back";
  private static final String PUSH_FRONT = "push_front";

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
          boolean result = doubleQueueSized.push_back(Integer.parseInt(values[1]));
          if(!result) {
            sb.append(ERROR).append("\n");
          }
        } else if (cmd.contains(PUSH_FRONT)) {
          String[] values = cmd.split(" ");
          boolean result = doubleQueueSized.push_front(Integer.parseInt(values[1]));
          if(!result) {
            sb.append(ERROR).append("\n");
          }
        } else if (POP_FRONT.equals(cmd)) {
          Integer val = doubleQueueSized.pop_front();
          if (val == null) {
            sb.append(ERROR).append("\n");
          } else {
            sb.append(val).append("\n");
          }
        } else if (POP_BACK.equals(cmd)) {
          Integer val = doubleQueueSized.pop_back();
          if (val == null) {
            sb.append(ERROR).append("\n");
          } else {
            sb.append(val).append("\n");
          }
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
