class Solution {
    public int maximumLengthSubstring(String s) {
        int [] count = new int[26];
        int mmax =0;
        int left =0;
        for(int right =0 ; right<s.length();right++)
        {
            count[s.charAt(right)- 'a']++;
            while(count[s.charAt(right)-'a']>2)
            {
                count[s.charAt(left)- 'a']--;
                left++;
            }
            mmax = Math.max(mmax,right-left+1);
        }
     return mmax;   
    }
}