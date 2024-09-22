import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class Lections {

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      int listLength = readInt(reader);
      readArray(reader, listLength);
    }
  }

  private static int readInt(BufferedReader reader) throws IOException {
    return Integer.parseInt(reader.readLine());
  }

  private static void readArray(BufferedReader reader, int n) throws IOException {
    StringBuilder sb = new StringBuilder();
    HashSet<String> hs = new LinkedHashSet<>();
    for (int i = 0; i < n; i++) {
      hs.add(reader.readLine());
    }

    for (String l : hs) {
      sb.append(l).append("\n");
    }

    System.out.println(sb);

  }
}
