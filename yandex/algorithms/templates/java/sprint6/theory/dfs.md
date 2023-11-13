color = [white, white, ...]  # Длина массива равна числу вершин |V|.

функция DFS(v):  # v - номер вершины
color[v] = gray  # Вершина посещена, но ещё не обработана.
для каждого исходящего ребра (v,w):
возьмём вершину w
if color[w] == white:  # Если вершина не посещена, то
DFS(w)             # запустим обход от найденной смежной вершины.
color[v] = black  # Теперь вершина обработана.

функция MainDFS():
для каждого i от 0 до |V| - 1:
# Перебираем варианты стартовых вершин, пока они существуют.
if color[i] == white:
DFS(i)  # Запускаем обход, стартуя с i-й вершины.


func printReversed(node *Node) {
for _, child := range node.children {
printReversed(child)
}
fmt.Println(node.value)
} 