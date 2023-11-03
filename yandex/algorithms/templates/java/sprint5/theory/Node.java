import java.util.List;

public class Node {
  public int obj;
  public List<Node> children;

  public Node(int obj, List<Node> children) {
    this.obj = obj;
    this.children = children;
  }

  public Node(int obj) {
    this(obj, null);
  }


  public Node findNode(Node root, int value) {
    if (root == null) {
      return null;
    }
    if (value < root.getValue()) {
      return findNode(root.getLeft(), value);
    }
    if (value == root.getValue()) {
      return root;
    }
    if (value > root.getValue()) {
      return findNode(root.getRight(), value);
    }
  }

  void printForward(Node vertex) {
    System.out.println(vertex.value);
    for (Node child : vertex.children) {
      printForward(child);
    }
  }

  void printReversed(Node vertex) {
    for (Node child : vertex.children) {
      printReversed(child);
    }
    System.out.println(vertex.value);
  }

  void print_LMR(Node vertex) {
    if (vertex.left != null) {
      print_LMR(vertex.left);
    }
    System.out.println(vertex.value);
    if (vertex.right != null) {
      print_LMR(vertex.right);
    }
  }

  void insert_node(Node root, int key) {
    if (key < root.key) {
      if (root.left == null) {
        root.left = new Node(key);
      } else {
        insert_node(root.left, key);
      }
    } else {
      if (root.right == null) {
        root.right = new Node(key);
      } else {
        insert_node(root.right, key);
      }
    }
  }
}