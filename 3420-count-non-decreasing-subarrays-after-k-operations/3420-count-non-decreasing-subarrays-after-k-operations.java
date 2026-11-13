import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        long ans = 0;
        long ops = 0;
        
        Deque<long[]> dq = new ArrayDeque<>();
        int r = n - 1;

        for (int l = n - 1; l >= 0; l--) {
            long val = nums[l];
            long count = 1;

            while (!dq.isEmpty() && dq.peekFirst()[0] < val) {
                long[] front = dq.pollFirst();
                ops += (val - front[0]) * front[1];
                count += front[1];
            }
            dq.addFirst(new long[]{val, count});

           while (ops > k) {
                long[] back = dq.peekLast();
                ops -= (back[0] - nums[r]);
                back[1]--;
                if (back[1] == 0) {
                    dq.pollLast();
                }
                r--;
            }

           ans += (r - l + 1);
        }

        return ans;
    }
}