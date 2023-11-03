// <template>
class Node {
  private int value;
  private Node left;
  private Node right;

  Node(Node left, Node right, int value) {
    this.left = left;
    this.right = right;
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public Node getRight() {
    return right;
  }

  public void setRight(Node right) {
    this.right = right;
  }

  public Node getLeft() {
    return left;
  }

  public void setLeft(Node left) {
    this.left = left;
  }

  public void setValue(int value) {
    this.value = value;
  }
}
// <template>

public class Solution {
  public static Node remove(Node root, int key) {
    if (root.getValue() == key) {
      if (root.getLeft() == null && root.getRight() == null) {
        return null;
      } else if (root.getLeft() == null) {
        return root.getRight();
      } else if (root.getRight() == null) {
        return root.getLeft();
      } else {
        Node newRoot = getNewRoot(root.getLeft());
        if (newRoot != null) {
          newRoot.setRight(root.getRight());
        }
        return newRoot;
      }
    } else {
      NodeWithParent nodeWithParent = findNode(root, root, key);
      Node parent = nodeWithParent.getParent();
      Node nodeToRemove = nodeWithParent.getNode();
      if (nodeToRemove.getLeft() == null && nodeToRemove.getRight() == null) {
        if (parent.getLeft() == nodeToRemove) {
          parent.setLeft(null);
        }
        if (parent.getRight() == nodeToRemove) {
          parent.setRight(null);
        }
        return root;
      }
      return null;
    }
  }

  static Node getNewRoot(Node leftNode) {
    if (leftNode.getLeft() == null && leftNode.getRight() == null) {
      return leftNode;
    } else if (leftNode.getLeft() == null) {
      return getNewRoot(leftNode.getRight());
    } else if (leftNode.getRight() == null) {
      return leftNode;
    } else {
      return getNewRoot(leftNode.getRight());
    }
  }

  static NodeWithParent findNode(Node parent, Node currentRoot, int value) {
    if (currentRoot == null) {
      return null;
    }
    if (value < currentRoot.getValue()) {
      return findNode(currentRoot, currentRoot.getLeft(), value);
    }
    if (value == currentRoot.getValue()) {
      return new NodeWithParent(currentRoot, parent);
    }
    if (value > currentRoot.getValue()) {
      return findNode(currentRoot, currentRoot.getRight(), value);
    }

    return null;
  }



  static void insert_node(Node root, int key) {
    if (key < root.getValue()) {
      if (root.getLeft() == null) {
        root.setLeft(new Node(null, null,key));
      } else {
        insert_node(root.getLeft(), key);
      }
    } else {
      if (root.getRight() == null) {
        root.setRight(new Node(null, null, key));
      } else {
        insert_node(root.getRight(), key);
      }
    }
  }

  private static void test() {
    Node node1 = new Node(null, null, 2);
    Node node2 = new Node(node1, null, 3);
    Node node3 = new Node(null, node2, 1);
    Node node4 = new Node(null, null, 6);
    Node node5 = new Node(node4, null, 8);
    Node node6 = new Node(node5, null, 10);
    Node node7 = new Node(node3, node6, 5);
    Node newHead = remove(node7, 10);
    assert newHead.getValue() == 5;
    assert newHead.getRight() == node5;
    assert newHead.getRight().getValue() == 8;
  }

  static class NodeWithParent {
    private Node node;
    private Node parent;

    public NodeWithParent(Node node, Node parent) {
      this.node = node;
      this.parent = parent;
    }

    public Node getNode() {
      return node;
    }

    public void setNode(Node node) {
      this.node = node;
    }

    public Node getParent() {
      return parent;
    }

    public void setParent(Node parent) {
      this.parent = parent;
    }
  }
}