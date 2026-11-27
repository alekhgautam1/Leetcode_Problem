import java.util.Arrays;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int maxFactor = 0;
        for (int[] item : items) {
            if (item[0] > maxFactor) {
                maxFactor = item[0];
            }
        }
  int[] freq = new int[maxFactor + 1];
        for (int[] item : items) {
            freq[item[0]]++;
        }

        int[] divCount = new int[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            if (freq[f] == 0) continue;
            int totalMultiples = 0;
            for (int m = f; m <= maxFactor; m += f) {
                totalMultiples += freq[m];
            }
           divCount[f] = totalMultiples - 1;
        }

     
        int[] dp = new int[budget + 1];
        int[] curr = new int[budget + 1];

        for (int i = 0; i < n; i++) {
            int price = items[i][1];
            if (price > budget) continue;

            int firstGain = 1 + divCount[items[i][0]];
            Arrays.fill(curr, -1);
 for (int b = price; b <= budget; b++) {
                curr[b] = dp[b - price] + firstGain;
            }

         
            for (int b = price * 2; b <= budget; b++) {
                if (curr[b - price] != -1 && curr[b - price] + 1 > curr[b]) {
                    curr[b] = curr[b - price] + 1;
                }
            }

            for (int b = price; b <= budget; b++) {
                if (curr[b] > dp[b]) {
                    dp[b] = curr[b];
                }
            }
        }

        int ans = 0;
        for (int b = 0; b <= budget; b++) {
            if (dp[b] > ans) {
                ans = dp[b];
            }
        }
        return ans;
    }
}