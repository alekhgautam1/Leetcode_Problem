class Solution {
    public int minOperations(List<Integer> nums, int k) {
        boolean[] collected = new boolean[k + 1];
        int count = 0;
        int n = nums.size();
        
        for (int i = n - 1; i >= 0; i--) {
            int val = nums.get(i);
            if (val <= k && !collected[val]) {
                collected[val] = true;
                count++;
                if (count == k) {
                    return n - i;
                }
            }
        }
        return n;
    }
}