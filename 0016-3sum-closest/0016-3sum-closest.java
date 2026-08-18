import java.util.Arrays;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        // Sort the array to use the two-pointer technique
        Arrays.sort(nums);
        
        // Initialize closest sum with the sum of the first three elements
        int closestSum = nums[0] + nums[1] + nums[2];
        
        // Iterate through each element as the first element of the triplet
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            
            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];
                
                // If perfect match is found, return it immediately
                if (currentSum == target) {
                    return currentSum;
                }
                
                // Update closest sum if the current sum is closer to the target
                if (Math.abs(currentSum - target) < Math.abs(closestSum - target)) {
                    closestSum = currentSum;
                }
                
                // Move pointers based on comparison with target
                if (currentSum < target) {
                    left++; // Need a larger sum
                } else {
                    right--; // Need a smaller sum
                }
            }
        }
        
        return closestSum;
    }
}