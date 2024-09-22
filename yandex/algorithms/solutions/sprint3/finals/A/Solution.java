// https://contest.yandex.ru/contest/23815/run-report/91689667/

/*
-- ПРИНЦИП РАБОТЫ --
Так как мы знаем что массив отсортирован то мы можем искать на отрезках.
Для этого можно использовать метод двух указателей.
Возьмём два указателя. В начале они будут указывать на начало и конец массива.
Будем искать до тех пор, пока левый указатель не станет больше правого.
Возьмём элемент из середины отрезка:
   - Если элемент равен искомому то вернём его
   - Если нет то:
       - проверим если элемент в левом отрезке
          - если да то осечём правую половину отрезка
          - если нет то отсечём левую половину отрезка
       - проверим есть ли элемент в правом отрезке
          - если да то осечём левую половину отрезка
          - если нет то отсечём правую половину отрезка


-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Если элемент существует то он должен находиться внутри одного из отрезков

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Временная сложность O(logn) так как мы отсекаем половину данных при каждом шаге.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Массив, содержащий n элементов, занимает O(n) памяти. Дополнительных расходов по памяти нет.
*/


public class Solution {
  public static int brokenSearch(int[] arr, int k) {
    int leftIndex = 0;
    int rightIndex = arr.length - 1;
    while (leftIndex <= rightIndex) {
      int middleIndex = (leftIndex + rightIndex)/2;
      if (arr[middleIndex] == k) {
        return middleIndex;
      } else if (arr[leftIndex] <= arr[middleIndex]) {
        if (arr[leftIndex] <= k && k < arr[middleIndex]) {
          rightIndex = middleIndex - 1;
        } else {
          leftIndex = middleIndex + 1;
        }
      } else {
        if (arr[middleIndex] < k && k <= arr[rightIndex]) {
          leftIndex = middleIndex + 1;
        } else {
          rightIndex = middleIndex - 1;
        }
      }
    }
    return -1;
  }

  private static void test() {
    int[] arr = {19, 21, 100, 101, 1, 4, 5, 7, 12};
     System.out.println(brokenSearch(arr, 5));
    assert 6 == brokenSearch(arr, 5);
  }
}