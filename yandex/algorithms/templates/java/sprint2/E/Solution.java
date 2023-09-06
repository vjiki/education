import java.util.ArrayList;
import java.util.List;

// <template>
class Node<V> {
    public V value;
    public Node<V> next;
    public Node<V> prev;

    public Node(V value, Node<V> next, Node<V> prev) {
        this.value = value;
        this.next = next;
        this.prev = prev;
    }
}
// <template>

public class Solution {

    private static int getLastIndex(Node<String> head) {
        Node<String> currentNode = head;
        int length = 0;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
            length++;
        }
        return length;
    }

    private static Node<String> getNodeByIndex(Node<String> head, int idx) {
        Node<String> node = head;
        while (idx > 0) {
            idx--;
            node = node.next;
        }

        return node;
    }

    public static Node<String> solution(Node<String> head) {

        int lastIndex = getLastIndex(head);
        List<Node<String>> listWithNewNodes = new ArrayList<>();
        Node<String> newHead = new Node<>(getNodeByIndex(head, lastIndex).value, null, null);
        listWithNewNodes.add(newHead);
        int newIndex = 0;
        while (lastIndex != 0) {
            Node<String> currentElem = listWithNewNodes.get(newIndex);
            lastIndex--;
            Node<String> newElem = new Node<>(getNodeByIndex(head, lastIndex).value, null, currentElem);
            listWithNewNodes.add(newElem);
            currentElem.next = newElem;
            newIndex++;
        }
        return newHead;
    }

    private static void test() {
        Node<String> node3 = new Node<>("node3", null, null);
        Node<String> node2 = new Node<>("node2", node3, null);
        Node<String> node1 = new Node<>("node1", node2, null);
        Node<String> node0 = new Node<>("node0", node1, null);
        node1.prev = node0;
        node2.prev = node1;
        node3.prev = node2;
        Node<String> newNode = solution(node0);
        /* result is :*/
        assert newNode == node3;
        assert node3.next == node2;
        assert node2.next == node1;
        assert node2.prev == node3;
        assert node1.next == node0;
        assert node1.prev == node2;
        assert node0.prev == node1;
    }
}