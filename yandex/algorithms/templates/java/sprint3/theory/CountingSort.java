public class CountingSort {

  // функция counting_sort(array, k)
  //  counted_values = [0] * k
  //  for value in array:
  //    counted_values[value] += 1
  //
  //  index = 0
  //  for value = 0 .. (k - 1):
  //    for amount = 1 .. counted_values[value]:
  //      array[index] = value
  //      index += 1

  public static int[] countingSort(int[] array, int k) {
    int[] countedValues = new int[k];
    for (int value : array) {
      countedValues[value]++;
    }

    int index = 0;
    for (int value = 0; value < k; value++) {
      for (int amount = 0; amount < countedValues[value]; amount++) {
        array[index] = value;
        index++;
      }
    }
    return array;
  }
}
