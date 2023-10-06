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
import java.util.Comparator;
import java.util.Objects;

public class Main {

  static String quickSort(Student[] students, Comparator<Student> less) {

    EffectiveQuickSort effectiveQuickSort = new EffectiveQuickSort(less);
    effectiveQuickSort.quickSort(students, 0, students.length-1);

    StringBuilder sb = new StringBuilder();

    for (Student student : students) {
      sb.append(student.name).append("\n");
    }

    return sb.toString();
  }

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      int attendeesSize = readInt(reader);
      StudentComparator studentComparator = new StudentComparator();
      Student[] studentList = new Student[attendeesSize];

      for (int i = 0; i < attendeesSize; i++) {
        String[] stInfoLine = reader.readLine().split(" ");
        studentList[i] = new Student(stInfoLine[0], Integer.parseInt(stInfoLine[1]), Integer.parseInt(stInfoLine[2]));
      }

      System.out.println(quickSort(studentList, studentComparator));
    }
  }

  private static int readInt(BufferedReader reader) throws IOException {
    return Integer.parseInt(reader.readLine());
  }

  static class StudentComparator implements Comparator<Student> {

    @Override
    public int compare(Student firstStudent, Student secondStudent) {
      if (firstStudent.equals(secondStudent)) {
        return 0;
      } else {
        int tasksCmp= Integer.compare(secondStudent.solvedTaskNumber, firstStudent.solvedTaskNumber);

        if (tasksCmp == 0) {
          int penaltyCmp = Integer.compare(firstStudent.penaltyScore, secondStudent.penaltyScore);
          if (penaltyCmp == 0) {
            return firstStudent.name.compareTo(secondStudent.name);
          } else {
            return penaltyCmp;
          }
        } else {
          return tasksCmp;
        }
      }
    }
  }
}

class Student {
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
}

class EffectiveQuickSort {

  private final Comparator comparator;

  EffectiveQuickSort(Comparator comparator) {
    this.comparator = comparator;
  }



  public void quickSort(Student[] array, int begin, int end) {
    if (begin < end) {
      int partitionIndex = partition(array, begin, end);

      quickSort(array, begin, partitionIndex-1);
      quickSort(array, partitionIndex+1, end);
    }
  }

  private int partition(Student[] array, int begin, int end) {
    Student pivot = array[end];

    int i = (begin-1);

    for (int j = begin; j < end; j++) {
      if (comparator.compare(array[j], pivot) <= 0) {
        i++;

        Student swapTemp = array[i];
        array[i] = array[j];
        array[j] = swapTemp;
      }
    }

    Student swapTemp = array[i+1];
    array[i+1] = array[end];
    array[end] = swapTemp;

    return i+1;
  }
}

