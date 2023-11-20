import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class MaxStp {
//    private static final List<String> color = new ArrayList<>();
    private static final List<Vertex> graph = new ArrayList<>();
    static List<Edge> maximumSpanningTree = new ArrayList<>();
    static List<Vertex> added = new ArrayList<>();
    static Set<Vertex> notAdded = new HashSet<>();
    static Map<Vertex, Edge> edgesMap = new HashMap<>();


    static void addVertex(Vertex v) {
        added.add(v);
        notAdded.remove(v);
//        edgesList.addAll(graph.stream().filter(e -> {
//            if (e.getKey().equals(v.getKey())) {
//                Map<Vertex, Edge> edgeMap = e.getEdges();
//                for (Vertex end : edgeMap.keySet()) {
//                    if (notAdded.contains(end)) {
//                        return true;
//                    }
//                }
//            }
//            return false;
//        }).collect(Collectors.toList()));

        edgesMap.putAll(v.getEdges());

//        edgesList.addAll(v.getEdges().keySet());

    }

    static void findMST () {
        notAdded.addAll(graph);

        Vertex v = graph.get(0);
        addVertex(v);

        while (!notAdded.isEmpty() && !edgesMap.isEmpty()) {
            Edge e = extractMaximum();
            if (e.getWeight() != Integer.MIN_VALUE) {
                if (notAdded.contains(e.getEnd())) {
                    maximumSpanningTree.add(e);
                    addVertex(e.getEnd());
                }
            } else {
                break;
            }
        }

        if (!notAdded.isEmpty()) {
            System.out.println("Oops! I did it again");
        } else {
            System.out.println(maximumSpanningTree.stream().mapToInt(Edge::getWeight).sum());
        }
    }

    static Edge extractMaximum() {
        Edge nextMaximum = new Edge(Integer.MIN_VALUE);
//        Iterator<Vertex> it = edgesMap.iterator();
        Iterator<Map.Entry<Vertex, Edge>> it = edgesMap.entrySet()
                .iterator();
        while (it.hasNext()) {
            Map.Entry<Vertex, Edge> pair = it.next();
                if (pair.getValue().getWeight() > nextMaximum.getWeight()
                 && !pair.getValue().isIncluded()) {
                    nextMaximum = pair.getValue();
                }
        }
        nextMaximum.setIncluded(true);
        return  nextMaximum;
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int[] firstLine = readArray(reader);
            int n = firstLine[0];
            int m = firstLine[1];

            if (m == 0 && n > 1) {
                System.out.println("Oops! I did it again");
                return;
            } else if (m ==0 && n == 1) {
                System.out.println(0);
                return;
            }

            for (int i = 0; i < m; i++) {
                int[] row = readArray(reader);
                List<Integer> graphKeys = graph.stream().map(Vertex::getKey).collect(Collectors.toList());
                if (!graphKeys.contains(row[0]) && !graphKeys.contains(row[1])) {
                    Vertex a = new Vertex(row[0]);
                    Vertex b = new Vertex(row[1]);
                    Edge ab = new Edge(row[2]);
                    ab.setEnd(b);
                    Map<Vertex, Edge> map = new HashMap<>();
                    map.put(b, ab);
                    a.setEdges(map);
                    graph.add(a);
                    graph.add(b);
                }
                else if (!graphKeys.contains(row[0])) {
                    Vertex a = new Vertex(row[0]);
                    Vertex b = graph.stream().filter(e -> e.getKey().equals(row[1])).findFirst().get();
                    Edge ab = new Edge(row[2]);
                    ab.setEnd(b);
                    Map<Vertex, Edge> map = new HashMap<>();
                    map.put(b, ab);
                    a.setEdges(map);
                    graph.add(a);
                } else if (!graphKeys.contains(row[1])) {
                    Vertex a = graph.stream().filter(e -> e.getKey().equals(row[0])).findFirst().get();
                    Vertex b = new Vertex(row[1]);
                    Edge ab = new Edge(row[2]);
                    ab.setEnd(b);
                    Map<Vertex, Edge> map = a.getEdges();
                    map.put(b, ab);
                    a.setEdges(map);
//                    graph.add(a);
                    graph.add(b);
                } else {
                    Vertex a = graph.stream().filter(e -> e.getKey().equals(row[0])).findFirst().get();
                    Vertex b = graph.stream().filter(e -> e.getKey().equals(row[1])).findFirst().get();
                    Map<Vertex, Edge> map = a.getEdges();
                    if (a.getEdges().containsKey(b)) {
                        Edge edge = map.get(b);
                        if (row[2] > edge.getWeight()) {
                            Edge ab = new Edge(row[2]);
                            ab.setEnd(b);
                            map.put(b, ab);
                            a.setEdges(map);
                        }
                    } else {
                        Edge ab = new Edge(row[2]);
                        ab.setEnd(b);
                        map.put(b, ab);
                        a.setEdges(map);
                    }
//                    graph.add(a);
                }

//                System.out.println(originalGraphToString());


//                resetPrintHistory();

//                {
//                    TreeSet<Pair> val;
//                    if (newMatrix.containsKey(row[0])) {
//                        val = newMatrix.get(row[0]);
//                    } else {
//                        val = new TreeSet<>();
//                    }
//                    val.add(new Pair(row[1], row[2]));
//                    newMatrix.put(row[0], val);
//                }
//                {
//                    TreeSet<Pair> val;
//                    if (newMatrix.containsKey(row[1])) {
//                        val = newMatrix.get(row[1]);
//                    } else {
//                        val = new TreeSet<>();
//                    }
//                    val.add(new Pair(row[0], row[2]));
//                    newMatrix.put(row[1], val);
//                }
            }


//            run();
//            System.out.println(maximumValueOfOSTP());

            findMST();

//            initializeColor(n);
//
//            StringBuilder sb = new StringBuilder();
//            DFS(1, sb);
//            System.out.println(sb);
        }
    }


    public static void run() {
        if (!graph.isEmpty()) {
            graph.get(0).setVisited(true);
        }
        while (isDisconnected()) {
            Edge nextMaximum = new Edge(Integer.MIN_VALUE);
            Vertex nextVertex = graph.get(0);
            for (Vertex vertex : graph) {
                if (vertex.isVisited()) {
                    Pair<Vertex, Edge> candidate = vertex.nextMaximum();
                    if (candidate.getValue().getWeight() > nextMaximum.getWeight()) {
                        nextMaximum = candidate.getValue();
                        nextVertex = candidate.getKey();
                    }
                }
            }
            nextMaximum.setIncluded(true);
            nextVertex.setVisited(true);
        }
    }


    private static boolean isDisconnected() {
        for (Vertex vertex : graph) {
            if (!vertex.isVisited()) {
                return true;
            }
        }
        return false;
    }

    private static int[] readArray(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .mapToInt(Integer::intValue).toArray();
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    public static String originalGraphToString(){
        StringBuilder sb = new StringBuilder();
        for (Vertex vertex : graph){
            sb.append(vertex.originalToString());
        }
        return sb.toString();
    }

    public static void resetPrintHistory(){
        for (Vertex vertex : graph){
            Iterator<Map.Entry<Vertex,Edge>> it = vertex.getEdges().entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Vertex,Edge> pair = it.next();
                pair.getValue().setPrinted(false);
            }
        }
    }

    public static int maximumValueOfOSTP(){
        int sum = 0;
        for (Vertex vertex : graph){
            sum += vertex.includedToWeight();
        }
        return sum;
    }

    public static String maximumSpanningTreeToString(){
        StringBuilder sb = new StringBuilder();
        for (Vertex vertex : graph){
            sb.append(vertex.includedToString());
        }
        return sb.toString();
    }

