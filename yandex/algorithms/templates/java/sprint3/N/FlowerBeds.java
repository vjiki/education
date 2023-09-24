import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.io.InputStreamReader;
import java.util.HashMap;


public class FlowerBeds {


  static void removeDups(int[][] a)
  {
    HashMap<Integer, Integer> mp = new HashMap<>();

    for (int i = 0; i < a.length; ++i) {
      if (mp.get(a[i][0]) == null)
      {
        mp.putIfAbsent(a[i][0], i);
      }
    }

    for (Integer key: mp.keySet()) {
      System.out.println(a[mp.get(key)][0] + " " + a[mp.get(key)][1]);
    }
  }

  static void mergeBeds(int[][] allNumbers) {

//    int[][] result = new int[allNumbers.length][];


//    boolean swaps = true;
//    while(swaps) {
//      swaps = false;
    for (int i = 0; i < allNumbers.length; i++) {
      int[] newPair = allNumbers[i];
      for (int j = 0; j < allNumbers.length; j++) {
        if ((newPair[0] >= allNumbers[j][0] && newPair[1] <= allNumbers[j][1]) ||
            newPair[1] == allNumbers[j][0]) {
          newPair[0] = Math.min(newPair[0], allNumbers[j][0]);
          newPair[1] = Math.max(newPair[1], allNumbers[j][1]);
          allNumbers[i] = newPair;
          allNumbers[j] = newPair;
//          swaps = true;
        }
      }
    }
//      result[i] = newPair;
    //}

//    System.out.println("remove dups");
    removeDups(allNumbers);
  }


  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      int lineLengths = readInt(reader);
      int[][] allNumbers = new int[lineLengths][];
      for (int i = 0; i < lineLengths; i++) {
        int[] numbers = readArray(reader);
        allNumbers[i] = numbers;
      }
//      for (int i = 0; i < lineLengths; i++) {
//        System.out.println(Arrays.toString(allNumbers[i]));
//      }

      mergeBeds(allNumbers);
//      System.out.println("mergeBeds");
//      for (int i = 0; i < lineLengths; i++) {
//        System.out.println(Arrays.toString(mergedNumbers[i]));
//      }

//      getBedsCoordinats(numbers);
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
