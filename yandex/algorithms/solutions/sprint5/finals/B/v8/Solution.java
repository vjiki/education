// https://contest.yandex.ru/contest/24810/run-report/96239842/

/*
-- ПРИНЦИП РАБОТЫ --
Я реализовал удаление узла из бинарного дерева поиска:
 Алгорим я реализовал на основе урока 6 Двоичные деревья поиска. Там описан алгоритм.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
При удалении мы используем самый правый узел из левого поддерева.
Когда мы удаляем вершину, мы перезаписываем туда значение заменителя (sub), после мы удаляем sub (который уже без детей)

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
В худшем случае O(h), где h высота дерева.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Алгоритм не требует дополнительной памяти. Мы только храним один объект с ссылками на парента и на ноду которую хотим удалить.

*/

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
        getNewRoot(nodeWithParent.getNodeToRemove(), nodeWithParent.getNodeToRemove().getLeft(), nodeWithParent.getNodeToRemove());
        return root;
      }
    }
  }

  static void getNewRoot(Node parent, Node leftNode, Node root) {
    Node currentParent = parent;
    Node currentLeftNode = leftNode;
    boolean foundNewRoot = false;
    boolean isRight = false;
    while (!foundNewRoot) {
      if (currentLeftNode.getLeft() == null && currentLeftNode.getRight() == null) {
        if (isRight) {
          currentParent.setRight(null);
        } else {
          currentParent.setLeft(null);
        }
        foundNewRoot = true;
        root.setValue(currentLeftNode.getValue());
        currentLeftNode = null;
      } else if (currentLeftNode.getLeft() == null) {
        currentParent = currentLeftNode;
        currentLeftNode = currentLeftNode.getRight();
        isRight = true;
      } else if (currentLeftNode.getRight() == null) {
        if (isRight) {
          currentParent.setRight(null);
        } else {
          currentParent.setLeft(null);
        }
        foundNewRoot = true;
        root.setValue(currentLeftNode.getValue());
        currentLeftNode = null;
      } else {
        currentParent = currentLeftNode;
        isRight = true;
        currentLeftNode = currentLeftNode.getRight();
      }
    }
  }

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
}