class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        List<Integer> onesIndices = new ArrayList<>();
        
      
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                onesIndices.add(i);
            }
        }
        
       
        if (onesIndices.size() < k) {
            return "";
        }
        
        String result = "";
        
       
        for (int i = 0; i <= onesIndices.size() - k; i++) {
            int start = onesIndices.get(i);
            int end = onesIndices.get(i + k - 1);
            String sub = s.substring(start, end + 1);
            
            if (result.isEmpty() || sub.length() < result.length() || 
               (sub.length() == result.length() && sub.compareTo(result) < 0)) {
                result = sub;
            }
        }
        
        return result;
    }
}