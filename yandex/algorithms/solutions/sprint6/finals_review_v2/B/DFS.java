// https://contest.yandex.ru/contest/25070/run-report/98853001/

/*
-- ПРИНЦИП РАБОТЫ --
Я реализовал проверку на оптимальность железнодорожной карты с помощью DFS алгоритма для поиска циклов:
 Алгоритм я реализовал на основе урока 5 DFS. Поиск цикла и времена входа-выхода. Там описан алгоритм.
 - из особенностей: при считывании мы меняем направление ребра для дорог типа B, чтобы получить граф
 - для каждой вершины запускаем обход DFS, если граф имеет цикл, значит путь неоптимальный.
 - если в процессе обхода графа мы наткнемся на серый город, это означает, что в графе есть цикл.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
При удалении мы используем самый правый узел из левого поддерева.
Когда мы удаляем вершину, мы перезаписываем туда значение заменителя (sub), после мы удаляем sub (который уже без детей)

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
O(V+E) - как в DFS со списками смежности.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
O(E*V) - список смежности, где V - количество вершин, E - количество рёбер.
O(V) - хранение массива цветов, где V - количество вершин.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

class DFS {

    public static void main(String[] args) throws IOException {
        Map<Integer, ArrayList<Integer>> newMatrix = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            for (int i = 0; i < n - 1; i++) {
                char[] row = reader.readLine().toCharArray();
                for (int j = 0; j < row.length; j++) {
                    if (row[j] == 'R') {
                        ArrayList<Integer> val;

                        if (newMatrix.containsKey(i)) {
                            val = newMatrix.get(i);
                        } else {
                            val = new ArrayList<>();
                        }
                        val.add(j + 1 + i);
                        newMatrix.put(i, val);
                    }
                    if (row[j] == 'B') {
                        ArrayList<Integer> val;
                        if (newMatrix.containsKey(j + 1 + i)) {
                            val = newMatrix.get(j + 1 + i);
                        } else {
                            val = new ArrayList<>();
                        }
                        val.add(i);
                        newMatrix.put(j + 1 + i, val);
                    }
                }
            }

            Color[] colors = new Color[n];
            Arrays.fill(colors, Color.WHITE);
            for (int i = 0; i < n; i++) {
                if (isGraphCycled(i, colors, newMatrix)) {
                    System.out.println("NO");
                    return;
                }
            }

            System.out.println("YES");
        }
    }

    static boolean isGraphCycled(int startPoint, Color[] colors, Map<Integer, ArrayList<Integer>> newMatrix) {
        Stack<Integer> planned = new Stack<>();
        planned.push(startPoint);

        planned.push(startPoint);
        while (!planned.isEmpty()) {
            int u = planned.pop();
            if (colors[u] == Color.WHITE) {
                colors[u] = Color.GRAY;
                planned.push(u);
                List<Integer> outgoingEdges = outgoingEdges(u, newMatrix);
                if (outgoingEdges != null) {
                    for (int w : outgoingEdges) {
                        if (colors[w] == Color.WHITE) {
                            planned.push(w);
                        } else if (colors[w] == Color.GRAY) {
                            return true;
                        }
                    }
                }
            } else if (colors[u] == Color.GRAY) {
                colors[u] = Color.BLACK;
            }
        }
        return false;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static ArrayList<Integer> outgoingEdges(int v, Map<Integer, ArrayList<Integer>> newMatrix) {
        // Реализация получения исходящих рёбер для вершины v
        return newMatrix.get(v);
    }
}

enum Color {
    WHITE,
    GRAY,
    BLACK;
}