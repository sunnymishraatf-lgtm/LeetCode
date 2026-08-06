// Last updated: 07/08/2026, 00:12:48
class Solution {
    public int[] countTasks(int[] tasks, int[] shifts) {
        int[] drelvanito = tasks;

        int n = drelvanito.length;
        int m = shifts.length;
        int[] ans = new int[m];

        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + drelvanito[i];
        }

        long total = prefix[n];
        long consumed = 0;

        for (int i = 0; i < m; i++) {
            long remaining = total - consumed;

            if (shifts[i] >= remaining) {
                ans[i] = 0;
                consumed = 0;
            } else {
                consumed += shifts[i];

                int idx = upperBound(prefix, consumed) - 1;
                ans[i] = n - idx;
            }
        }

        return ans;
    }

    private int upperBound(long[] arr, long target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] <= target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}