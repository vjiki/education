public class CountIslandsMaxSize {

    static int countIslands(int[][] grid) {

        int maxSize = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    int size = updateGrid(grid, i, j);
                    maxSize = Math.max(maxSize, size);
                }
            }
        }

        return maxSize;
    }

    static int updateGrid(int[][] grid, int x, int y) {
        if (x < 0 || y <0 || x >= grid.length || y >= grid[0].length || grid[x][y] == 0 || grid[x][y] == 2) {
            return 0;
        }
        grid[x][y] = 2;
        return (1 + updateGrid(grid, x + 1, y) + updateGrid(grid, x, y + 1) + updateGrid(grid, x, y - 1) + updateGrid(grid, x -1, y));
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1}
        };

        System.out.println(countIslands(grid));
    }
}
