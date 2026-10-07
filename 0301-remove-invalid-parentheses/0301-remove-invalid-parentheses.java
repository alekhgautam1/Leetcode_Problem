import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        remove(s, result, 0, 0, new char[]{'(', ')'});
        return result;
    }

    private void remove(String s, List<String> result, int lastI, int lastJ, char[] par) {
        for (int stack = 0, i = lastI; i < s.length(); ++i) {
            if (s.charAt(i) == par[0]) stack++;
            if (s.charAt(i) == par[1]) stack--;
            
            if (stack >= 0) continue;

           for (int j = lastJ; j <= i; ++j) {
                if (s.charAt(j) == par[1] && (j == lastJ || s.charAt(j - 1) != par[1])) {
                    remove(s.substring(0, j) + s.substring(j + 1), result, i, j, par);
                }
            }
            return; 
        }

       String reversed = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') {
            remove(reversed, result, 0, 0, new char[]{')', '('});
        } else {
            result.add(reversed);
        }
    }
}