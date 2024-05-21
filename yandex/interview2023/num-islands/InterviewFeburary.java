/*
Задача 2.
Дана карта NxM (N, M <= 1000).
Где '.' - это вода, а '#' - земля
......     ......
###.##     111.22
......     .....

Земля соединяется сверху, снизу, справа и слева:
.#.#
#### - один остров
.#..

#.#
.#. - пять островов
#.#

Найди кол-во островов и размер самого большого острова.
Первым пример: 2 острова, размер 3.
Второй пример: 1 остров, размер 5.
Третий пример: 5 островов, размер 1.
*/



void numIslandsAndMaxIsland(char[][] map, int n, int m) {

  int amountOfIslands = 0;
  int maxIslandSize = 0;

  for(int i = 0; i < n; i++) {
    for(int j = 0; j < m; j++) {
      char elem = map[i][j];
      if (elem == '#') {
        amountOfIslands++;
        int max = updateMap(map, i, j, );
        if (max > maxIslandSize) {
          maxIslandSize = max;
        }
      }
    }
  }

  System.out.println("%d острова, размер %d.", amountOfIslands, maxIslandSize);

}
/*
O(n * m * C) ~ O(n * m)
1-> 4
4 * 4 -> 16
16 * 4 -> 64
64 * 4 -> 256
256 * 4 -> 1024
#.#
.#.
#.#
*/


int updateMap(char[][] map, int i, int j, int n, int m) {

  if(i < 0 || i >= n || j < 0 || j >= m || map[i][j] != '#') {
    return 0;
  }

  map[i][j] = '@';
  return (1 + updateMap(map, i, j+1, n, m) + updateMap(map, i+1, j, n, m) + updateMap(map, i, j-1, n, m) + updateMap(map, i-1, j, n, m));
}