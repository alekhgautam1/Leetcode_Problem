class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;
        
        int maxDiff = 0;
        int[] diffs = new int[n];
        long totalDiffSum = 0;
        
        for (int i = 0; i < n; i++) {
            diffs[i] = Math.abs(nums1[i] - nums2[i]);
            maxDiff = Math.max(maxDiff, diffs[i]);
            totalDiffSum += diffs[i];
        }
        if (totalDiffSum <= k) {
            return 0;
        }
        
        int[] count = new int[maxDiff + 1];
        for (int d : diffs) {
            count[d]++;
        }
        
        for (int i = maxDiff; i > 0 && k > 0; i--) {
            if (count[i] > 0) {
                long operationsNeeded = count[i];
                if (k >= operationsNeeded) {
                    k -= operationsNeeded;
                    count[i - 1] += count[i];
                    count[i] = 0;
                } else {
                    count[i] -= k;
                    count[i - 1] += (int) k;
                    k = 0;
                }
            }
        }
        
        long ans = 0;
        for (int i = 1; i <= maxDiff; i++) {
            if (count[i] > 0) {
                ans += (long) count[i] * i * i;
            }
        }
        
        return ans;
    }
}