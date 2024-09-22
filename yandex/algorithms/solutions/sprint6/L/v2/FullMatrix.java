import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class FullMatrix {
    static final String SUCCESS = "YES";
    static final String FAIL = "NO";

    static class Node {
        int value;
        List<Node> to;

        Node(int value) {
            this.value = value;
            this.to = new ArrayList<>();
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int[] firstLine = readArray(reader);
            int n = firstLine[0];
            int m = firstLine[1];
            List<Node> list = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                list.add(new Node(i));
            }
            for (int i = 0; i < m; i++) {
                int[] row = readArray(reader);
                int a = row[0];
                int b = row[1];
                if (a != b) {
                    list.get(a).to.add(list.get(b));
                    list.get(b).to.add(list.get(a));
                }
            }
            isMatrixFull(n, list);
        }
    }

    public static void isMatrixFull(int vertexes, List<Node> list) {
        for (Node node : list) {
            if (node == null) {
                continue;
            }
            boolean[] visited = new boolean[vertexes + 1];
            for (Node n : node.to) {
                visited[n.value] = true;
            }
            for (int i = 1; i <= vertexes; i++) {
                if (i == node.value) {
                    continue;
                }
                if (!visited[i]) {
                    System.out.println(FAIL);
                    return;
                }
            }
        }
        System.out.println(SUCCESS);
    }

    private static int[] readArray(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .mapToInt(Integer::intValue).toArray();
    }
}