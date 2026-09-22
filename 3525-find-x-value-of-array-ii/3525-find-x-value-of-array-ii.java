class Solution {
    private int[] prod;
    private int[][] cnt;
    private int n;
    private int K;

    private void merge(int node, int left, int right) {
        prod[node] = (prod[left] * prod[right]) % K;

        // Reset and aggregate left child counts
        for (int r = 0; r < K; r++) {
            cnt[node][r] = cnt[left][r];
        }

        // Merge right child counts shifted by left child product
        int leftProd = prod[left];
        for (int r = 0; r < K; r++) {
            int rightCnt = cnt[right][r];
            if (rightCnt > 0) {
                cnt[node][(leftProd * r) % K] += rightCnt;
            }
        }
    }

    private void build(int node, int start, int end, int[] nums) {
        if (start == end) {
            int val = nums[start] % K;
            prod[node] = val;
            cnt[node][val] = 1;
            return;
        }
        int mid = (start + end) >>> 1;
        int leftNode = node << 1;
        int rightNode = leftNode | 1;

        build(leftNode, start, mid, nums);
        build(rightNode, mid + 1, end, nums);
        merge(node, leftNode, rightNode);
    }

    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            int rem = val % K;
            prod[node] = rem;
            for (int r = 0; r < K; r++) {
                cnt[node][r] = 0;
            }
            cnt[node][rem] = 1;
            return;
        }
        int mid = (start + end) >>> 1;
        int leftNode = node << 1;
        int rightNode = leftNode | 1;

        if (idx <= mid) {
            update(leftNode, start, mid, idx, val);
        } else {
            update(rightNode, mid + 1, end, idx, val);
        }
        merge(node, leftNode, rightNode);
    }

    // Helper method to merge query results directly into output parameters without object allocation
    private void mergeQueryResults(int[] resCnt, int[] leftCnt, int leftProd, int[] rightCnt, int rightProd) {
        for (int r = 0; r < K; r++) {
            resCnt[r] = leftCnt[r];
        }
        for (int r = 0; r < K; r++) {
            if (rightCnt[r] > 0) {
                resCnt[(leftProd * r) % K] += rightCnt[r];
            }
        }
    }

    private int queryX(int node, int start, int end, int l, int r, int targetX, int[] tempProd) {
        if (l <= start && end <= r) {
            tempProd[0] = prod[node];
            return cnt[node][targetX];
        }

        int mid = (start + end) >>> 1;
        int leftNode = node << 1;
        int rightNode = leftNode | 1;

        if (r <= mid) {
            return queryX(leftNode, start, mid, l, r, targetX, tempProd);
        }
        if (l > mid) {
            return queryX(rightNode, mid + 1, end, l, r, targetX, tempProd);
        }

        // If range spans both left and right, query subtrees
        int[] leftProd = new int[1];
        int[] rightProd = new int[1];

        // Retrieve full state arrays for left and right ranges
        int[] leftCnt = getRangeCnt(leftNode, start, mid, l, r, leftProd);
        int[] rightCnt = getRangeCnt(rightNode, mid + 1, end, l, r, rightProd);

        tempProd[0] = (leftProd[0] * rightProd[0]) % K;

        // Calculate count for targetX directly without full array copy
        int res = leftCnt[targetX];
        for (int rRem = 0; rRem < K; rRem++) {
            if (rightCnt[rRem] > 0 && (leftProd[0] * rRem) % K == targetX) {
                res += rightCnt[rRem];
            }
        }
        return res;
    }

    private int[] getRangeCnt(int node, int start, int end, int l, int r, int[] tempProd) {
        if (l <= start && end <= r) {
            tempProd[0] = prod[node];
            return cnt[node];
        }
        int mid = (start + end) >>> 1;
        int leftNode = node << 1;
        int rightNode = leftNode | 1;

        if (r <= mid) {
            return getRangeCnt(leftNode, start, mid, l, r, tempProd);
        }
        if (l > mid) {
            return getRangeCnt(rightNode, mid + 1, end, l, r, tempProd);
        }

        int[] leftProd = new int[1];
        int[] rightProd = new int[1];
        int[] leftCnt = getRangeCnt(leftNode, start, mid, l, r, leftProd);
        int[] rightCnt = getRangeCnt(rightNode, mid + 1, end, l, r, rightProd);

        tempProd[0] = (leftProd[0] * rightProd[0]) % K;
        int[] mergedCnt = new int[5];
        mergeQueryResults(mergedCnt, leftCnt, leftProd[0], rightCnt, rightProd[0]);
        return mergedCnt;
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.K = k;

        this.prod = new int[4 * n];
        this.cnt = new int[4 * n][5];

        build(1, 0, n - 1, nums);

        int q = queries.length;
        int[] ans = new int[q];
        int[] tempProd = new int[1];

        for (int i = 0; i < q; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            update(1, 0, n - 1, idx, val);
            ans[i] = queryX(1, 0, n - 1, start, n - 1, x, tempProd);
        }

        return ans;
    }
}