import java.util.ArrayList;
import java.util.List;

public class Solution {
   
    private static final String[] KEYPAD = {
        "",     // 0
        "",     // 1
        "abc",  // 2
        "def",  // 3
        "ghi",  // 4
        "jkl",  // 5
        "mno",  // 6
        "pqrs", // 7
        "tuv",  // 8
        "wxyz"  // 9
    };

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        
       
        if (digits == null || digits.isEmpty()) {
            return result;
        }
     
        backtrack(result, digits, new StringBuilder(), 0);
        return result;
    }

    private void backtrack(List<String> result, String digits, StringBuilder current, int index) {
       
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

       
        String letters = KEYPAD[digits.charAt(index) - '0'];
        
       
        for (int i = 0; i < letters.length(); i++) {
            current.append(letters.charAt(i));         
            backtrack(result, digits, current, index + 1); 
            current.deleteCharAt(current.length() - 1); 
        }
    }
}