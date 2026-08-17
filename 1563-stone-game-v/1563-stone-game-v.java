import java.util.Arrays;

class Solution {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }
        
        int[][] memo = new int[n][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        
        return solve(0, n - 1, prefix, memo);
    }
    
    private int solve(int i, int j, int[] prefix, int[][] memo) {
        if (i == j) {
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        
        int maxScore = 0;
        
        // Iterate through all possible split points
        for (int p = i; p < j; p++) {
            int leftSum = prefix[p + 1] - prefix[i];
            int rightSum = prefix[j + 1] - prefix[p + 1];
            
            if (leftSum < rightSum) {
                // Bob throws away the right row, Alice takes leftSum
                maxScore = Math.max(maxScore, leftSum + solve(i, p, prefix, memo));
            } else if (leftSum > rightSum) {
                // Bob throws away the left row, Alice takes rightSum
                maxScore = Math.max(maxScore, rightSum + solve(p + 1, j, prefix, memo));
            } else {
                // Sums are equal, Alice chooses the side that maximizes her outcome
                int chooseLeft = leftSum + solve(i, p, prefix, memo);
                int chooseRight = rightSum + solve(p + 1, j, prefix, memo);
                maxScore = Math.max(maxScore, Math.max(chooseLeft, chooseRight));
            }
        }
        
        return memo[i][j] = maxScore;
    }
}