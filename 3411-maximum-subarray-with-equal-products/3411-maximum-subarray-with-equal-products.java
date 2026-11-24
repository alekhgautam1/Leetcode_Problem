class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int maxLen = 2; 
        int prod = 1;
        int left = 0;

        for (int right = 0; right < n; right++) {
           while (gcd(prod, nums[right]) > 1) {
                prod /= nums[left];
                left++;
            }
            
            prod *= nums[right];
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}