class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] minLen = new int[n]; 
        
        int left = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;
        int bestSoFar = Integer.MAX_VALUE;
        
        for (int right = 0; right < n; right++) {
            sum += arr[right]; 
            while (sum > target && left <= right) {
                sum -= arr[left];
                left++;
            }
            
            if (sum == target) {
                int currentLen = right - left + 1;
                
               if (left > 0 && minLen[left - 1] != Integer.MAX_VALUE) {
                    result = Math.min(result, currentLen + minLen[left - 1]);
                }
                
               bestSoFar = Math.min(bestSoFar, currentLen);
            }
            
            minLen[right] = bestSoFar;
        }
        
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}