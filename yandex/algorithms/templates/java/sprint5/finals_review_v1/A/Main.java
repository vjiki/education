// https://contest.yandex.ru/contest/24810/run-report/96164467/

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
}

class Heap {

  private final List<Student> heap = new ArrayList<>();

  public void heapAdd(Student key) {
    int index = heap.size() + 1;
    heap.add(key);
    siftUp(index);
  }

  public void siftUp(int index) {
    if (index == 1) {
      return;
    }

    int parentIndex = index / 2;
    if (heap.get(parentIndex - 1).compareTo(heap.get(index - 1)) > 0) {
      Student temp = heap.get(parentIndex - 1);
      heap.set(parentIndex - 1, heap.get(index - 1));
      heap.set(index - 1, temp);
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
      Student temp = heap.get(index);
      heap.set(index, heap.get(indexLargest));
      heap.set(indexLargest, temp);
      siftDown(indexLargest);
    }
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

