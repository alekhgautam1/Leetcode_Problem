class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] map = new String[26];
        int n = pattern.length();
        int sLen = s.length();
        int sIdx = 0;

        for (int i = 0; i < n; i++) {
            if (sIdx >= sLen) {
                return false;
            }

           int start = sIdx;
            while (sIdx < sLen && s.charAt(sIdx) != ' ') {
                sIdx++;
            }
            String word = s.substring(start, sIdx);

           if (sIdx < sLen && s.charAt(sIdx) == ' ') {
                sIdx++;
            }

            int charIdx = pattern.charAt(i) - 'a';

            if (map[charIdx] != null) {
                if (!map[charIdx].equals(word)) {
                    return false;
                }
            } else {
                for (int j = 0; j < 26; j++) {
                    if (map[j] != null && map[j].equals(word)) {
                        return false;
                    }
                }
                map[charIdx] = word;
            }
        }

        return sIdx == sLen;
    }
}