import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main {
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
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        solution(reader, sb);
        System.out.println(sb);
    }

    public static void solution(BufferedReader reader, StringBuilder sb) throws IOException {
        String[] initData = reader.readLine().split(" ");
        int vertexes = Integer.parseInt(initData[0]);
        int edges = Integer.parseInt(initData[1]);
        List<Node> list = new ArrayList<>();
        for (int i = 0; i <= vertexes; i++) {
            list.add(new Node(i));
        }
        for (int i = 0; i < edges; i++) {
            String[] edgeData = reader.readLine().split(" ");
            int a = Integer.parseInt(edgeData[0]);
            int b = Integer.parseInt(edgeData[1]);
            if (a != b) {
                list.get(a).to.add(list.get(b));
                list.get(b).to.add(list.get(a));
            }
        }
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
                    sb.append(FAIL);
                    return;
                }
            }
        }
        sb.append(SUCCESS);
    }
}