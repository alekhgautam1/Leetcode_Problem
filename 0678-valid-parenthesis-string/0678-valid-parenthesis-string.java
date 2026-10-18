class Solution {
    public boolean checkValidString(String s) {
        int cmin = 0;
        int cmax = 0; 

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                cmin++;
                cmax++;
            } else if (c == ')') {
                cmin = Math.max(0, cmin - 1);
                cmax--;
            } else { // c == '*'
                cmin = Math.max(0, cmin - 1); // Treat '*' as ')'
                cmax++;                       // Treat '*' as '('
            }
            
            if (cmax < 0) {
                return false;
            }
        }

       return cmin == 0;
    }
}