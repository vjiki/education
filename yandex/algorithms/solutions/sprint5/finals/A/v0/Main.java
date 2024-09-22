// https://contest.yandex.ru/contest/23815/run-report/91866573/

/*
-- ПРИНЦИП РАБОТЫ --
Я реализовал эффективную быструю сортировку in place:
 - сортировка работает на основе компаратора: сначала идут студенты с самым большим количеством решённых задач,
 потом с наименьшим штрафом, потом лексикрофичекси по алфавиту
 - сортировка использует рекурсию, выбирает опорный элемент и рекурсивно сортирует относительно опорного элемента


-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Мы не используем дополнительную память на стеке так как всегда сортируем существующий массив

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
В худшем случае сложность O(n2) а средняя O(nlog(n)). Худший случай это когда данные уже отсортированы или содержат
одно и тоже значение.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
O(logn) это дополнительная память которая будет нужна на стеке в худшем случае для работы всего алгоритма.
https://en.wikipedia.org/wiki/Quicksort.

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {

  static String quickSort(Student[] students) {

    StringBuilder sb = new StringBuilder();

    for (Student student : students) {
      sb.append(student.name).append("\n");
    }

    return sb.toString();
  }

  public static List<Student> heapsort(List<Student> array) {
    // Создадим пустую бинарную кучу.
    List<Student> heap = new ArrayList<>();

    // Вставим в неё по одному все элементы массива, сохраняя свойства кучи.
    for (Student item : array) {
      heapAdd(heap, item);   // код для heapAdd можно посмотреть в прошлом уроке
    }

    // Будем извлекать из неё наиболее приоритетные элементы, удаляя их из кучи.
    List<Student> sortedArray = new ArrayList<>();
    while (!heap.isEmpty()) {
      Student max = popMax(heap);
      sortedArray.add(max);
    }
    return sortedArray;
  }

  public static void siftUp(List<Student> heap, int index) {
    if (index == 1) {
      return;
    }

    int parentIndex = index / 2;
    // TODO
    if (heap.get(parentIndex - 1).compareTo(heap.get(index - 1)) > 0) {
      Student temp = heap.get(parentIndex - 1);
      heap.set(parentIndex - 1, heap.get(index - 1));
      heap.set(index - 1, temp);
      siftUp(heap, parentIndex);
    }
  }

  public static void heapAdd(List<Student> heap, Student key) {
    int index = heap.size() + 1;
    heap.add(key);
    siftUp(heap, index);
  }

  public static void siftDown(List<Student> heap, int index) {
    int left = 2 * index + 1;
    int right = 2 * index + 2;

    if (left >= heap.size()) {
      return;
    }

    int indexLargest = left;
    // TODO
    if (right < heap.size() && heap.get(left).compareTo(heap.get(right)) > 0) {
      indexLargest = right;
    }

    // TODO
    if (heap.get(index).compareTo(heap.get(indexLargest)) > 0) {
      Student temp = heap.get(index);
      heap.set(index, heap.get(indexLargest));
      heap.set(indexLargest, temp);
      siftDown(heap, indexLargest);
    }
  }

  public static Student popMax(List<Student> heap) {
    Student result = heap.get(0);
    heap.set(0, heap.get(heap.size() - 1));
    heap.remove(heap.size() - 1);
    siftDown(heap, 0);
    return result;
  }

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      int attendeesSize = readInt(reader);
//      Student[] studentArray = new Student[attendeesSize];
//
//      for (int i = 0; i < attendeesSize; i++) {
//        String[] stInfoLine = reader.readLine().split(" ");
//        studentArray[i] = new Student(stInfoLine[0], Integer.parseInt(stInfoLine[1]), Integer.parseInt(stInfoLine[2]));
//      }
      List<Student> studentList = new ArrayList<>();

      for (int i = 0; i < attendeesSize; i++) {
        String[] stInfoLine = reader.readLine().split(" ");
        studentList.add(new Student(stInfoLine[0], Integer.parseInt(stInfoLine[1]), Integer.parseInt(stInfoLine[2])));
      }

      List<Student> resultStudents = heapsort(studentList);

      StringBuilder sb = new StringBuilder();

      for (Student student : resultStudents) {
        sb.append(student.name).append("\n");
      }

      System.out.println(sb);

    }
  }

  private static int readInt(BufferedReader reader) throws IOException {
    return Integer.parseInt(reader.readLine());
  }
}

class Student implements Comparable<Student> {
  String name;
  int solvedTaskNumber;
  int penaltyScore;

  public Student(String name, int solvedTaskNumber, int penaltyScore) {
    this.name = name;
    this.solvedTaskNumber = solvedTaskNumber;
    this.penaltyScore = penaltyScore;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Student)) {
      return false;
    }
    Student student = (Student) o;
    return solvedTaskNumber == student.solvedTaskNumber && penaltyScore == student.penaltyScore
        && Objects.equals(name, student.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, solvedTaskNumber, penaltyScore);
  }

  @Override
  public int compareTo(Student secondStudent) {
    if (this.equals(secondStudent)) {
      return 0;
    } else {
      int tasksCmp= Integer.compare(secondStudent.solvedTaskNumber, this.solvedTaskNumber);

      if (tasksCmp == 0) {
        int penaltyCmp = Integer.compare(this.penaltyScore, secondStudent.penaltyScore);
        if (penaltyCmp == 0) {
          return this.name.compareTo(secondStudent.name);
        } else {
          return penaltyCmp;
        }
      } else {
        return tasksCmp;
      }
    }
  }
}


class Heap {

  private final int max_size;
  private final Student[] heap;
  private int size;

  public Heap(int maxSize) {
    max_size = maxSize;
    heap = new Student[max_size];
    size = 0;
  }

  public String delete(String key) {

    return null;
  }

  public boolean put(String key, String value) {

    size++;

    return true;
  }

  public String get(String key) {

//    if (heap[bucket] != null) {
//
//    }

    return null;
  }

  public Integer size() {
    return size;
  }
}

