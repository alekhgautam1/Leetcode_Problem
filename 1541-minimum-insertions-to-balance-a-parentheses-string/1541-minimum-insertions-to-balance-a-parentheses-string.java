class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int open = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                open++;
            } else {
                if (i + 1 < n && s.charAt(i + 1) == ')') {
                    i++;
                } else {
                    insertions++; 
                }
  if (open > 0) {
                    open--;
                } else {
                    insertions++; 
                }
            }
        }

        return insertions + (open * 2);
    }
}