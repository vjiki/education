// https://contest.yandex.ru/contest/24414/run-report/92668620/

/*
-- ПРИНЦИП РАБОТЫ --
Я реализовал поиск по документам следующей логикой:
- сначала ищем id документов в которых есть хотя бы одно слово из того документа который мы ищем
- потом для этих документах считаем рейтинг
- потом сортируем по рейтингу
- берем 5 верхних документов


-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
при сортировке документы с лучшем рейтингом окажутся наверху

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
O(Q*M*W) -> Q - это количество поисковых запросов
M - количество документов
W - количество слов в поисковом запросе

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Мы выделяем несколько хэш мапы для хранения дополнительной информации
O (N*WD + W)
N - количество документов
WD - количесво слов в документе
W - количество слов во всех документах

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


public class SearchSystem {
  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      int docsAmount = readInt(reader);

      List<Map<String, Integer>> docs = new ArrayList<>();
      Map<String, Set<Integer>> wordToDocNums = new HashMap<>();
      for (int i = 0; i < docsAmount; i++) {
        Map<String, Integer> wordsMap = readMap(reader, i+1, wordToDocNums);
        docs.add(wordsMap);
      }

      int queriesAmount = readInt(reader);
      List<String[]> queries = new ArrayList<>();

      for (int i = 0; i < queriesAmount; i++) {
        String[] queriesArray = readArray(reader);
        queries.add(queriesArray);
      }

      getTopDocs(queries, docs, wordToDocNums);
    }
  }

  private static int readInt(BufferedReader reader) throws IOException {
    return Integer.parseInt(reader.readLine());
  }

  private static String[] readArray(BufferedReader reader) throws IOException {
    return Arrays.stream(reader.readLine().split(" ")).collect(Collectors.toSet()).toArray(String[]::new);
  }

  private static Map<String, Integer> readMap(BufferedReader reader, int docNum, Map<String, Set<Integer>> wordToDocNums) throws IOException {
    Map<String, Integer> wordsMap = new HashMap<>();
    String[] words =  reader.readLine().split(" ");
    for (String word : words) {
      wordsMap.compute(word, (k, v) -> v == null ? 1 : v + 1);
      if (wordToDocNums.get(word) == null ) {
        Set<Integer> docSet = new HashSet<>();
        docSet.add(docNum);
        wordToDocNums.put(word, docSet);
      } else {
        Set<Integer> docSet = wordToDocNums.get(word);
        docSet.add(docNum);
        wordToDocNums.put(word, docSet);
      }
    }
    return wordsMap;
  }

  static void getTopDocs(List<String[]> queriesList, List<Map<String, Integer>> docs, Map<String, Set<Integer>> wordToDocNums) {

    StringBuilder sbm = new StringBuilder();

    for(String[] queries: queriesList) {
      List<Doc> docList = new ArrayList<>();

      Set<Integer> docIds = getDocIds(queries, wordToDocNums);

      int docNum = 1;
      for (Map<String, Integer> docCount : docs) {
        if(docIds.contains(docNum)) {
          Doc doc = new Doc(docNum, 0);
          for (String query : queries) {
            if (docCount.get(query) != null) {
              doc.rating = doc.rating + docCount.get(query);
            }
          }
          if (doc.rating != 0) {
            docList.add(doc);
          }
        }
        docNum++;
      }

      Collections.sort(docList);
      StringBuilder sb = new StringBuilder();
      docList.stream().limit(5).forEach(doc -> sb.append(doc.docNumber).append(" "));

      if (!sb.toString().isEmpty()) {
        sbm.append(sb.deleteCharAt(sb.length() - 1)).append("\n");
      }
    }
    System.out.println(sbm);
  }

  static boolean isDocContainsAnyWord(String[] queries, Set<String> keys) {
    return keys.stream().anyMatch(e -> Arrays.stream(queries).collect(Collectors.toList()).contains(e));
  }

  static Set<Integer> getDocIds(String[] queries, Map<String, Set<Integer>> wordToDocNums) {
    Set<Integer> docIds = new HashSet<>();
    for (String query: queries) {
      if (wordToDocNums.get(query) != null) {
        docIds.addAll(wordToDocNums.get(query));
      }
    }
    return docIds;
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
