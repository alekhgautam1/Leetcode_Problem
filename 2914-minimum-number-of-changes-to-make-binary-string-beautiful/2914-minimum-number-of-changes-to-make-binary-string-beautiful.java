class Solution {
    public int minChanges(String s) {
        int changes = 0;
        int n = s.length();
        
        for (int i = 0; i < n; i += 2) {
            if (s.charAt(i) != s.charAt(i + 1)) {
                changes++;
            }
        }
        
        return changes;
    }
}