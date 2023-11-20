minimum_spanning_tree = []   # Рёбра, составляющие MST.

added = {}          # Множество вершин, уже добавленных в остов.
not_added = {}      # Множество вершины, ещё не добавленных в остов.
edges = []          # Массив рёбер, исходящих из остовного дерева.

функция add_vertex(v)
added += v
not_added -= v
# Добавляем все рёбра, которые инцидентны v, но их конец ещё не в остове.
#
# Вершины not_added стоит хранить в таком контейнере,
# чтобы проверка (end in not_added) выполнялась эффективно.
#
# Для этого подойдёт, например, хеш-таблица.
# Также в некоторых языках программирования имеется контейнер set.
edges += graph.edges.filter(start == v, end in not_added)


функция find_MST(graph):
not_added = graph.vertices

# Берём первую попавшуюся вершину.
v = graph.vertices[0]
add_vertex(v)

пока not_added не пуст и edges не пуст:
# Подразумеваем, что extract_minimum извлекает минимальное ребро
# из массива рёбер и больше данного ребра в массива не будет.
e = extract_minimum(edges)
если e.end in not_added, то:
minimum_spanning_tree += e
add_vertex(e.end)

если not_added не пуст, то
верни ошибку "Исходный граф несвязный"
иначе
верни minimum_spanning_tree