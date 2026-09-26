import java.util.*;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>((int) (knowledge.size() / 0.75f) + 1);
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder result = new StringBuilder(s.length());
        int start = -1;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                start = i; 
            } else if (c == ')') {
                String key = s.substring(start + 1, i);
                result.append(map.getOrDefault(key, "?"));
                start = -1; 
            } else if (start == -1) {
                result.append(c); 
            }
        }

        return result.toString();
    }
}