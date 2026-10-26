class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int validIslandsCount = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    long islandSum = dfs(grid, i, j, m, n);
                    if (islandSum % k == 0) {
                        validIslandsCount++;
                    }
                }
            }
        }
        
        return validIslandsCount;
    }
    
    private long dfs(int[][] grid, int r, int c, int m, int n) {
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == 0) {
            return 0;
        }
        
        long sum = grid[r][c];
        grid[r][c] = 0; 
        
        sum += dfs(grid, r - 1, c, m, n);
        sum += dfs(grid, r + 1, c, m, n);
        sum += dfs(grid, r, c - 1, m, n);
        sum += dfs(grid, r, c + 1, m, n);
        
        return sum;
    }
}