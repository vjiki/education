import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class EffectiveQuickSortV0 {

  public static List<Integer>[] partition(List<Integer> array, int pivot) {
    ArrayList<Integer> left = new ArrayList<>();
    ArrayList<Integer> center = new ArrayList<>();
    ArrayList<Integer> right = new ArrayList<>();
    for (Integer x : array) {
      if (x < pivot) {
        left.add(x);
      } else if (x == pivot) {
        center.add(x);
      } else {
        right.add(x);
      }
    }
    List<Integer>[] result = new List[3];
    result[0] = left;
    result[1] = center;
    result[2] = right;
   // return new List<Integer>[]{left, center, right};
    return result;
  }

  public static List<Integer> quicksort(List<Integer> array) {
    if (array.size() < 2) {
      return array; // массивы с 0 или 1 элементами фактически отсортированы
    } else {
      Random random = new Random();
      int pivot = array.get(random.nextInt(array.size()));
      List<Integer>[] parts = partition(array, pivot);
      return concatenate(quicksort(parts[0]), parts[1], quicksort(parts[2]));
    }
  }

  public static List<Integer> concatenate(List<Integer> a, List<Integer> b, List<Integer> c) {
    List<Integer> result = new ArrayList<>(a.size() + b.size() + c.size());
    result.addAll(a);
    result.addAll(b);
    result.addAll(c);
    return result;
  }
}
