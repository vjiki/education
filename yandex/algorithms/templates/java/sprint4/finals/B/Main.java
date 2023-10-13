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
  private static final String GET = "get";
  private static final String PUT = "put";
  private static final String DELETE = "delete";
  private static final String NONE = "None";

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      StringBuilder sb = new StringBuilder();
      int commandsSize = readInt(reader);
      HashTable hashTable = new HashTable(1000000);

      for (int i = 0; i < commandsSize; i++) {
        String cmd = reader.readLine();
        if (cmd.contains(GET)) {
          String[] values = cmd.split(" ");
          String result = hashTable.get(values[1]);
          if(result == null) {
            sb.append(NONE).append("\n");
          } else {
            sb.append(result).append("\n");
          }
        } else if (cmd.contains(PUT)) {
          String[] values = cmd.split(" ");
          boolean result = hashTable.put(values[1], values[2]);
          if(!result) {
            sb.append(NONE).append("\n");
          }
        } else if (cmd.contains(DELETE)) {
          String[] values = cmd.split(" ");
          String val = hashTable.delete(values[1]);
          if (val == null) {
            sb.append(NONE).append("\n");
          } else {
            sb.append(val).append("\n");
          }
        } else if (SIZE.equals(cmd)) {
          sb.append(hashTable.size()).append("\n");
        }
      }
      System.out.println(sb);
    }
  }

  private static int readInt(BufferedReader reader) throws IOException {
    return Integer.parseInt(reader.readLine());
  }
}

class HashTable {

  private final int max_size;
  private final Pair[] hashTable;
  private int size;

  public HashTable(int maxSize) {
    max_size = maxSize;
    hashTable = new Pair[max_size];
    size = 0;
  }

  public String delete(String key) {
    int bucket = bucket(Integer.parseInt(key));
    if (hashTable[bucket] != null) {
      if (hashTable[bucket].key.equals(key)) {
        String value = hashTable[bucket].value;
        hashTable[bucket] = new Pair("deleted", null);
        size--;
        return value;
      } else {
        int i = bucket;
        while(hashTable[i] != null) {
          if (hashTable[i].key.equals(key)) {
            String value = hashTable[i].value;
            hashTable[i] = new Pair("deleted", null);
            size--;
            return value;
          }
          i++;
          if (i > max_size - 1) {
            i = 0;
          }
        }
      }
    }

    return null;
  }

  public boolean put(String key, String value) {

    int bucket = bucket(Integer.parseInt(key));
    if (hashTable[bucket] == null) {
      hashTable[bucket] = new Pair(key, value);
    } else {
      int i = bucket;
      while(hashTable[i] != null) {
        if (hashTable[i].key.equals(key)) {
          hashTable[i].setValue(value);
          return true;
        }
        i++;
        if (i > max_size - 1) {
          i = 0;
        }
      }
      hashTable[i] = new Pair(key, value);
    }

    size++;

    return true;
  }

  public String get(String key) {

    int bucket = bucket(Integer.parseInt(key));
    if (hashTable[bucket] != null) {
      if (hashTable[bucket].key.equals(key)) {
        return hashTable[bucket].value;
      } else {
        int i = bucket;
        while(hashTable[i] != null) {
          if (hashTable[i].key.equals(key)) {
            return hashTable[i].value;
          }
          i++;
          if (i > max_size - 1) {
            i = 0;
          }
        }
      }
    }

    return null;
  }

  public Integer size() {
    return size;
  }


  private int bucket(Integer key) {
    return Math.abs(hash(key) % max_size);
  }


  private int hash(Integer key) {
    return key.hashCode();
  }

  class Pair {
    private final String key;
    private String value;

    public Pair(String key, String value) {
      this.key = key;
      this.value = value;
    }

    public String getKey() {
      return key;
    }

    public String getValue() {
      return value;
    }

    public void setValue(String value) {
      this.value = value;
    }
  }
}
