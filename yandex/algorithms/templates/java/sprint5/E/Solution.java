public class Solution {

    private static boolean isBinary = true;

    public static boolean treeSolution(Node head) {
        return isSearchTree(head, head.value, 0);
    }

    public static boolean isSearchTree(Node head, int max, int isLeft) {
        if (head.left != null && head.right != null) {
            if (isLeft == 0) {
                if (isLess(head)) {
                    boolean leftBool = isSearchTree(head.left, max, 1);
                    boolean rightBool = isSearchTree(head.right, max, 2);
                    if (!leftBool || !rightBool) {
                        isBinary = false;
                    }
                } else {
                    isBinary = false;
                }
            } else if (isLeft == 1) {
                if (isLessLeft(head, max)) {
                    boolean leftBool = isSearchTree(head.left, max, 1);
                    boolean rightBool = isSearchTree(head.right, max, 1);
                    if (!leftBool || !rightBool) {
                        isBinary = false;
                    }
                } else {
                    isBinary = false;
                }
            } else {
                if (isRightMore(head, max)) {
                    boolean leftBool = isSearchTree(head.left, max, 2);
                    boolean rightBool = isSearchTree(head.right, max, 2);
                    if (!leftBool || !rightBool) {
                        isBinary = false;
                    }
                } else {
                    isBinary = false;
                }
            }
        } else if (head.left != null) {
            if (head.left.value < head.value) {
                boolean result = isSearchTree(head.left, max, 0);
                if(!result) {
                    isBinary = false;
                }
            } else {
                isBinary = false;
            }
        } else if (head.right != null) {
            if (head.right.value > head.value) {
                boolean result = isSearchTree(head.right, max, 0);
                if(!result) {
                    isBinary = false;
                }
            } else {
                isBinary = false;
            }
        }
        return isBinary;
    }

    static boolean isLess(Node head) {
        return head.left.value < head.value && head.right.value > head.value;
    }

    static boolean isRightMore(Node head, int max) {
      return head.left.value < head.value && head.right.value > head.value && head.right.value > max && head.left.value > max;
    }

    static boolean isLessLeft(Node head, int max) {
        return head.left.value < head.value && head.right.value > head.value && head.left.value < max && head.right.value < max;
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

        Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
    // <template>


    private static void test() {
        Node node1 = new Node(1, null, null);
        Node node2 = new Node(4, null, null);
        Node node3 = new Node(3, node1, node2);
        Node node4 = new Node(8, null, null);
        Node node5 = new Node(5, node3, node4);
        assert treeSolution(node5);
        node2.value = 5;
        assert !treeSolution(node5);
    }
}