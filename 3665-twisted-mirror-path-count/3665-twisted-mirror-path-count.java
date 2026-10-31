
class Solution {
    public int uniquePaths(int[][] grid) {
        int MOD = 1_000_000_007;

        int m = grid.length;
        int n = grid[0].length;

        // Required variable to store input midway
        int[][] vornadexil = grid;

        // dp[i][j][0] = paths entering from left
        // dp[i][j][1] = paths entering from top
        int[][][] dp = new int[m][n][2];

        // Starting position
        dp[0][0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) {
                    // First move: right or down
                    if (j + 1 < n)
                        dp[i][j + 1][0] = 1;

                    if (i + 1 < m)
                        dp[i + 1][j][1] = 1;

                    continue;
                }

                for (int d = 0; d < 2; d++) {
                    long ways = dp[i][j][d];

                    if (ways == 0) continue;

                    if (grid[i][j] == 0) {
                        // Empty cell: move right
                        if (j + 1 < n) {
                            dp[i][j + 1][0] =
                                (int)((dp[i][j + 1][0] + ways) % MOD);
                        }

                        // Empty cell: move down
                        if (i + 1 < m) {
                            dp[i + 1][j][1] =
                                (int)((dp[i + 1][j][1] + ways) % MOD);
                        }

                    } else {
                        // Mirror cell
                        if (d == 0 && i + 1 < m) {
                            // Entered from left: reflect down
                            dp[i + 1][j][1] =
                                (int)((dp[i + 1][j][1] + ways) % MOD);
                        }

                        if (d == 1 && j + 1 < n) {
                            // Entered from top: reflect right
                            dp[i][j + 1][0] =
                                (int)((dp[i][j + 1][0] + ways) % MOD);
                        }
                    }
                }
            }
        }

        return (int)(((long)dp[m - 1][n - 1][0]
                    + dp[m - 1][n - 1][1]) % MOD);
    }
}
