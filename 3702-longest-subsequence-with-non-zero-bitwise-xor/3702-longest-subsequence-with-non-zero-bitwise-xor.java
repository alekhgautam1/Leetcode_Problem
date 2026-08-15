class Solution {
    public int longestSubsequence(int[] nums) {
        int totalXOR = 0;
        boolean hasNonZero = false;
        int n = nums.length;
        
        // Step 1: Calculate total XOR and check for non-zero elements
        for (int num : nums) {
            totalXOR ^= num;
            if (num != 0) {
                hasNonZero = true;
            }
        }
        
        // Step 2: Evaluate the three possible cases based on XOR properties
        if (totalXOR != 0) {
            return n; // Case 1: Whole array has non-zero XOR
        } else if (hasNonZero) {
            return n - 1; // Case 2: Total XOR is 0, omit one non-zero element
        } else {
            return 0; // Case 3: All elements are 0
        }
    }
}
