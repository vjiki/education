import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.io.InputStreamReader;

public class BubbleSort {

  static void bubbleSort(int[] integersArray) {
    int n = integersArray.length;
    int swapsCount = 1;
    boolean swapHappend = false;
    while (swapsCount != 0) {
      StringBuilder sb = new StringBuilder();
      swapsCount = 0;
      for (int i = 0; i < n -1; i++) {
        if (integersArray[i] > integersArray[i+1]) {
          int newSwap = integersArray[i+1];
          integersArray[i+1] = integersArray[i];
          integersArray[i] = newSwap;
          swapsCount++;
          swapHappend = true;
        }
//        n--;
        sb.append(integersArray[i]).append(" ");
      }
      sb.append(integersArray[n -1]);
      if (!swapHappend) {
        System.out.println(sb);
      }
      if (swapsCount != 0) {
        System.out.println(sb);
      }
    }
  }


  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      int listLength = readInt(reader);
      int[] unsortedArray = readArray(reader);
      bubbleSort(unsortedArray);
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
