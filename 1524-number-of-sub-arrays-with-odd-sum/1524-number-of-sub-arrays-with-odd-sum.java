class Solution {
    public int numOfSubarrays(int[] arr) {
        int MOD = 1_000_000_007;
        long totalOddSubarrays = 0;
        int currentPrefixSum = 0;
        
        int evenCount = 1;
        int oddCount = 0;
        
        for (int num : arr) {
            currentPrefixSum += num;
            
            if (currentPrefixSum % 2 == 0) {
             
                totalOddSubarrays = (totalOddSubarrays + oddCount) % MOD;
                evenCount++;
            } else {
            
                totalOddSubarrays = (totalOddSubarrays + evenCount) % MOD;
                oddCount++;
            }
        }
        
        return (int) totalOddSubarrays;
    }
}