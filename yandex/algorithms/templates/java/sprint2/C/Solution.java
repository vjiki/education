// <template>
class Node<V> {

  public V value;
  public Node<V> next;

  public Node(V value, Node<V> next) {
    this.value = value;
    this.next = next;
  }
}
// <template>

public class Solution {

  public static Node<String> solution(Node<String> head, int idx) {

    if (idx == 0) {
        head = head.next;
    }
    Node<String> previousNode = getNodeByIndex(head, idx-1);
    Node<String> nextNode = getNodeByIndex(head, idx+1);
    previousNode.next = nextNode;

    return head;
  }

  private static void test() {
    Node<String> node3 = new Node<>("node3", null);
    Node<String> node2 = new Node<>("node2", node3);
    Node<String> node1 = new Node<>("node1", node2);
    Node<String> node0 = new Node<>("node0", node1);
    Node<String> newHead = solution(node0, 1);
    assert newHead == node0;
    assert newHead.next == node2;
    assert newHead.next.next == node3;
    assert newHead.next.next.next == null;
    // result is : node0 -> node2 -> node3
  }

    private static Node<String> getNodeByIndex(Node<String> head, int idx) {
    Node<String> node = head;
    while (idx > 0) {
      idx--;
      node = node.next;
    }

    return node;
  }
}