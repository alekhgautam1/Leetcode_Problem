class Solution {
    public int minOperations(String s) {
        int n = s.length();
        
        boolean sorted = true;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) {
                sorted = false;
                break;
            }
        }
        if (sorted) return 0;
        
    
        if (n == 2) return -1;
        
        char minChar = s.charAt(0);
        char maxChar = s.charAt(0);
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c < minChar) minChar = c;
            if (c > maxChar) maxChar = c;
        }
        
      
        boolean minAtStart = (s.charAt(0) == minChar);
        boolean maxAtEnd = (s.charAt(n - 1) == maxChar);
        
        if (minAtStart || maxAtEnd) {
            return 1;
        }
     
        boolean onlyMaxAtStart = (s.charAt(0) == maxChar);
        boolean onlyMinAtEnd = (s.charAt(n - 1) == minChar);
        
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == maxChar) onlyMaxAtStart = false;
        }
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == minChar) onlyMinAtEnd = false;
        }
        
        if (onlyMaxAtStart && onlyMinAtEnd) {
            return 3;
        }
        
        return 2;
    }
}