//    private static void initializeColor(int numVertices) {
//        for (int i = 0; i < numVertices; i++) {
//            color.add("white");
//        }
//    }

//    private static void DFS(int v, StringBuilder sb) {
//        color.set(v - 1, "gray"); // Вершина посещена, но ещё не обработана.
//        sb.append(v).append(" ");
//        TreeSet<Pair> outgoingEdges = outgoingEdges(v);
//
//        if (outgoingEdges != null) {
//            for (Pair w : outgoingEdges) {
//                if (color.get(w.getKey() - 1).equals("white")) { // Если вершина не посещена, то
//                    DFS(w.getKey(), sb); // запустим обход от найденной смежной вершины.
//                }
//            }
//        }
//        color.set(v - 1, "black"); // Теперь вершина обработана.
//    }

//    private static TreeSet<Pair> outgoingEdges(int v) {
//        // Реализация получения исходящих рёбер для вершины v
//        return newMatrix.get(v);
//    }
}

class Edge {

    private Vertex end;
    private int weight;
    private boolean isIncluded = false;
    private boolean isPrinted = false;

    public Edge(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isIncluded() {
        return isIncluded;
    }

    public void setIncluded(boolean included) {
        isIncluded = included;
    }

    public boolean isPrinted() {
        return isPrinted;
    }

    public Vertex getEnd() {
        return end;
    }

    public void setEnd(Vertex end) {
        this.end = end;
    }

    public void setPrinted(boolean printed) {
        isPrinted = printed;
    }
}

class Vertex {

