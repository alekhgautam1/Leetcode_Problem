import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        char[] chars = s.toCharArray();

   
        List<Integer>[] divisors = new ArrayList[n + 1];
        for (int len = 2; len <= n; len++) {
            divisors[len] = new ArrayList<>();
            for (int d = 1; d * 2 <= len; d++) {
                if (len % d == 0) {
                    divisors[len].add(d);
                }
            }
        }

        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                cost[i][j] = getMinChanges(chars, i, j, divisors[j - i + 1]);
            }
        }

    
        int INF = 1_000_000;
        int[][] dp = new int[k + 1][n];
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        for (int j = 1; j < n; j++) {
            dp[1][j] = cost[0][j];
        }

        for (int p = 2; p <= k; p++) {
            int minEnd = 2 * p - 1;
            int maxEnd = n - 1 - 2 * (k - p);

            for (int j = minEnd; j <= maxEnd; j++) {
                int minSplit = 2 * (p - 1) - 1;
                for (int split = minSplit; split <= j - 2; split++) {
                    if (dp[p - 1][split] + cost[split + 1][j] < dp[p][j]) {
                        dp[p][j] = dp[p - 1][split] + cost[split + 1][j];
                    }
                }
            }
        }

        return dp[k][n - 1];
    }

    private int getMinChanges(char[] chars, int start, int end, List<Integer> divs) {
        int len = end - start + 1;
        int minChanges = len;

        for (int d : divs) {
            int changes = 0;
           for (int offset = 0; offset < d; offset++) {
                int left = start + offset;
                int right = end - d + 1 + offset;
                while (left < right) {
                    if (chars[left] != chars[right]) {
                        changes++;
                    }
                    left += d;
                    right -= d;
                }
            }
            if (changes < minChanges) {
                minChanges = changes;
            }
           if (minChanges == 0) {
                return 0;
            }
        }

        return minChanges;
    }
}