class Solution {
    private static final int MOD = 1_000_000_007;

    long[] sum;
    long[] sum1;
    long[] lazy;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sum = new long[4 * n];
        sum1 = new long[4 * n];
        lazy = new long[4 * n];

      java.util.HashMap<Integer, Integer> lastSeen = new java.util.HashMap<>();
        long totalSum = 0;
        long currentSumSquares = 0;

        for (int j = 0; j < n; j++) {
            int val = nums[j];
            int prevIndex = lastSeen.getOrDefault(val, -1);
            
       
            update(1, 0, n - 1, prevIndex + 1, j, 1);
            
        
            currentSumSquares = sum[1];
            totalSum = (totalSum + currentSumSquares) % MOD;
            
            lastSeen.put(val, j);
        }

        return (int) totalSum;
    }

    private void push(int node, int l, int r) {
        if (lazy[node] != 0) {
            int mid = (l + r) / 2;
            applyLazy(2 * node, l, mid, lazy[node]);
            applyLazy(2 * node + 1, mid + 1, r, lazy[node]);
            lazy[node] = 0;
        }
    }

    private void applyLazy(int node, int l, int r, long val) {
        long len = (r - l + 1);
       sum[node] = (sum[node] + 2 * val * sum1[node] + val * val * len) % MOD;
        sum[node] = (sum[node] + MOD) % MOD;
        
        sum1[node] = (sum1[node] + val * len) % MOD;
        sum1[node] = (sum1[node] + MOD) % MOD;
        
        lazy[node] = (lazy[node] + val) % MOD;
    }

    private void update(int node, int l, int r, int ql, int qr, long val) {
        if (ql > r || qr < l) {
            return;
        }
        if (ql <= l && r <= qr) {
            applyLazy(node, l, r, val);
            return;
        }
        push(node, l, r);
        int mid = (l + r) / 2;
        update(2 * node, l, mid, ql, qr, val);
        update(2 * node + 1, mid + 1, r, ql, qr, val);
        
        sum[node] = (sum[2 * node] + sum[2 * node + 1]) % MOD;
        sum1[node] = (sum1[2 * node] + sum1[2 * node + 1]) % MOD;
    }
}