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
}