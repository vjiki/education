import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] firstLine = readArray(reader);
            int n = firstLine[0];
            int m = firstLine[1];

            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                int[] row = readArray(reader);
                graph.get(row[0] -1).add(row[1] -1);
                graph.get(row[1] -1).add(row[0] -1);

            }

            getConnectivity(n, graph);
        }
    }

    static void getConnectivity(int n, List<List<Integer>> graph) {

        boolean[] visited = new boolean[n];
        int answer = 0;
        List<List<Integer>> components = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            answer++;
            visited[i] = true;
            List<Integer> component = new ArrayList<>();
            List<Integer> queue = new ArrayList<>();
            queue.add(i);
            while (!queue.isEmpty()) {
                int v = queue.remove(queue.size() - 1);
                component.add(v + 1);
                for (int to : graph.get(v)) {
                    if (!visited[to]) {
                        visited[to] = true;
                        queue.add(to);
                    }
                }
            }
            components.add(component);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(answer).append("\n");
        for (List<Integer> component : components) {
            component.sort(null);
            for (int v : component) {
                sb.append(v).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static int[] readArray(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .mapToInt(Integer::intValue).toArray();
    }
}