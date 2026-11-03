class Solution {
    public int minMoves(int[] nums) {
        int max = nums[0];
        int sum = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
            sum += nums[i];
        }
        
        return (max * nums.length) - sum;
    }
}