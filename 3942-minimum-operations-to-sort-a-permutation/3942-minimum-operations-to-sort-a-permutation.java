import java.util.Arrays;

class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;

        // Check if it's a cyclic rotation of [0, 1, ..., n-1]
        int zeroIdx = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zeroIdx = i;
                break;
            }
        }

        boolean isIncRotation = true;
        for (int i = 0; i < n; i++) {
            if (nums[(zeroIdx + i) % n] != i) {
                isIncRotation = false;
                break;
            }
        }

        // Check if it's a cyclic rotation of [n-1, n-2, ..., 0]
        int maxIdx = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == n - 1) {
                maxIdx = i;
                break;
            }
        }

        boolean isDecRotation = true;
        for (int i = 0; i < n; i++) {
            if (nums[(maxIdx + i) % n] != n - 1 - i) {
                isDecRotation = false;
                break;
            }
        }

        if (!isIncRotation && !isDecRotation) {
            return -1;
        }

        int minOps = Integer.MAX_VALUE;

        if (isIncRotation) {
            // Option 1: Just rotate left by zeroIdx
            minOps = Math.min(minOps, zeroIdx);
            // Option 2: Reverse, then rotate, then reverse
            // Reversing takes 1 op, rotating takes (n - zeroIdx) % n ops, reversing takes 1 op
            minOps = Math.min(minOps, 2 + (n - zeroIdx) % n);
        }

        if (isDecRotation) {
            // Option 1: Reverse first (1 op), then rotate
            minOps = Math.min(minOps, 1 + (n - maxIdx) % n);
            // Option 2: Rotate first, then reverse (1 op)
            minOps = Math.min(minOps, maxIdx + 1);
        }

        return minOps;
    }
}