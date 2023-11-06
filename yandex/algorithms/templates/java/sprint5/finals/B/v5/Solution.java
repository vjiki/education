import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

class NodeWithParent {

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

  // Попробуй добавить вот такой момент для ускорения:
// код в котором заместитель усыновляет потомков удаляемого можно не делать.
// Когда мы удаляем вершину, можно перезаписать туда значение заменителя (sub),
// например del.value = sub.value и нам останется только удалить sub (который уже без детей, это просто).

  static void getNewRoot(Node parent, Node leftNode, Node root) {
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
        // TODO
//        currentParent.setLeft(null);
//        foundNewRoot = true;
//        root.setValue(currentLeftNode.getValue());
//        currentLeftNode = null;
        currentParent = currentLeftNode;
        currentLeftNode = currentLeftNode.getLeft();
      } else {
        currentParent = currentLeftNode;
        currentLeftNode = currentLeftNode.getRight();
      }
    }
  }

  //   static Node[] findNode(Node parent, Node currentRoot, int value) {
  //    if (currentRoot == null) {
  //      return null;
  //    }
  //    if (value < currentRoot.getValue()) {
  //      return findNode(currentRoot, currentRoot.getLeft(), value);
  //    }
  //    if (value == currentRoot.getValue()) {
  //      return new Node[]{currentRoot, parent};
  //    }
  //    if (value > currentRoot.getValue()) {
  //      return findNode(currentRoot, currentRoot.getRight(), value);
  //    }
  //
  //    return null;
  //  }



  static NodeWithParent findNode(Node parent, Node currentRoot, int value) {

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

//  public static void main(String[] args) {
//    Node node7 = new Node(null, null, 7);
//    Node node6 = new Node(null, null, 5);
//    Node node5 = new Node(null, null, 3);
//    Node node4 = new Node(null, null, 1);
//    Node node3 = new Node(node6, node7, 6);
//    Node node2 = new Node(node4, node5, 2);
//    Node node1 = new Node(node2, node3, 4);
//    Node newHead = remove(node1, 3);
//    Node newHead1 = remove(newHead, 2);
//    Node newHead2 = remove(newHead1, 1);
//    Node newHead3 = remove(newHead2, 6);
//    Node newHead4 = remove(newHead3, 7);
//    Node newHead5 = remove(newHead4, 4);
////    Node newHead6 = remove(newHead5, 4);
//    System.out.println(newHead5.getValue());
//    System.out.println(newHead5.getLeft());
//    System.out.println(newHead5.getRight());
//
////    System.out.println(newHead.getValue());
////    System.out.println(newHead.getLeft().getValue());
////    System.out.println(newHead.getRight().getValue());
////    System.out.println(newHead.getLeft().getLeft().getValue());
////    System.out.println(newHead.getLeft().getRight());
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


//    public static void main(String[] args) {
//    Node node7 = new Node(null, null, 7);
//    Node node6 = new Node(null, null, 5);
//    Node node5 = new Node(null, null, 3);
//    Node node4 = new Node(null, null, 1);
//    Node node3 = new Node(node6, node7, 6);
//    Node node2 = new Node(node4, node5, 2);
//    Node node1 = new Node(node2, node3, 4);
//    Node newHead = remove(node1, 6);
//    System.out.println(newHead.getValue());
//    System.out.println(newHead.getLeft().getValue());
//    System.out.println(newHead.getRight().getValue());
//    System.out.println(newHead.getRight().getLeft().getValue());
//    System.out.println(newHead.getRight().getRight());
//
//  }

  // 10
  //1 668 2 5
  //2 298 3 -1
  //3 191 -1 4
  //4 266 -1 -1
  //5 702 6 7
  //6 701 -1 -1
  //7 870 8 9
  //8 822 -1 -1
  //9 912 -1 10
  //10 932 -1 -1
  //545

//    public static void main(String[] args) {
//    Node node10 = new Node(null, null, 932);
//    Node node9 = new Node(null, node10, 912);
//    Node node8 = new Node(null, null, 822);
//    Node node7 = new Node(node8, node9, 870);
//    Node node6 = new Node(null, null, 701);
//    Node node5 = new Node(node6, node7, 702);
//    Node node4 = new Node(null, null, 266);
//    Node node3 = new Node(null, node4, 191);
//    Node node2 = new Node(node3, null, 298);
//    Node node1 = new Node(node2, node5, 668);
//    Node newHead = remove(node1, 545);
//    System.out.println(newHead.getValue());
////    System.out.println(newHead.getLeft().getValue());
////    System.out.println(newHead.getRight().getValue());
//  }

  // from solution import remove
  //from node import Node
  //
  //
  //
  //def read_tree(n):
  //    keys = set()
  //    if n == 0:
  //        return None, keys
  //    nodes = [Node() for i in range(n)]
  //    for i in range(n):
  //        idx, key, lf, rg = map(int, input().split())
  //        keys.add(key)
  //        nodes[idx - 1].value = key
  //        if lf != -1:
  //            nodes[idx - 1].left = nodes[lf - 1]
  //        if rg != -1:
  //            nodes[idx - 1].right = nodes[rg - 1]
  //    return nodes[0], keys
  //
  //
  //if __name__ == '__main__':
  //    test_type = input()
  //    n = int(input())
  //    tree, keys = read_tree(n)
  //    for key in keys:
  //        tree = remove(tree, key)
  //    if tree != None:
  //        print('FAIL: non-null')
  //    else:
  //        print('OK: null')

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      int nodesSize = readInt(reader);

      Node nodes[] = new Node[nodesSize];

      for (int i = 0; i < nodesSize; i++) {
        nodes[i] = new Node(null, null, -1);
      }

      for (int i = 0; i < nodesSize; i++) {
        String[] nodeInfoLine = reader.readLine().split(" ");
        Node left = Integer.parseInt(nodeInfoLine[2]) == -1 ? null : nodes[Integer.parseInt(nodeInfoLine[2]) - 1];
        Node right = Integer.parseInt(nodeInfoLine[3]) == -1 ? null : nodes[Integer.parseInt(nodeInfoLine[3]) - 1];
        nodes[i].setLeft(left);
        nodes[i].setRight(right);
        nodes[i].setValue(Integer.parseInt(nodeInfoLine[1]));
      }

      Node root = nodes[0];
      Node newHead = nodes[0];

//      for (int i = nodesSize-1; i >= 0; i--) {
      for (int i = 0; i < nodesSize; i++) {
        System.out.println(i);
        if (nodes[i].getValue() == 494397512) {
          newHead = remove(newHead, nodes[i].getValue());
        } else {
          newHead = remove(newHead, nodes[i].getValue());
        }
      }

      System.out.println(newHead.getValue());
      System.out.println(newHead.getLeft().getValue());
      System.out.println(newHead.getRight().getValue());
//      System.out.println(newHead.getValue());
//      System.out.println(newHead.getLeft().getValue());
//      System.out.println(newHead.getRight().getValue());



    }
  }

  private static int readInt(BufferedReader reader) throws IOException {
    return Integer.parseInt(reader.readLine());
  }

}