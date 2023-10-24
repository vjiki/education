public class Solution {
    public static int treeSolution(Node head) {
        if (head.left != null && head.right != null) {
            int maxChildren = Math.max(treeSolution(head.left), treeSolution(head.right));
            return Math.max(head.value, maxChildren);
        } else if (head.left != null) {
            return Math.max(head.value, treeSolution(head.left));
        } else if (head.right != null) {
            return Math.max(head.value, treeSolution(head.right));
        } else {
            return head.value;
        }
    }

    // <template>
    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
    // <template>

    private static void test() {
        Node node1 = new Node(1);
        Node node2 = new Node(-5);
        Node node3 = new Node(3);
        node3.left = node1;
        node3.right = node2;
        Node node4 = new Node(2);
        node4.left = node3;
        assert treeSolution(node4) == 3;
    }
}