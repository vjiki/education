/*
Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.



Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.
*/

/*можно сделать через рекурсию если встретился 1 то нужно отмечать сразу * например весь остров и потом прибавить счетчик а потом уже идти дальше по таблице*/


class MaxIslands {

  public static void main(String[] args) {

    char[][] grid = new char[][]{
        {'1', '1', '0', '0', '0'},
        {'1', '1', '0', '0', '0'},
        {'0', '0', '1', '0', '0'},
        {'0', '0', '0', '1', '1'}};

//    for (int i = 0; i < grid.length; i++) {
//      for (int j = 0; j < grid[i].length; j++) {
//        System.out.println(grid[i][j]);
//      }
//    }

    System.out.println(numIslands(grid));
  }

  // corner cases!!

  public static int numIslands(char[][] grid) {

    int count = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == '1') {
          updateField(grid, i, j);
          count++;
        }
      }
    }

    return count;
  }

  public static void updateField(char[][] grid, int i, int j) {
    if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length || grid[i][j] == '0' || grid[i][j] == '*') {
      return;
    }

    if (grid[i][j] == '1') {
      grid[i][j] = '*';
    }

    updateField(grid, i + 1, j);
    updateField(grid, i, j + 1);
    updateField(grid, i - 1, j);
    updateField(grid, i, j - 1);
  }

}