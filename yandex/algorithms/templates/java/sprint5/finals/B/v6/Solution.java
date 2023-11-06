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
    // Your code
    if (root == null) {
      return null;
    }
    if (root.getValue() == key) {
      if (root.getLeft() == null && root.getRight() == null) {
        return null;
      } else if (root.getLeft() == null) {
        return root.getRight();
      } else if (root.getRight() == null) {
        return root.getLeft();
      } else {
        getNewRoot(root, root.getLeft(), root);
        root.setLeft(root.getLeft());
        root.setRight(root.getRight());
        return root;
      }
    } else {
      NodeWithParent nodeWithParent = findNode(root, root, key);
      if (nodeWithParent == null) {
        return root;
      }
      if (nodeWithParent.getNodeToRemove().getLeft() == null &&
          nodeWithParent.getNodeToRemove().getRight() == null) {
        if (nodeWithParent.getParent().getLeft() == nodeWithParent.getNodeToRemove()) {
          nodeWithParent.getParent().setLeft(null);
        }
        if (nodeWithParent.getParent().getRight() == nodeWithParent.getNodeToRemove()) {
          nodeWithParent.getParent().setRight(null);
        }
        return root;
      } else if (nodeWithParent.getNodeToRemove().getLeft() == null) {
        if (nodeWithParent.getParent().getLeft() == nodeWithParent.getNodeToRemove()) {
          nodeWithParent.getParent().setLeft(nodeWithParent.getNodeToRemove().getRight());
        }
        if (nodeWithParent.getParent().getRight() == nodeWithParent.getNodeToRemove()) {
          nodeWithParent.getParent().setRight(nodeWithParent.getNodeToRemove().getRight());
        }
        return root;
      } else if (nodeWithParent.getNodeToRemove().getRight() == null) {
        if (nodeWithParent.getParent().getLeft() == nodeWithParent.getNodeToRemove()) {
          nodeWithParent.getParent().setLeft(nodeWithParent.getNodeToRemove().getLeft());
        }
        if (nodeWithParent.getParent().getRight() == nodeWithParent.getNodeToRemove()) {
          nodeWithParent.getParent().setRight(nodeWithParent.getNodeToRemove().getLeft());
        }
        return root;
      } else {
        getNewRoot(nodeWithParent.getParent(), nodeWithParent.getNodeToRemove(), nodeWithParent.getNodeToRemove());
        nodeWithParent.getNodeToRemove().setLeft(nodeWithParent.getNodeToRemove().getLeft());
        if (nodeWithParent.getParent().getLeft() == nodeWithParent.getNodeToRemove()) {
          nodeWithParent.getParent().setLeft(nodeWithParent.getNodeToRemove());
        }
        if (nodeWithParent.getParent().getRight() == nodeWithParent.getNodeToRemove()) {
          nodeWithParent.getParent().setRight(nodeWithParent.getNodeToRemove());
        }
        return root;
      }
    }
  }


  private static void getNewRoot(Node parent, Node leftNode, Node root) {
    Node currentParent = parent;
    Node currentLeftNode = leftNode;
    boolean foundNewRoot = false;
    while (!foundNewRoot) {
      if (currentLeftNode.getLeft() == null && currentLeftNode.getRight() == null) {
        currentParent.setRight(null);
        foundNewRoot = true;
        root.setValue(currentLeftNode.getValue());
        currentLeftNode = null;
      } else if (currentLeftNode.getLeft() == null) {
        currentParent = currentLeftNode;
        currentLeftNode = currentLeftNode.getRight();
      } else if (currentLeftNode.getRight() == null) {
        currentParent.setLeft(null);
        foundNewRoot = true;
        root.setValue(currentLeftNode.getValue());
        currentLeftNode = null;
      } else {
        currentParent = currentLeftNode;
        currentLeftNode = currentLeftNode.getRight();
      }
    }
  }

  private static NodeWithParent findNode(Node parent, Node currentRoot, int value) {

    Node newParent = parent;
    Node newCurrentRoot = currentRoot;
    NodeWithParent foundNodeWithParent = null;

    while (foundNodeWithParent == null) {
      if (newCurrentRoot == null) {
        return null;
      }
      if (value < newCurrentRoot.getValue()) {
        newParent = newCurrentRoot;
        if (newCurrentRoot.getLeft() != null) {
          newCurrentRoot = newCurrentRoot.getLeft();
        } else {
          return null;
        }
      }
      if (value == newCurrentRoot.getValue()) {
        return new NodeWithParent(newCurrentRoot, newParent);
      }
      if (value > newCurrentRoot.getValue()) {
        newParent = newCurrentRoot;
        if (newCurrentRoot.getRight() != null) {
          newCurrentRoot = newCurrentRoot.getRight();
        } else {
          return null;
        }
      }
    }

    return null;
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

    private Node nodeToRemove;
    private Node parent;

    public NodeWithParent(Node nodeToRemove, Node parent) {
      this.nodeToRemove = nodeToRemove;
      this.parent = parent;
    }

    public Node getNodeToRemove() {
      return nodeToRemove;
    }

    public void setNodeToRemove(Node nodeToRemove) {
      this.nodeToRemove = nodeToRemove;
    }

    public Node getParent() {
      return parent;
    }

    public void setParent(Node parent) {
      this.parent = parent;
    }
  }
}