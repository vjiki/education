class Node {
  int key;
  int height;
  Node left;
  Node right;

  public Node(int k, int h, Node l, Node r) {
    key = k;
    height = h;
    left = l;
    right = r;
  }

  public Node(int k) {
    this(k, 1, null, null);
  }


  Node small_left_rotation(Node a) {
    // Задаём обозначения.
    Node b = a.right;
    Node C = b.left;

    // Переусыновляем вершины.
    a.right = C;
    b.left = a;

    // Корректируем высоты в зависимости от того, равны ли высоты C и R.
    if (C.height == a.height) {
      a.height -= 1;
      b.height += 1;
    } else {
      a.height -= 2;
    }
    return b;
  }

  Node bigLeftRotation(Node a) {
    Node b = a.right;
    Node c = b.left;
    Node M = c.left;
    Node N = c.right;

    a.right = M;
    b.left = N;
    c.left = a;
    c.right = b;

    a.height -= 2;
    b.height -= 1;
    c.height += 1;
    return c;
  }

  public Node bigLeftRotation(Node v) {
    // Правым ребёнком становится новый корень правого поддерева.
    v.right = smallRightRotation(v.right);
    // Возвращаем новый корень поддерева.
    return smallLeftRotation(v);
  }


  Node rotate(Node vertex) {
    if (Math.abs(getHeight(vertex.left) - getHeight(vertex.right)) < 2) {
      // Вращать не надо, поддерево с вершиной vertex и так сбалансировано.
      return vertex;
    }
    if (getHeight(vertex.left) - getHeight(vertex.right) == -2) {
      // Нам нужны левые вращения.
      Node b = vertex.right;
      Node R = b.right;
      Node C = b.left;

      if (getHeight(C) <= getHeight(R)) {
        // Нужно малое левое вращение.
        return smallLeftRotation(vertex);
      } else {
        // Нужно большое левое вращение.
        return bigLeftRotation(vertex);
      }
    }
    if (getHeight(vertex.left) - getHeight(vertex.right) == 2) {
      // Нам нужны правые вращения.
      Node b = vertex.left;
      Node C = b.right;
      Node L = b.left;

      if (getHeight(C) <= getHeight(L)) {
        // Нужно малое правое вращение.
        return smallRightRotation(vertex);
      } else {
        // Нужно большое правое вращение.
        return bigRightRotation(vertex);
      }
    }
  }
}