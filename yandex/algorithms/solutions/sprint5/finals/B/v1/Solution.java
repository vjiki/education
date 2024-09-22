package v1;

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

  public void setValue(int value) {
    this.value = value;
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
}
// <template>

public class Solution {

  public static Node remove(Node root, int key) {
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
        Node newRoot = getNewRoot(root, root.getLeft());
        if (newRoot != null) {
          newRoot.setLeft(root.getLeft());
          newRoot.setRight(root.getRight());
        }
        return newRoot;
      }
    } else {
      Node parent = null;
      Node nodeToRemove = null;
      findNode(root, root, key, parent, nodeToRemove);
      if (nodeToRemove == null) {
        return root;
      }
      if (nodeToRemove.getLeft() == null && nodeToRemove.getRight() == null) {
        if (parent.getLeft() == nodeToRemove) {
          parent.setLeft(null);
        }
        if (parent.getRight() == nodeToRemove) {
          parent.setRight(null);
        }
        return root;
      } else if (nodeToRemove.getLeft() == null) {
        if (parent.getLeft() == nodeToRemove) {
          parent.setLeft(nodeToRemove.getRight());
        }
        if (parent.getRight() == nodeToRemove) {
          parent.setRight(nodeToRemove.getRight());
        }
        return root;
      } else if (nodeToRemove.getRight() == null) {
        if (parent.getLeft() == nodeToRemove) {
          parent.setLeft(nodeToRemove.getLeft());
        }
        if (parent.getRight() == nodeToRemove) {
          parent.setRight(nodeToRemove.getLeft());
        }
        return root;
      } else {
        Node newRoot = getNewRoot(parent, nodeToRemove);
        newRoot.setLeft(nodeToRemove.getLeft());
        if (parent.getLeft() == nodeToRemove) {
          parent.setLeft(newRoot);
        }
        if (parent.getRight() == nodeToRemove) {
          parent.setRight(newRoot);
        }
        return root;
      }
    }
  }

  static Node getNewRoot(Node parent, Node leftNode) {
    if (leftNode.getLeft() == null && leftNode.getRight() == null) {
      parent.setRight(null);
      return leftNode;
    } else if (leftNode.getLeft() == null) {
      return getNewRoot(leftNode, leftNode.getRight());
    } else if (leftNode.getRight() == null) {
      parent.setLeft(null);
      return leftNode;
    } else {
      return getNewRoot(leftNode, leftNode.getRight());
    }
  }

  static void findNode(Node parent, Node currentRoot, int value, Node newParent, Node nodeToRemove) {
    if (currentRoot == null) {
      return;
    }
    if (value < currentRoot.getValue()) {
      findNode(currentRoot, currentRoot.getLeft(), value, newParent, nodeToRemove);
    }
    if (value == currentRoot.getValue()) {
      newParent = parent;
      nodeToRemove = currentRoot;
    }
    if (value > currentRoot.getValue()) {
      findNode(currentRoot, currentRoot.getRight(), value, newParent, nodeToRemove);
    }

  }


  static void insert_node(Node root, int key) {
    if (key < root.getValue()) {
      if (root.getLeft() == null) {
        root.setLeft(new Node(null, null, key));
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

//  public static void main(String[] args) {
//    Node node1 = new Node(null, null, 2);
//    Node node2 = new Node(node1, null, 3);
//    Node node3 = new Node(null, node2, 1);
//    Node node4 = new Node(null, null, 6);
//    Node node5 = new Node(node4, null, 8);
//    Node node6 = new Node(node5, null, 10);
//    Node node7 = new Node(node3, node6, 5);
//    Node newHead = remove(node7, 10);
//    System.out.println(newHead.getValue());
//    System.out.println(newHead.getRight().getValue());
//  }

  // 10
  //1 41 2 3
  //2 20 4 5
  //3 65 7 8
  //4 11 -1 -1
  //5 29 -1 6
  //6 32 -1 -1
  //7 50 -1 -1
  //8 91 9 10
  //9 72 -1 -1
  //10 99 -1 -1
  //41

//  public static void main(String[] args) {
//    Node node10 = new Node(null, null, 99);
//    Node node9 = new Node(null, null, 72);
//    Node node8 = new Node(node9, node10, 91);
//    Node node7 = new Node(null, null, 50);
//    Node node6 = new Node(null, null, 32);
//    Node node5 = new Node(null, node6, 29);
//    Node node4 = new Node(null, null, 11);
//    Node node3 = new Node(node7, node8, 65);
//    Node node2 = new Node(node4, node5, 20);
//    Node node1 = new Node(node2, node3, 41);
//    Node newHead = remove(node1, 41);
//    System.out.println(newHead.getValue());
//    System.out.println(newHead.getLeft().getValue());
//    System.out.println(newHead.getRight().getValue());
//    System.out.println(newHead.getLeft().getRight().getValue());
//    System.out.println(newHead.getLeft().getRight().getRight());
//  }

//  public static void main(String[] args) {
//    Node node10 = new Node(null, null, 99);
//    Node node9 = new Node(null, null, 72);
//    Node node8 = new Node(node9, node10, 91);
//    Node node7 = new Node(null, null, 50);
//    Node node6 = new Node(null, null, 32);
//    Node node5 = new Node(null, node6, 29);
//    Node node4 = new Node(null, null, 11);
//    Node node3 = new Node(node7, node8, 65);
//    Node node2 = new Node(node4, node5, 20);
//    Node node1 = new Node(node2, node3, 41);
//    Node newHead = remove(node1, 543);
//    System.out.println(newHead.getValue());
//    System.out.println(newHead.getLeft().getValue());
//    System.out.println(newHead.getRight().getValue());
//  }

  // correctness
  //7
  //1 4 2 3
  //2 2 4 5
  //3 6 6 7
  //4 1 -1 -1
  //5 3 -1 -1
  //6 5 -1 -1
  //7 7 -1 -1
  //2

//  public static void main(String[] args) {
//    Node node7 = new Node(null, null, 7);
//    Node node6 = new Node(null, null, 5);
//    Node node5 = new Node(null, null, 3);
//    Node node4 = new Node(null, null, 1);
//    Node node3 = new Node(node6, node7, 6);
//    Node node2 = new Node(node4, node5, 2);
//    Node node1 = new Node(node2, node3, 4);
//    Node newHead = remove(node1, 2);
//    System.out.println(newHead.getValue());
//    System.out.println(newHead.getLeft().getValue());
//    System.out.println(newHead.getRight().getValue());
//    System.out.println(newHead.getLeft().getLeft().getValue());
//    System.out.println(newHead.getLeft().getRight());
//  }

  // 7
  //1 4 2 3
  //2 2 4 5
  //3 6 6 7
  //4 1 -1 -1
  //5 3 -1 -1
  //6 5 -1 -1
  //7 7 -1 -1
  //6

    public static void main(String[] args) {
    Node node7 = new Node(null, null, 7);
    Node node6 = new Node(null, null, 5);
    Node node5 = new Node(null, null, 3);
    Node node4 = new Node(null, null, 1);
    Node node3 = new Node(node6, node7, 6);
    Node node2 = new Node(node4, node5, 2);
    Node node1 = new Node(node2, node3, 4);
    Node newHead = remove(node1, 6);
    System.out.println(newHead.getValue());
    System.out.println(newHead.getLeft().getValue());
    System.out.println(newHead.getRight().getValue());
    System.out.println(newHead.getRight().getLeft().getValue());
    System.out.println(newHead.getRight().getRight());

  }


}