import java.util.ArrayList;
import java.util.List;

class Solution {
    private static final int MOD = 1_000_000_007;
    private long[][] table;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        table = new long[n + 1][n + 1];
        
       for (int i = 0; i <= n; i++) {
            table[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                table[i][j] = (table[i - 1][j - 1] + table[i - 1][j]) % MOD;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

    
        return (int) (getCount(list) - 1 + MOD) % MOD;
    }

    private long getCount(List<Integer> nums) {
        if (nums.size() <= 2) {
            return 1;
        }

        int root = nums.get(0);
        List<Integer> leftTree = new ArrayList<>();
        List<Integer> rightTree = new ArrayList<>();

        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) < root) {
                leftTree.add(nums.get(i));
            } else {
                rightTree.add(nums.get(i));
            }
        }

        long leftWays = getCount(leftTree);
        long rightWays = getCount(rightTree);

        int leftSize = leftTree.size();
        int rightSize = rightTree.size();

       long ways = table[leftSize + rightSize][leftSize];

       ways = (ways * leftWays) % MOD;
        ways = (ways * rightWays) % MOD;

        return ways;
    }
}