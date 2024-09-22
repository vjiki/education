import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


public class SearchSystemV0 {
  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      int docsAmount = readInt(reader);

      List<Map<String, Integer>> docs = new ArrayList<>();
      for (int i = 0; i < docsAmount; i++) {
        Map<String, Integer> wordsMap = readMap(reader);
        docs.add(wordsMap);
      }

      int queriesAmount = readInt(reader);

      StringBuilder sb = new StringBuilder();

      for (int i = 0; i < queriesAmount; i++) {
        Set<String> queries = readArray(reader);
        sb.append(getTopDocs(queries, docs)).append("\n");
      }

      System.out.println(sb);

    }
  }

  private static int readInt(BufferedReader reader) throws IOException {
    return Integer.parseInt(reader.readLine());
  }

  private static Set<String> readArray(BufferedReader reader) throws IOException {
    return Arrays.stream(reader.readLine().split(" ")).collect(Collectors.toSet());
  }

  private static Map<String, Integer> readMap(BufferedReader reader) throws IOException {
    Map<String, Integer> wordsMap = new HashMap<>();
    String[] words =  reader.readLine().split(" ");
    for (String word : words) {
      wordsMap.compute(word, (k, v) -> v == null ? 1 : v + 1);
    }
    return wordsMap;
  }

  static String getTopDocs(Set<String> queries, List<Map<String, Integer>> docs) {
    List<Doc> docList = new ArrayList<>();

    int docNum = 1;
    for (Map<String, Integer> docCount: docs) {
      Doc doc = new Doc(docNum,0);
      for (String query: queries) {
        if (docCount.get(query) != null) {
          doc.rating = doc.rating + docCount.get(query);
        }
      }
      if (doc.rating != 0) {
        docList.add(doc);
      }
      docNum++;
    }

    Collections.sort(docList);
    StringBuilder sb = new StringBuilder();
    docList.stream().limit(5).forEach(doc -> sb.append(doc.docNumber).append(" "));

    if (sb.toString().isEmpty()) {
      return "";
    }

    return sb.deleteCharAt(sb.length()-1).toString();
  }

  static class Doc implements Comparable<Doc> {
    int docNumber;
    int rating;

    public Doc(int docNumber, int rating) {
      this.docNumber = docNumber;
      this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Doc)) {
        return false;
      }
      Doc doc = (Doc) o;
      return docNumber == doc.docNumber && rating == doc.rating;
    }

    @Override
    public int hashCode() {
      return Objects.hash(docNumber, rating);
    }

    @Override
    public int compareTo(Doc secondDoc) {
      if (this.equals(secondDoc)) {
        return 0;
      } else {
        int tasksCmp = Integer.compare(secondDoc.rating, this.rating);

        if (tasksCmp == 0) {
          return Integer.compare(this.docNumber, secondDoc.docNumber);
        } else {
          return tasksCmp;
        }
      }
    }
  }
}
