class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;

        int minPos = 0;
        int maxPos = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minPos]) {
                minPos = i;
            }

            if (nums[i] > nums[maxPos]) {
                maxPos = i;
            }
        }

        int left = Math.min(minPos, maxPos);
        int right = Math.max(minPos, maxPos);

        int fromLeft = right + 1;
        int fromRight = n - left;
        int fromBoth = (left + 1) + (n - right);

        return Math.min(fromLeft, Math.min(fromRight, fromBoth));
    }
}