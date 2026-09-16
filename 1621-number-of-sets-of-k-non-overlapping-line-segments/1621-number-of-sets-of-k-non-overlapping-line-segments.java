class Solution {
    private static final int MOD = 1_000_000_007;

    public int numberOfSets(int n, int k) {
        int N = n + k - 1;
        int R = 2 * k;

        if (R > N) return 0;

        long numerator = 1;
        long denominator = 1;

        for (int i = 1; i <= R; i++) {
            numerator = (numerator * (N - i + 1)) % MOD;
            denominator = (denominator * i) % MOD;
        }

       
        return (int) ((numerator * modInverse(denominator, MOD)) % MOD);
    }

    private long modInverse(long base, int exp) {
        return power(base, exp - 2);
    }

    private long power(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if (exp % 2 == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp /= 2;
        }
        return res;
    }
}