import java.util.Arrays;

class Solution {
    private static final long MOD1 = 1_000_000_007L;
    private static final long MOD2 = 1_000_000_009L;
    private static final long BASE1 = 131_542_391L;
    private static final long BASE2 = 265_443_576L;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        
        // Precompute prefix hashes and base powers in O(n)
        long[] h1 = new long[n + 1];
        long[] h2 = new long[n + 1];
        long[] p1 = new long[n + 1];
        long[] p2 = new long[n + 1];
        
        p1[0] = 1;
        p2[0] = 1;
        
        for (int i = 0; i < n; i++) {
            // +1 ensures 0 values (if any) still contribute distinctly
            long val = nums[i] + 1L;
            h1[i + 1] = (h1[i] * BASE1 + val) % MOD1;
            h2[i + 1] = (h2[i] * BASE2 + val) % MOD2;
            p1[i + 1] = (p1[i] * BASE1) % MOD1;
            p2[i + 1] = (p2[i] * BASE2) % MOD2;
        }

        // Reusable primitive array to avoid object allocation inside binary search
        long[] subHashes = new long[n];

        int low = 1, high = n, ans = n;
        while (low <= high) {
            int mid = low + ((high - low) >>> 1);
            if (hasUniqueSubarray(mid, n, h1, h2, p1, p2, subHashes)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private boolean hasUniqueSubarray(int len, int n, long[] h1, long[] h2, 
                                      long[] p1, long[] p2, long[] subHashes) {
        int count = n - len + 1;
        
        for (int i = 0; i < count; i++) {
            int j = i + len;
            
            long hash1 = (h1[j] - (h1[i] * p1[len]) % MOD1 + MOD1) % MOD1;
            long hash2 = (h2[j] - (h2[i] * p2[len]) % MOD2 + MOD2) % MOD2;
            
            subHashes[i] = (hash1 << 32) | hash2;
        }

       Arrays.sort(subHashes, 0, count);

        for (int i = 0; i < count; i++) {
            boolean sameAsPrev = (i > 0 && subHashes[i] == subHashes[i - 1]);
            boolean sameAsNext = (i + 1 < count && subHashes[i] == subHashes[i + 1]);
            if (!sameAsPrev && !sameAsNext) {
                return true;
            }
        }
        return false;
    }
}