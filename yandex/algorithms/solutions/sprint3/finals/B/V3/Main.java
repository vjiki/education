// https://contest.yandex.ru/contest/23815/run-report/91686310/

/*
-- ПРИНЦИП РАБОТЫ --
Я реализовал эффективную быструю сортировку in place:
 - сортировка работает на основе компаратора: сначала идут студенты с самым большим количеством решённых задач,
 потом с наименьшим штрафом, потом лексикрофичекси по алфавиту
 - сортировка использует рекурсию, выбирает опорный элемент и рекурсивно сортирует относительно опорного элемента


-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Мы не используем дополнительную память на стеке так как всегда сортируем существующий массив

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
В худшем случае сложность O(n2) а средняя O(nlog(n))

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
O(n) это память которая нужна для работы алгоритма. Нет дополнительных расходов по памяти.

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


  public void quickSort(Student[] array, int startIdx, int endIdx) {
    int idx = partition(array, startIdx, endIdx);

    if (startIdx < idx - 1) {
      quickSort(array, startIdx, idx - 1);
    }

    if (endIdx > idx) {
      quickSort(array, idx, endIdx);
    }
  }

  private int partition(Student[] array, int left, int right) {
    Student pivot = array[left + (right - left) / 2];

    while (left <= right) {
      while (comparator.compare(array[left], pivot) < 0) {
        left++;
      }
      while (comparator.compare(array[right], pivot) > 0) {
        right--;
      }

      if (left <= right) {
        Student tmp = array[left];
        array[left] = array[right];
        array[right] = tmp;

        left++;
        right--;
      }
    }
    return left;
  }
}

