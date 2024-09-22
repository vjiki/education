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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Main {

  static String quickSort(Student[] students, Comparator<Student> less) {

    EffectiveQuickSort effectiveQuickSort = new EffectiveQuickSort(less);
//    List<Student> sortedStudents = effectiveQuickSort.quicksort(students);
    effectiveQuickSort.quickSortV2(students, 0, students.length-1);

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
    if (!(o instanceof Student student)) {
      return false;
    }
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


  List<Student>[] partition(List<Student> array, Student pivot) {
    ArrayList<Student> left = new ArrayList<>();
    ArrayList<Student> center = new ArrayList<>();
    ArrayList<Student> right = new ArrayList<>();


    for (Student x : array) {
      int cmp = comparator.compare(x, pivot);
      if (cmp < 0) {
        left.add(x);
      } else if (cmp == 0) {
        center.add(x);
      } else {
        right.add(x);
      }
    }
    List<Student>[] result = new List[3];
    result[0] = left;
    result[1] = center;
    result[2] = right;
    // return new List<Integer>[]{left, center, right};
    return result;
  }

  void partitionV1(List<Student> array, Student pivot) {
    ArrayList<Student> left = new ArrayList<>();
    ArrayList<Student> center = new ArrayList<>();
    ArrayList<Student> right = new ArrayList<>();


    int leftIndex = 0;
    int rightIndex = array.size()-1;

    while (leftIndex < rightIndex) {
      int cmpLeft = comparator.compare(array.get(leftIndex), pivot);
      int cmpRight = comparator.compare(array.get(rightIndex), pivot);
      if (cmpLeft < 0) {
        leftIndex++;
      }
      if (cmpRight > 0) {
        rightIndex--;
      }
      if (cmpLeft > 0 && cmpRight < 0) {
        Student rightStudent = array.get(rightIndex);
        array.add(rightIndex, array.get(leftIndex));
        array.add(leftIndex, rightStudent);
        leftIndex++;
        rightIndex--;
      }
    }

    for (Student x : array) {
      int cmp = comparator.compare(x, pivot);
      if (cmp < 0) {
        left.add(x);
      } else if (cmp == 0) {
        center.add(x);
      } else {
        right.add(x);
      }
    }
    List<Student>[] result = new List[3];
    result[0] = left;
    result[1] = center;
    result[2] = right;
    // return new List<Integer>[]{left, center, right};
  }

  // function quicksort(array)
  //    if length(array) > 1
  //        pivot := select any element of array
  //        left := first index of array
  //        right := last index of array
  //        while left ≤ right
  //            while array[left] < pivot
  //                left := left + 1
  //            while array[right] > pivot
  //                right := right - 1
  //            if left ≤ right
  //                swap array[left] with array[right]
  //                left := left + 1
  //                right := right - 1
  //        quicksort(array from first index to right)
  //        quicksort(array from left to last index)

  public void quickSortV2(Student[] array, int begin, int end) {
    if (begin < end) {
      int partitionIndex = partitionV2(array, begin, end);

      quickSortV2(array, begin, partitionIndex-1);
      quickSortV2(array, partitionIndex+1, end);
    }
  }

  private int partitionV2(Student[] array, int begin, int end) {
    Student pivot = array[end];

    int i = (begin-1);

    for (int j = begin; j < end; j++) {
      //if (arr[j] <= pivot) {
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


  void quicksortv1(Student[] array, int leftIndex, int rightIndex) {
    if (array.length < 2 || leftIndex >= rightIndex) {
      return; // массивы с 0 или 1 элементами фактически отсортированы
    } else {
      System.out.println(array.length);
      Random random = new Random();
      Student pivot = array[random.nextInt(array.length-1)];

      while (leftIndex < rightIndex) {
        int cmpLeft = comparator.compare(array[leftIndex], pivot);
        while (cmpLeft < 0) {
          System.out.println("left: " + leftIndex);
          leftIndex++;
          cmpLeft = comparator.compare(array[leftIndex], pivot);
        }
        System.out.println("leftEnd");
        int cmpRight = comparator.compare(array[rightIndex], pivot);
        while (cmpRight > 0) {
          System.out.println("right:" + rightIndex);
          rightIndex--;
          cmpRight = comparator.compare(array[rightIndex], pivot);
        }
        if (comparator.compare(array[leftIndex], array[rightIndex]) > 0) {
          System.out.println("swap");
          Student rightStudent = array[rightIndex];
          array[rightIndex] = array[leftIndex];
          array[leftIndex] = rightStudent;
          leftIndex++;
          rightIndex--;
        }
      }
      System.out.println("leftIndex:" + leftIndex);
      System.out.println("rightIndex:" + rightIndex);
      quicksortv1(array, 0, leftIndex);
      quicksortv1(array, rightIndex, array.length-1);
    }
  }


  List<Student> quicksort(List<Student> array) {
    if (array.size() < 2) {
      return array; // массивы с 0 или 1 элементами фактически отсортированы
    } else {
      Random random = new Random();
      Student pivot = array.get(random.nextInt(array.size()));
      List<Student>[] parts = partition(array, pivot);
      return concatenate(quicksort(parts[0]), parts[1], quicksort(parts[2]));
    }
  }

 List<Student> concatenate(List<Student> a, List<Student> b, List<Student> c) {
    List<Student> result = new ArrayList<>(a.size() + b.size() + c.size());
    result.addAll(a);
    result.addAll(b);
    result.addAll(c);
    return result;
  }
}

