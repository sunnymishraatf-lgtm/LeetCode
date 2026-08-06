// Last updated: 07/08/2026, 00:12:36
class Solution {
    public List<List<Integer>> aggregateTimeSeries(int[][] series1, int[][] series2) {
        int n = series1.length, m = series2.length;
        int i = 0, j = 0;
        int p1 = 0, p2 = 0;

        List<List<Integer>> ans = new ArrayList<>();

        while (i < n || j < m) {
            int t;

            if (j == m || (i < n && series1[i][0] < series2[j][0])) {
                t = series1[i][0];
                i++;
            } else if (i == n || series2[j][0] < series1[i][0]) {
                t = series2[j][0];
                j++;
            } else {
                t = series1[i][0];
                i++;
                j++;
            }

            while (p1 < n && series1[p1][0] < t) p1++;
            while (p2 < m && series2[p2][0] < t) p2++;

            long v1 = (p1 < n) ? series1[p1][1] : 0;
            long v2 = (p2 < m) ? series2[p2][1] : 0;

            ans.add(Arrays.asList(t, (int)(v1 + v2)));
        }

        return ans;
    }
}