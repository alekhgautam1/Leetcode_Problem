class Solution {
    public int maxPalindromes(String s, int k) {
        int count = 0;
        int n = s.length();
        int i = 0;

        while (i <= n - k) {
            if (isPalindrome(s, i, i + k - 1)) {
                count++;
                i += k; 
            } 
            else if (i + k < n && isPalindrome(s, i, i + k)) {
                count++;
                i += k + 1; 
            } 
            else {
                i++; 
            }
        }

        return count;
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}