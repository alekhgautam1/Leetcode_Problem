import java.util.List;

class Solution {
    public int sumCounts(List<Integer> nums) {
        int totalSum = 0;
        int n = nums.size();
        boolean[] seen = new boolean[101];
        
        for (int i = 0; i < n; i++) {
            int distinctCount = 0;
            java.util.Arrays.fill(seen, false);
            
            for (int j = i; j < n; j++) {
                int val = nums.get(j);
                if (!seen[val]) {
                    seen[val] = true;
                    distinctCount++;
                }
                totalSum += distinctCount * distinctCount;
            }
        }
        
        return totalSum;
    }
}