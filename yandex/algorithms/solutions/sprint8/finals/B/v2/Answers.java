import java.util.*;
import java.io.*;

class Answers {
    static final String SUCCESS = "YES";
    static final String FAIL = "NO";

    static class Node {
        boolean isBlack;
        PrefixMap tree;

        Node() {
            isBlack = false;
            tree = new PrefixMap();
        }
    }

    static class PrefixMap extends HashMap<String, Node> {}

    static class DP extends ArrayList<ArrayList<Node>> {}

    public static void main(String[] args) {
        StringBuilder s = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        String t;
        Node root;
        try {
            t = scanner.nextLine();
            int n = Integer.parseInt(scanner.nextLine());
            root = new Node();
            for (int i = 0; i < n; i++) {
                String p = scanner.nextLine();
                if (Character.isWhitespace(p.charAt(p.length() - 1))) {
                    addPrefix(root, p.substring(0, p.length() - 1));
                } else {
                    addPrefix(root, p);
                }
            }
            Solution(t, root, s);
            System.out.println(s.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Solution(String t, Node rootNode, StringBuilder s) {
        if (rootNode.tree.get(String.valueOf(t.charAt(0))) == null) {
            s.append(FAIL);
            return;
        }
        DP dp = new DP();
        dp.add(new ArrayList<>());
        dp.get(0).add(rootNode.tree.get(String.valueOf(t.charAt(0))));
        for (int i = 1; i < t.length(); i++) {
            String c = String.valueOf(t.charAt(i));
            boolean isSomeBlack = false;
            ArrayList<Node> nodes = findNodes(dp.get(i - 1), c);
            for (Node node : nodes) {
                if (node.isBlack) {
                    isSomeBlack = true;
                }
                if (rootNode.tree.get(c) != null) {
                    dp.get(i).add(rootNode.tree.get(c));
                }
            }
            if (dp.get(i).isEmpty()) {
                break;
            }
        }
        for (Node v : dp.get(t.length() - 1)) {
            if (v.isBlack) {
                s.append(SUCCESS);
                return;
            }
        }
        s.append(FAIL);
    }

    public static ArrayList<Node> findNodes(ArrayList<Node> n, String c) {
        ArrayList<Node> res = new ArrayList<>();
        boolean isBlack = false;
        for (Node v : n) {
            if (v.isBlack) {
                isBlack = true;
            }
            if (v.tree.get(c) != null) {
                res.add(v.tree.get(c));
            }
        }
        return res;
    }

    public static void addPrefix(Node root, String s) {
        for (int i = 0; i < s.length(); i++) {
            String c = String.valueOf(s.charAt(i));
            if (!root.tree.containsKey(c)) {
                root.tree.put(c, new Node());
            }
            root = root.tree.get(c);
        }
        root.isBlack = true;
    }
}