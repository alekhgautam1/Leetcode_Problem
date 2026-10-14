class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > 9 * (long) num || sum < 0) {
            return "";
        }
        
        char[] result = new char[num];
        for (int i = 0; i < num; i++) {
            int digit = Math.min(9, sum);
            result[i] = (char) ('0' + digit);
            sum -= digit;
        }
        
        if (sum > 0) {
            return "";
        }
        
        return new String(result);
    }
}