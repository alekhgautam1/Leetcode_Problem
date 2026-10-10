import java.util.Arrays;

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long[] diff = new long[n];
        long totalDiff = 0;
        long maxDiff = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs((long) nums1[i] - nums2[i]);
            totalDiff += diff[i];
            maxDiff = Math.max(maxDiff, diff[i]);
        }

        long k = (long) k1 + k2;
        
        if (totalDiff <= k) {
            return 0;
        }

        long low = 0, high = maxDiff, target = maxDiff;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (canReduce(diff, mid, k)) {
                target = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

       long remaining = k;
        for (int i = 0; i < n; i++) {
            if (diff[i] > target) {
                remaining -= (diff[i] - target);
                diff[i] = target;
            }
        }


        for (int i = 0; i < n && remaining > 0; i++) {
            if (diff[i] == target && target > 0) {
                diff[i]--;
                remaining--;
            }
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += diff[i] * diff[i];
        }

        return ans;
    }

    private boolean canReduce(long[] diff, long target, long k) {
        long ops = 0;
        for (long d : diff) {
            if (d > target) {
                ops += (d - target);
            }
        }
        return ops <= k;
    }
}