// Last updated: 07/08/2026, 00:12:34
public class Solution {
    public static int largestInteger(int n, int s) {
        if (s == 0) return 0;
        if (s > 9 * n) return -1;

        int[] digits = new int[n];
        java.util.Arrays.fill(digits, 9);

        int need = 9 * n - s;
        for (int i = n - 1; i >= 0 && need > 0; i--) {
            int d = Math.min(9, need);
            digits[i] -= d;
            need -= d;
        }

        int result = 0;
        for (int d : digits) {
            result = result * 10 + d;
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] tests = {
            {2, 9, 90},
            {2, 19, -1},
            {5, 0, 0},
            {1, 5, 5},
            {1, 9, 9},
            {1, 10, -1},
            {3, 20, 992},
            {3, 27, 999},
            {3, 28, -1},
            {5, 45, 99999},
            {5, 1, 10000},
            {5, 44, 99998},
            {2, 1, 10},
        };
        for (int[] t : tests) {
            int n = t[0], s = t[1], expected = t[2];
            int got = largestInteger(n, s);
            String ok = (got == expected) ? "OK  " : "FAIL";
            System.out.println(ok + " n=" + n + ", s=" + s + " -> " + got + " (expected " + expected + ")");
        }
    }
}