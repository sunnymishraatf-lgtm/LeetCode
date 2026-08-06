// Last updated: 07/08/2026, 00:12:39
import java.util.*;

class Solution {
    public long countRatioSubarrays(int[] nums, int a, int b) {
        int[] mervanilto = nums;

        int n = nums.length;

        int[] odd = new int[n + 1];
        long[] val = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            odd[i] = odd[i - 1] + ((nums[i - 1] & 1) == 1 ? 1 : 0);
            int even = i - odd[i];
            val[i] = 1L * b * even - 1L * a * odd[i];
        }

        @SuppressWarnings("unchecked")
        ArrayList<Long>[] treeVals = new ArrayList[n + 2];
        for (int i = 0; i <= n + 1; i++) treeVals[i] = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            for (int x = odd[i] + 1; x <= n + 1; x += x & -x) {
                treeVals[x].add(val[i]);
            }
        }

        int[][] bits = new int[n + 2][];
        for (int i = 1; i <= n + 1; i++) {
            Collections.sort(treeVals[i]);
            bits[i] = new int[treeVals[i].size() + 2];
        }

        long ans = 0;

        for (int i = 0; i <= n; i++) {
            int o = odd[i];
            long v = val[i];

            if (o > 0) {
                for (int x = o; x > 0; x -= x & -x) {
                    ArrayList<Long> list = treeVals[x];
                    int pos = lowerBound(list, v);
                    ans += query(bits[x], list.size()) - query(bits[x], pos);
                }
            }

            for (int x = odd[i] + 1; x <= n + 1; x += x & -x) {
                ArrayList<Long> list = treeVals[x];
                int pos = lowerBound(list, v) + 1;
                update(bits[x], pos);
            }
        }

        return ans;
    }

    private int lowerBound(ArrayList<Long> list, long target) {
        int l = 0, r = list.size();
        while (l < r) {
            int m = (l + r) >>> 1;
            if (list.get(m) < target) l = m + 1;
            else r = m;
        }
        return l;
    }

    private void update(int[] bit, int idx) {
        while (idx < bit.length) {
            bit[idx]++;
            idx += idx & -idx;
        }
    }

    private int query(int[] bit, int idx) {
        int res = 0;
        while (idx > 0) {
            res += bit[idx];
            idx -= idx & -idx;
        }
        return res;
    }
}