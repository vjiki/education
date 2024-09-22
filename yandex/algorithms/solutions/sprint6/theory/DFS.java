import java.util.ArrayList;
import java.util.List;

public class GraphTraversal {
  private static List<String> color;

  public static void main(String[] args) {
    int numVertices = ...; // Задайте нужное количество вершин
    initializeColor(numVertices);

    mainDFS();
  }

  private static void initializeColor(int numVertices) {
    color = new ArrayList<>();
    for (int i = 0; i < numVertices; i++) {
      color.add("white");
    }
  }

  private static void DFS(int v) {
    color.set(v, "gray"); // Вершина посещена, но ещё не обработана.
    List<Integer> outgoingEdges = outgoingEdges(v);
    for (int w : outgoingEdges) {
      if (color.get(w).equals("white")) { // Если вершина не посещена, то
        DFS(w); // запустим обход от найденной смежной вершины.
      }
    }
    color.set(v, "black"); // Теперь вершина обработана.
  }

  private static void mainDFS() {
    for (int i = 0; i < color.size(); i++) {
      // Перебираем варианты стартовых вершин, пока они существуют.
      if (color.get(i).equals("white")) {
        DFS(i); // Запускаем обход, стартуя с i-й вершины.
      }
    }
  }

  private static List<Integer> outgoingEdges(int v) {
    // Реализация получения исходящих рёбер для вершины v
  }
}