    private Integer key = null;
    private Map<Vertex, Edge> edges = new HashMap<>();
    private boolean isVisited = false;

    public Integer getKey() {
        return key;
    }

    public Map<Vertex, Edge> getEdges() {
        return edges;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public void setEdges(Map<Vertex, Edge> edges) {
        this.edges = edges;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public Vertex(Integer key) {
        this.key = key;
    }

    public Pair<Vertex, Edge> nextMaximum() {
        Edge nextMaximum = new Edge(Integer.MIN_VALUE);
        Vertex nextVertex = this;
        Iterator<Map.Entry<Vertex, Edge>> it = edges.entrySet()
                .iterator();
        while (it.hasNext()) {
            Map.Entry<Vertex, Edge> pair = it.next();
            if (!pair.getKey().isVisited()) {
                if (!pair.getValue().isIncluded()) {
                    if (pair.getValue().getWeight() > nextMaximum.getWeight()) {
                        nextMaximum = pair.getValue();
                        nextVertex = pair.getKey();
                    }
                }
            }
        }
        return new Pair<>(nextVertex, nextMaximum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(key, vertex.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    public String originalToString(){
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<Vertex,Edge>> it = edges.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Vertex,Edge> pair = it.next();
            if (!pair.getValue().isPrinted()) {
                sb.append(getKey());
                sb.append(" --- ");
                sb.append(pair.getValue().getWeight());
                sb.append(" --- ");
                sb.append(pair.getKey().getKey());
                sb.append("\n");
                pair.getValue().setPrinted(true);
            }
        }
        return sb.toString();
    }

    public String includedToString(){
        StringBuilder sb = new StringBuilder();
        if (isVisited()) {
            Iterator<Map.Entry<Vertex,Edge>> it = edges.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Vertex,Edge> pair = it.next();
                if (pair.getValue().isIncluded()) {
                    if (!pair.getValue().isPrinted()) {
                        sb.append(getKey());
                        sb.append(" --- ");
                        sb.append(pair.getValue().getWeight());
                        sb.append(" --- ");
                        sb.append(pair.getKey().getKey());
                        sb.append("\n");
                        pair.getValue().setPrinted(true);
                    }
                }
            }
        }
        return sb.toString();
    }

    public int includedToWeight(){
        int sum = 0;
        if (isVisited()) {
            Iterator<Map.Entry<Vertex,Edge>> it = edges.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Vertex,Edge> pair = it.next();
                if (pair.getValue().isIncluded()) {
                    if (!pair.getValue().isPrinted()) {
                        sum += pair.getValue().getWeight();
                        pair.getValue().setPrinted(true);
                    }
                }
            }
        }
        return sum;
    }

}


class Pair<K, V> {
    private K key;
    private V value;

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public Pair(K paramK, V paramV) {
        this.key = paramK;
        this.value = paramV;
    }

    public String toString() {
        return this.key + "=" + this.value;
    }

    public int hashCode() {
        return this.key.hashCode() * 13 + (this.value == null ? 0 : this.value.hashCode());
    }

    public boolean equals(Object paramObject) {
        if (this == paramObject) return true;
        if ((paramObject instanceof Pair)) {
            Pair localPair = (Pair) paramObject;
            if (!Objects.equals(this.key, localPair.key)) return false;
            return Objects.equals(this.value, localPair.value);
        }
        return false;
    }
}