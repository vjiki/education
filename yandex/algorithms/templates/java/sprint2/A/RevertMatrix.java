import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.List;
import java.io.IOException;

public class RevertMatrix {

  private static List<List<Integer>> rotateMatrix(List<List<Integer>> matrix) {
    List<List<Integer>> newMatrix = new ArrayList<>();
    if (matrix.isEmpty()) {
      return Collections.emptyList();
    }
    for (int i = 0; i < matrix.get(0).size(); i++) {
      newMatrix.add(new ArrayList<>());
    }

    for (List<Integer> row : matrix) {
      List<Integer> newRow;
      for (int j = 0; j < row.size(); j++) {
        newRow = newMatrix.get(j);
        newRow.add(row.get(j));
      }
    }

    return newMatrix;
  }
  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      int rowsCount = readInt(reader);
      int colsCount = readInt(reader);
      List<List<Integer>> matrix = readMatrix(reader, rowsCount);
      List<List<Integer>> rotatedMatrix = rotateMatrix(matrix);
      StringBuilder sb = new StringBuilder();
      for (List<Integer> integers : rotatedMatrix) {
        for (int i = 0; i < integers.size(); i++) {
         sb.append(integers.get(i));
         if (i < integers.size()-1) {
           sb.append(" ");
         }
        }
        sb.append("\n");
      }
      System.out.println(sb);
    }
  }


  private static int readInt(BufferedReader reader) throws IOException {
    return Integer.parseInt(reader.readLine());
  }

  private static List<Integer> readList(BufferedReader reader) throws IOException {
    return Arrays.asList(reader.readLine().split(" "))
        .stream()
        .map(elem -> Integer.parseInt(elem))
        .collect(Collectors.toList());
  }

  private static List<List<Integer>> readMatrix(BufferedReader reader, int rowsCount) throws IOException {
    List<List<Integer>> matrix = new ArrayList<>(rowsCount);
    for (int i = 0; i < rowsCount; i++) {
      matrix.add(readList(reader));
    }
    return matrix;
  }
}
