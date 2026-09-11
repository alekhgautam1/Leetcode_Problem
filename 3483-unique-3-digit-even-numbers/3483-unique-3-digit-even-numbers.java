class Solution {
    public int totalNumbers(int[] digits) {
        int[] count = new int[10];
        for (int i = 0; i < digits.length; i++) {
            count[digits[i]]++;
        }
        
        int result = 0;
        
       for (int h = 1; h <= 9; h++) {
            if (count[h] == 0) continue; 
            count[h]--;
            
            for (int t = 0; t <= 9; t++) {
                if (count[t] == 0) continue;
                count[t]--;
                
                for (int o = 0; o <= 8; o += 2) {
                    if (count[o] > 0) {
                        result++;
                    }
                }
                
                count[t]++;
            }
            
            count[h]++; 
        }
        
        return result;
    }
}