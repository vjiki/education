import java.util.Arrays;

public class Main {

  public static void main(String[] args) {
    //Solution.test();
//    int[] a = {1, 4, 9, 2, 10, 11};
//    int[] b = Solution.merge(a, 0, 3, 6);
//    System.out.println(Arrays.toString(b));

    //
    int[] c = {1, 4, 2, 10, 1, 2};
    Solution.merge_sort(c, 0, 6);
    System.out.println(Arrays.toString(c));
  }
}
