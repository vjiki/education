// https://contest.yandex.ru/contest/25597/run-report/102402073/

/*
-- ПРИНЦИП РАБОТЫ --
Описание алгоритма - https://en.wikipedia.org/wiki/Partition_problem
Я использую вариант с методом наибольшей разности.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Доказательство алгоритма - https://en.wikipedia.org/wiki/Partition_problem

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
O(nlogn), где n количество оценок.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
O(n) для хранения приоритетной очереди, где n - количество оценок.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Algos {

  static boolean karmarkarKarpPartition(int[] baseArr) {
    Comparator<Integer> comp = (Integer::compareTo);
    // create max heap
    PriorityQueue<Integer> heap = new PriorityQueue<>(baseArr.length, comp.reversed());

    for (int value : baseArr) {
      heap.add(value);
    }

    while(heap.size() > 1) {
      int val1=heap.poll();
      int val2=heap.poll();
      heap.add(val1 - val2);
    }

      return heap.poll() == 0;
  }

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      int listLength = readInt(reader);
      if (listLength <= 1 ) {
        System.out.println("False");
      } else {
        int[] competitionsScores = readArray(reader);
        System.out.println(karmarkarKarpPartition(competitionsScores) ? "True" : "False");
      }
    }
  }

  private static int readInt(BufferedReader reader) throws IOException {
    return Integer.parseInt(reader.readLine());
  }

  private static int[] readArray(BufferedReader reader) throws IOException {
    return Arrays.stream(reader.readLine().split(" "))
        .map(Integer::parseInt)
        .mapToInt(Integer::intValue).toArray();
  }
}
