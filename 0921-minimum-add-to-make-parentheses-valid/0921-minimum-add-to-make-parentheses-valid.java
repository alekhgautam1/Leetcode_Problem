class Solution {
    public int minAddToMakeValid(String s) {
        int openParen = 0;
        int closeParen = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                openParen++;
            } else {
                if (openParen > 0) {
                    openParen--;
                } else {
                    closeParen++; 
                }
            }
        }

        return openParen + closeParen;
    }
}