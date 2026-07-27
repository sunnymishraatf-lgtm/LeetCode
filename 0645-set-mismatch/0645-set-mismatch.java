class Solution {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int[] count = new int[n + 1];
        int dup = -1;
        int miss = -1;
        
        for (int num : nums) {
            count[num]++;
        }
        
        for (int i = 1; i <= n; i++) {
            if (count[i] == 2) {
                dup = i;
            } else if (count[i] == 0) {
                miss = i;
            }
        }
        
        return new int[]{dup, miss};
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        
        int[] nums1 = {1, 2, 2, 4};
        int[] res1 = sol.findErrorNums(nums1);
        System.out.println("[" + res1[0] + "," + res1[1] + "]"); // Output: [2,3]
        
        int[] nums2 = {1, 1};
        int[] res2 = sol.findErrorNums(nums2);
        System.out.println("[" + res2[0] + "," + res2[1] + "]"); // Output: [1,2]
    }
}
