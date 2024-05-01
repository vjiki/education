"""
Дано бинарное дерево с выделенным корнем, 
 в каждой вершине которого записано по одной букве A-Z.
Две вершины считаются эквивалентными, 
 если поддеревья этих вершин содержат одинаковое множество (т.е. без учета частот) букв.
нужно найти любую пару эквивалентных вершин.

already_seen = dict() # mask -> node

      A
    /   \
   X+    B+ (B, F, D, X) -> BDX (0,1,0,1,......,1)b 576
  / \   / \
  F D   F  D (X, D) -> DX
 /          \
B            X (X, D) -> DX
              \
               D (D) -> D

               576
[0, 0, ......., 1, .....0]    -- 2**n

      A
    /   \
   X     B+
  / \    / \
  F+ D   F  B
 /          \
B            B

  X+
 /
X+

{'X', 'B', 'F'} == {'X', 'F', 'B'}

A B C D E F F
A C C F B F D
""""

class Node(NamedTuple):
    left : Optional["Node"]
    right : Optional["Node"]
    value: str


def calc_set(n: Node, already_seen):
    if n is None:
        return set()
    my_set = {n.value}.union(calc_set(n.left, already_seen)).union(calc_set(n.right, already_seen))
    node_set_hash = 0
    for c in my_set:
        idx = ord(c) - ord('A')
        node_set_hash |= 1 << idx

    if node_set_hash in already_seen:
        raise DuplicateNodesException(already_seen[node_set_hash], node)
    else:
        already_seen[node_set_hash] = node

    return my_set


def main(root):
    already_seen = dict() # mask -> node
    try:
        calc_set(root, already_seen)
    except DuplicateNodesException as e:
        return e.left, e.right

    # already_seen = dict() # mask -> node
    # for node in tree_sets.keys():
    #     node_set = tree_sets[node]

    #     node_set_hash = 0
    #     for c in node_set:
    #         idx = ord(c) - ord('A')
    #         node_set_hash |= 1 << idx

    #     if node_set_hash in already_seen:
    #         return already_seen[node_set_hash], node
    #     else:
    #         already_seen[node_set_hash] = node