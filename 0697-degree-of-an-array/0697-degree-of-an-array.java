class Solution {
    public int findShortestSubArray(int[] nums) {
      
        int maxVal = 0;
        for (int num : nums) {
            if (num > maxVal) maxVal = num;
        }

        int[] count = new int[maxVal + 1];
        int[] first = new int[maxVal + 1];
        int maxDegree = 0;
        int minLength = nums.length;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (count[num] == 0) {
                first[num] = i;
            }

            count[num]++;

          
            if (count[num] > maxDegree) {
                maxDegree = count[num];
                minLength = i - first[num] + 1;
            } else if (count[num] == maxDegree) {
                minLength = Math.min(minLength, i - first[num] + 1);
            }
        }

        return minLength;
    }
}