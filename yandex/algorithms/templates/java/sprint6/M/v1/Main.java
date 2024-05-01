import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Node {
    int value;
    List<Node> pointsTo;

    public Node(int value) {
        this.value = value;
        this.pointsTo = new ArrayList<>();
    }
}

public class Main {
    static final String RED = "red";
    static final String BLUE = "blue";
    static final String SUCCESS = "YES";
    static final String FAIL = "NO";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int vertexes = Integer.parseInt(st.nextToken());
            int edges = Integer.parseInt(st.nextToken());
            List<Node> AL = new ArrayList<>();
            for (int i = 0; i <= vertexes; i++) {
                AL.add(new Node(i));
            }
            for (int i = 0; i < edges; i++) {
                st = new StringTokenizer(reader.readLine());
                int vertexA = Integer.parseInt(st.nextToken());
                int vertexB = Integer.parseInt(st.nextToken());
                AL.get(vertexA).pointsTo.add(AL.get(vertexB));
                AL.get(vertexB).pointsTo.add(AL.get(vertexA));
            }
            String[] colors = new String[vertexes + 1];
            for (Node node : AL) {
                if (node == null) {
                    continue;
                }
                if (colors[node.value] == null) {
                    boolean ok = dfs(node, colors, BLUE);
                    if (!ok) {
                        System.out.println(FAIL);
                        return;
                    }
                }
            }
            System.out.println(SUCCESS);
    }

    public static boolean dfs(Node node, String[] colors, String currentColor) {
        colors[node.value] = currentColor;
        String oppositeColor = color(currentColor);
        for (Node n : node.pointsTo) {
            if (colors[n.value] == null) {
                boolean ok = dfs(n, colors, oppositeColor);
                if (!ok) {
                    return false;
                }
            }
            if (colors[n.value].equals(currentColor)) {
                return false;
            }
        }
        return true;
    }

    public static String color(String color) {
        if (color.equals(BLUE)) {
            return RED;
        }
        return BLUE;
    }
}
