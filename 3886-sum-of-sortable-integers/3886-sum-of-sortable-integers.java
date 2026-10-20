import java.util.Arrays;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);

        int totalSum = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k == 0) {
                if (isSortable(nums, sortedNums, n, k)) {
                    totalSum += k;
                }
            }
        }

        return totalSum;
    }

    private boolean isSortable(int[] nums, int[] sortedNums, int n, int k) {
        for (int i = 0; i < n; i += k) {
            if (!canRotateToSorted(nums, sortedNums, i, k)) {
                return false;
            }
        }
        return true;
    }

    private boolean canRotateToSorted(int[] nums, int[] sortedNums, int start, int k) {
        int targetMin = sortedNums[start];
        
        for (int s = 0; s < k; s++) {
           if (nums[start + s] == targetMin) {
                if (s > 0 && nums[start + s - 1] == targetMin) {
                    continue;    }
                if (isValidShift(nums, sortedNums, start, k, s)) {
                    return true;
                }
            }
        }
        
        return false;
    }

    private boolean isValidShift(int[] nums, int[] sortedNums, int start, int k, int shift) {
        for (int j = 0; j < k; j++) {
            if (nums[start + (j + shift) % k] != sortedNums[start + j]) {
                return false;
            }
        }
        return true;
    }
}