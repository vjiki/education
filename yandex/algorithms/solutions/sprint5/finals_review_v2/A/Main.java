// https://contest.yandex.ru/contest/24810/run-report/96486548/

/*
-- ПРИНЦИП РАБОТЫ --
Я реализовал сортировку кучей:
 - сортировка работает на основе компаратора: сначала идут студенты с самым большим количеством решённых задач,
 потом с наименьшим штрафом, потом лексикрофичекси по алфавиту
 - сортировка использует кучу, чтобы сохранять студентов с наилучшим балом на вершине, далее мы извлекаем
   по одному студентов в уже отсортированном виде.


-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Куча использует компаратор для добавления элементов. Самый лучший студент будет всегда на вершине.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
В худшем случае O(nlog(n)).

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
O(n) это дополнительная память которая будет нужна для кучи.
https://en.wikipedia.org/wiki/Heapsort.

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {

  public static void heapsort(List<Student> array) {
    Heap heap = new Heap();

    for (Student item : array) {
      heap.heapAdd(item);
    }

    List<Student> sortedArray = new ArrayList<>();
    while (!heap.isEmpty()) {
      Student max = heap.popMax();
      sortedArray.add(max);
    }

    StringBuilder sb = new StringBuilder();
    for (Student student : sortedArray) {
      sb.append(student.name).append("\n");
    }
    System.out.println(sb);
  }

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      int attendeesSize = readInt(reader);

      List<Student> studentList = new ArrayList<>();

      for (int i = 0; i < attendeesSize; i++) {
        String[] stInfoLine = reader.readLine().split(" ");
        studentList.add(new Student(stInfoLine[0], Integer.parseInt(stInfoLine[1]), Integer.parseInt(stInfoLine[2])));
      }

      heapsort(studentList);
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
  public int compareTo(Student secondStudent) {
    int tasksCmp = Integer.compare(secondStudent.solvedTaskNumber, this.solvedTaskNumber);

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

class Heap {

  private final List<Student> heap = new ArrayList<>();

  public void heapAdd(Student key) {
    int index = heap.size() + 1;
    heap.add(key);
    siftUp(index);
  }

  public void siftUp(int index) {
    if (index <= 1) {
      return;
    }

    int parentIndex = index / 2;
    if (heap.get(parentIndex - 1).compareTo(heap.get(index - 1)) > 0) {
      swap(parentIndex - 1, index - 1);
      siftUp(parentIndex);
    }
  }

  public void siftDown(int index) {
    int left = 2 * index + 1;
    int right = 2 * index + 2;

    if (left >= heap.size()) {
      return;
    }

    int indexLargest = left;
    if (right < heap.size() && heap.get(left).compareTo(heap.get(right)) > 0) {
      indexLargest = right;
    }

    if (heap.get(index).compareTo(heap.get(indexLargest)) > 0) {
      swap(index, indexLargest);
      siftDown(indexLargest);
    }
  }

  public void swap(int parentIndex, int index) {
    Student temp = heap.get(parentIndex);
    heap.set(parentIndex, heap.get(index));
    heap.set(index, temp);
  }

  public Student popMax() {
    Student result = heap.get(0);
    heap.set(0, heap.get(heap.size() - 1));
    heap.remove(heap.size() - 1);
    siftDown(0);
    return result;
  }

  public boolean isEmpty() {
    return heap.isEmpty();
  }
}

