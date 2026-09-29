class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        if ((m + n) % 2 == 0) return false;
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') return false;

       int maxBal = (m + n) / 2;
        boolean[][][] visited = new boolean[m][n][maxBal + 1];

        return dfs(grid, 0, 0, 0, visited, m, n);
    }

    private boolean dfs(char[][] grid, int r, int c, int bal, boolean[][][] visited, int m, int n) {
        bal += (grid[r][c] == '(' ? 1 : -1);

        int remainingSteps = (m - 1 - r) + (n - 1 - c);
       if (bal < 0 || bal > remainingSteps) return false;

        if (r == m - 1 && c == n - 1) {
            return bal == 0;
        }

        if (visited[r][c][bal]) return false;
        visited[r][c][bal] = true;

       if (r + 1 < m && dfs(grid, r + 1, c, bal, visited, m, n)) return true;
        if (c + 1 < n && dfs(grid, r, c + 1, bal, visited, m, n)) return true;

        return false;
    }
}