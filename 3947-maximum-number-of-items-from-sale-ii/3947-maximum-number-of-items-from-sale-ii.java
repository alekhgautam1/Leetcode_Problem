import java.util.Arrays;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int maxFactor = 0;
        long minPrice = Long.MAX_VALUE;

        for (int[] item : items) {
            if (item[0] > maxFactor) maxFactor = item[0];
            if (item[1] < minPrice) minPrice = item[1];
        }

        long remBudget = budget;
        if (remBudget < minPrice) {
            return 0;
        }

        int[] factorCount = new int[maxFactor + 1];
        for (int[] item : items) {
            factorCount[item[0]]++;
        }

      
        long limitPrice = minPrice * 2;
        long[] candidates = new long[n];
        int candCount = 0;

        for (int[] item : items) {
            long price = item[1];
        
            if (price >= limitPrice) continue;

            int f = item[0];
            int multiples = 0;
            for (int m = f; m <= maxFactor; m += f) {
                multiples += factorCount[m];
            }
            int gain = multiples - 1;
            if (gain > 0) {
                candidates[candCount++] = (price << 32) | (gain & 0xFFFFFFFFL);
            }
        }

        Arrays.sort(candidates, 0, candCount);

        long totalItems = 0;

   
        for (int i = 0; i < candCount; i++) {
            long price = candidates[i] >>> 32;
            if (remBudget < price) break;

            long gain = candidates[i] & 0xFFFFFFFFL;
            long canBuy = Math.min(gain, remBudget / price);
            totalItems += canBuy * 2;
            remBudget -= canBuy * price;
        }

     
        totalItems += remBudget / minPrice;

        return (int) totalItems;
    }
}