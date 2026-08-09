class Solution {

    int[][] dp;
    int[] sum;

    public int stoneGameII(int[] piles) {
        int n = piles.length;

        dp = new int[n][n + 1];
        sum = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            sum[i] = sum[i + 1] + piles[i];
        }

        return solve(piles, 0, 1);
    }

    public int solve(int[] piles, int i, int M) {

        if (i >= piles.length) {
            return 0;
        }

        if (dp[i][M] != 0) {
            return dp[i][M];
        }

        if (i + 2 * M >= piles.length) {
            return dp[i][M] = sum[i];
        }

        int best = 0;

        for (int X = 1; X <= 2 * M; X++) {

            int opponent = solve(
                piles,
                i + X,
                Math.max(M, X)
            );

            int current = sum[i] - opponent;

            best = Math.max(best, current);
        }

        return dp[i][M] = best;
    }
}