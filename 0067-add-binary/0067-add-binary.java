class Solution {
    public String addBinary(String a, String b) {
        int n = Math.max(a.length(), b.length());
        char[] res = new char[n + 1];

        int i = a.length() - 1;
        int j = b.length() - 1;
        int k = n;
        int carry = 0;

        while (i >= 0 || j >= 0) {
            int sum = carry;

            if (i >= 0) sum += a.charAt(i--) - '0';
            if (j >= 0) sum += b.charAt(j--) - '0';

            res[k--] = (char) ('0' + (sum & 1));
            carry = sum >> 1;
        }

        if (carry != 0) {
            res[k] = '1';
            return new String(res);
        }

        return new String(res, k + 1, n - k);
    }
}