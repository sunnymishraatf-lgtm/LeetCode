// Last updated: 07/08/2026, 00:12:31
class Solution {
    static final int MOD = 1_000_000_007;

    public int countValidSequences(int n, int k) {
        if (k > n) return 0;

        long[] fact = new long[n + 1];
        long[] invFact = new long[n + 1];

        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }

        invFact[n] = modPow(fact[n], MOD - 2);
        for (int i = n - 1; i >= 0; i--) {
            invFact[i] = invFact[i + 1] * (i + 1) % MOD;
        }

        long total = nCr(n - 1, k - 1, fact, invFact);

        long odd = 0;
        if (((n - k) & 1) == 0) {
            int top = (n + k - 2) / 2;
            odd = nCr(top, k - 1, fact, invFact);
        }

        return (int) ((total - odd + MOD) % MOD);
    }

    private long nCr(int n, int r, long[] fact, long[] invFact) {
        if (r < 0 || r > n) return 0;
        return fact[n] * invFact[r] % MOD * invFact[n - r] % MOD;
    }

    private long modPow(long a, long e) {
        long res = 1;
        while (e > 0) {
            if ((e & 1) == 1) res = res * a % MOD;
            a = a * a % MOD;
            e >>= 1;
        }
        return res;
    }
}