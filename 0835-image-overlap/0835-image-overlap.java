import java.util.*;

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<Integer> points1 = new ArrayList<>();
        List<Integer> points2 = new ArrayList<>();

       
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (img1[r][c] == 1) points1.add(r * 100 + c);
                if (img2[r][c] == 1) points2.add(r * 100 + c);
            }
        }

       
        int[][] count = new int[2 * n + 1][2 * n + 1];
        int maxOverlap = 0;

        for (int p1 : points1) {
            int r1 = p1 / 100, c1 = p1 % 100;
            for (int p2 : points2) {
                int r2 = p2 / 100, c2 = p2 % 100;
                
                int dr = r2 - r1 + n;
                int dc = c2 - c1 + n;
                
                count[dr][dc]++;
                maxOverlap = Math.max(maxOverlap, count[dr][dc]);
            }
        }

        return maxOverlap;
    }
}