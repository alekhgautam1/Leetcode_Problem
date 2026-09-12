import java.util.*;

class Solution {

    static class State {
        long weight;
        int[] indices;

        State(long weight, int[] indices) {
            this.weight = weight;
            this.indices = indices;
        }
    }

    private static final State EMPTY = new State(0, new int[0]);

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        int[][] sorted = new int[n][4];

        // 1. Unpack input and store original indices
        for (int i = 0; i < n; i++) {
            List<Integer> interval = intervals.get(i);
            sorted[i][0] = interval.get(0);
            sorted[i][1] = interval.get(1);
            sorted[i][2] = interval.get(2);
            sorted[i][3] = i;
        }

        Arrays.sort(sorted, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

        int[] next = new int[n];
        for (int i = 0; i < n; i++) {
            int targetR = sorted[i][1];
            int low = i + 1, high = n;
            while (low < high) {
                int mid = (low + high) >>> 1;
                if (sorted[mid][0] > targetR) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
            next[i] = low;
        }

        State[][] dp = new State[n + 1][5];
        for (int k = 0; k <= 4; k++) {
            dp[n][k] = EMPTY;
        }
        for (int i = 0; i <= n; i++) {
            dp[i][0] = EMPTY;
        }

        for (int i = n - 1; i >= 0; i--) {
            int nextIdx = next[i];
            int origIdx = sorted[i][3];
            long w = sorted[i][2];

            for (int k = 1; k <= 4; k++) {
                State skip = dp[i + 1][k];
                State nextState = dp[nextIdx][k - 1];

                long pickWeight = w + nextState.weight;
                int[] pickIndices = addAndSort(nextState.indices, origIdx);

                boolean pickBetter;
                if (pickWeight > skip.weight) {
                    pickBetter = true;
                } else if (pickWeight < skip.weight) {
                    pickBetter = false;
                } else {
                    pickBetter = compareIndices(pickIndices, skip.indices) < 0;
                }

                dp[i][k] = pickBetter ? new State(pickWeight, pickIndices) : skip;
            }
        }

        return dp[0][4].indices;
    }

    private static int[] addAndSort(int[] prev, int val) {
        int len = prev.length;
        int[] res = new int[len + 1];
        int i = 0;
        while (i < len && prev[i] < val) {
            res[i] = prev[i];
            i++;
        }
        res[i] = val;
        while (i < len) {
            res[i + 1] = prev[i];
            i++;
        }
        return res;
    }
    private static int compareIndices(int[] a, int[] b) {
        int len = Math.min(a.length, b.length);
        for (int i = 0; i < len; i++) {
            if (a[i] != b[i]) {
                return a[i] - b[i];
            }
        }
        return a.length - b.length;
    }
}