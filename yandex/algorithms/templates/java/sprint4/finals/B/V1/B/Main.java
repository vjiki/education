// https://contest.yandex.ru/contest/24414/run-report/92874475/

/*
-- ПРИНЦИП РАБОТЫ --
Я реализовал хэш таблицу на основе массив с фиксированным количеством элементов методом открытой адресации из теории
контекста:
 - при добавление элемента мы ищем сначала бакет. Если он свободен то добавляем если нет то либо обновляем элемент с новым значением
 либо идем дальше и ищем либо свободную следующую ячуйку либо нужный ключ и обновляем значение у него
 - при поиске элемента ищем бакет и потом ищем ключ в бакете или в следующих ячейках пока не дойдем до пустой ячейки
 - при удалении элемента помечаем ячейку как deleted


-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Мы знаем бакет и можем ходить по массиву в пока не найдем нужное значение. Это позволяет на быстро искать элементы.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Добавление в таблицу стоит O(1 + i), так как мы заранее знаем в какой индекс массива будем добавлять элемент.
i это константа которая в худшем случае не может превысить 10 в 5 степени.
Худшую сложность вставки O(N), средняя O(1)

Такая же сложность у получения значения и у удаления.

https://en.wikipedia.org/wiki/Hash_table

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Амортизационная сложность O(1) то есть мы не выделяем дополнительной памяти для хранения каждого элемента.
Таблица будет занимать в памяти всегда фиксированное значение.
Если максимальный размер таблицы M, то в памяти он будет занимать константу O(M).
В целом мы могли тратим больше памяти из за реализации на основе открытой адресации
Для нашего случая мы могли бы хранить только пару int int в лучшем случае но для реализации нам
приходиться хранить int в типе string. Для этого выделяется больше памяти. Но оно всегда константное в обоих случаях.

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
  private static final int HASH_TABLE_MAX_SIZE = 1000000; // при 100 000 не прохожу по времени, там видимо поиск
                                                          // без рехэширования долго идет так как все ячейки заняты

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      StringBuilder sb = new StringBuilder();
      int commandsSize = readInt(reader);
      HashTable hashTable = new HashTable(HASH_TABLE_MAX_SIZE);

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
