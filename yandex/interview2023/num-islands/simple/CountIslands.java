public class CountIslands {


    static int countIslands(int[][] grid) {

        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    count++;
                    updateGrid(grid, i, j);
                }
            }
        }

        return count;
    }

    static void updateGrid(int[][] grid, int x, int y) {
        if (x < 0 || y <0 || x >= grid.length || y >= grid[0].length || grid[x][y] == 0) {
            return;
        }
        grid[x][y] = 0;
        updateGrid(grid, x + 1, y);
        updateGrid(grid, x - 1, y);
        updateGrid(grid, x, y + 1);
        updateGrid(grid, x, y - 1);
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0, 1, 0},
                {0, 0, 1},
                {0, 1, 1}
        };

        System.out.println(countIslands(grid));
    }
}
