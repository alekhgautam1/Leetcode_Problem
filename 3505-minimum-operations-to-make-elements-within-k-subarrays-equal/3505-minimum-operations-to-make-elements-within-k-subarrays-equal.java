
import java.util.*;

class Solution {

    class MultiSet {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int size = 0;
        long sum = 0;

        void add(int val) {
            map.put(val, map.getOrDefault(val, 0) + 1);
            size++;
            sum += val;
        }

        void remove(int val) {
            int count = map.get(val);

            if (count == 1) {
                map.remove(val);
            } else {
                map.put(val, count - 1);
            }

            size--;
            sum -= val;
        }

        int first() {
            return map.firstKey();
        }

        int last() {
            return map.lastKey();
        }
    }

    MultiSet lower = new MultiSet();
    MultiSet upper = new MultiSet();

    void rebalance() {
        int target = (lower.size + upper.size + 1) / 2;

        while (lower.size > target) {
            int val = lower.last();
            lower.remove(val);
            upper.add(val);
        }

        while (lower.size < target) {
            int val = upper.first();
            upper.remove(val);
            lower.add(val);
        }
    }

    void add(int val) {
        if (lower.size == 0 || val <= lower.last()) {
            lower.add(val);
        } else {
            upper.add(val);
        }

        rebalance();
    }

    void remove(int val) {
        if (lower.map.containsKey(val)) {
            lower.remove(val);
        } else {
            upper.remove(val);
        }

        rebalance();
    }

    long getCost() {
        long median = lower.last();

        return median * lower.size - lower.sum
             + upper.sum - median * upper.size;
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        long[] cost = new long[n - x + 1];

        for (int i = 0; i < x; i++) {
            add(nums[i]);
        }

        cost[0] = getCost();

        for (int i = x; i < n; i++) {

            remove(nums[i - x]);
            add(nums[i]);

            cost[i - x + 1] = getCost();
        }

        int[] maritovexi = nums;

        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
            dp[i][0] = 0;
        }

        for (int i = n - 1; i >= 0; i--) {

            for (int j = 1; j <= k; j++) {

               dp[i][j] = dp[i + 1][j];

                if (i + x <= n &&
                    dp[i + x][j - 1] != INF) {

                    dp[i][j] = Math.min(
                        dp[i][j],
                        cost[i] + dp[i + x][j - 1]
                    );
                }
            }
        }

        return dp[0][k];
    }
}
