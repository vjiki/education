import java.util.HashMap;

class Node {
    String value;
    HashMap<Character, Node> next;
    boolean terminal;

    public Node(String value, HashMap<Character, Node> next) {
        this.value = value;
        this.next = next == null ? new HashMap<>() : next;
        this.terminal = false;
    }
}

public class Answers {

    public static Node createTree(String[] words) {
        Node root = new Node("", null);
        for (String word : words) {
            Node node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!node.next.containsKey(c)) {
                    node.next.put(c, new Node(String.valueOf(c), null));
                }
                node = node.next.get(c);
            }
            node.terminal = true;
        }
        return root;
    }

    public static boolean isSplitWords(String string, String[] words) {
        Node root = createTree(words);
        boolean[] dp = new boolean[string.length() + 1];
        dp[0] = true;
        for (int i = 0; i < string.length(); i++) {
            Node node = root;
            if (dp[i]) {
                for (int j = i; j < string.length(); j++) {
                    if (node.terminal) {
                        dp[j + 1] = true;
                    }
                    if (j == string.length() || !node.next.containsKey(string.charAt(j))) {
                        break;
                    }
                    node = node.next.get(string.charAt(j));
                }
            }
        }
        return dp[string.length()];
    }

    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String string = scanner.nextLine();
        int wordCount = Integer.parseInt(scanner.nextLine());
        String[] words = new String[wordCount];
        for (int i = 0; i < wordCount; i++) {
            words[i] = scanner.nextLine();
        }
        System.out.println(isSplitWords(string, words) ? "YES" : "NO");
    }
}
