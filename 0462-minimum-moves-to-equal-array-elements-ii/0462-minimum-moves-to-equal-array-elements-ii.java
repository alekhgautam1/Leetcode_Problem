import java.util.Random;

class Solution {
    public int minMoves2(int[] nums) {
        int n = nums.length;
        int medianIndex = n / 2;
        
        int median = quickSelect(nums, 0, n - 1, medianIndex);
        
        int moves = 0;
        for (int num : nums) {
            moves += Math.abs(num - median);
        }
        
        return moves;
    }
    
    private int quickSelect(int[] nums, int left, int right, int k) {
        if (left == right) {
            return nums[left];
        }
        
       int pivotIndex = left + new Random().nextInt(right - left + 1);
        pivotIndex = partition(nums, left, right, pivotIndex);
        
        if (pivotIndex == k) {
            return nums[k];
        } else if (pivotIndex > k) {
            return quickSelect(nums, left, pivotIndex - 1, k);
        } else {
            return quickSelect(nums, pivotIndex + 1, right, k);
        }
    }
    
    private int partition(int[] nums, int left, int right, int pivotIndex) {
        int pivotValue = nums[pivotIndex];
       swap(nums, pivotIndex, right);
        int storeIndex = left;
        
        for (int i = left; i < right; i++) {
            if (nums[i] < pivotValue) {
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }
       swap(nums, storeIndex, right);
        return storeIndex;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}