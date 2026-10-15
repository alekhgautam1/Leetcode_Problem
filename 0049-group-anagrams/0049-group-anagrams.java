import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();
        
        Map<String, List<String>> map = new HashMap<>();
        
        for (String s : strs) {
            char[] chars = s.toCharArray();
            int[] count = new int[26];
            for (char c : chars) {
                count[c - 'a']++;
            }
            
         
            char[] keyChars = new char[26];
            for (int i = 0; i < 26; i++) {
                keyChars[i] = (char) count[i];
            }
            String key = new String(keyChars);
            
            List<String> list = map.get(key);
            if (list == null) {
                list = new ArrayList<>();
                map.put(key, list);
            }
            list.add(s);
        }
        
        return new ArrayList<>(map.values());
    }
}