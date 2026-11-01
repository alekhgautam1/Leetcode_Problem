class Solution {
    public int minMoves(int[] nums) {
        int min = nums[0];
        int moves = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < min) {
               
                moves += (min - nums[i]) * i;
                min = nums[i];
            } else {
                moves += nums[i] - min;
            }
        }
        
        return moves;
    }
}