import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

class DFS {
    private static final Map<Integer, ArrayList<Integer>> newMatrix = new HashMap<>();

    public static void main(String[] args) throws IOException {

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

            int[] color = new int[n];
            for (int i = 0; i < n; i++) {
                if (isGraphCycled(i, color)) {
                    System.out.println("NO");
                    return;
                }
            }

            System.out.println("YES");
        }
    }

    static boolean isGraphCycled(int startPoint, int[] colors) {
        Stack<Integer> planned = new Stack<>();
        planned.push(startPoint);

        planned.push(startPoint);
        while (!planned.isEmpty()) {
            int u = planned.pop();
            if (colors[u] == 0) {
                colors[u] = 1;
                planned.push(u);
                List<Integer> outgoingEdges = outgoingEdges(u);
                if (outgoingEdges != null) {
                    for (int w : outgoingEdges) {
                        if (colors[w] == 0) {
                            planned.push(w);
                        } else if (colors[w] == 1) {
                            return true;
                        }
                    }
                }
            } else if (colors[u] == 1) {
                colors[u] = 2;
            }
        }
        return false;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static ArrayList<Integer> outgoingEdges(int v) {
        // Реализация получения исходящих рёбер для вершины v
        return newMatrix.get(v);
    }
}