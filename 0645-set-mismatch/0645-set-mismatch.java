class Solution {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        boolean[] seen = new boolean[n + 1];
        int duplicate = -1;
        int actualSum = 0;

        for (int num : nums) {
            if (seen[num]) {
                duplicate = num;
            }
            seen[num] = true;
            actualSum += num;
        }

        int expectedSum = n * (n + 1) / 2;
        int missing = expectedSum - (actualSum - duplicate);

        return new int[]{duplicate, missing};
    }
}