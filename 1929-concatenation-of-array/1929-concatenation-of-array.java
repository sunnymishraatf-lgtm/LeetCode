import java.util.Arrays;

class Solution {
    public int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] ans = new int[2 * n];
        
        for (int i = 0; i < n; i++) {
            ans[i] = nums[i];
            ans[i + n] = nums[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        
        int[] nums1 = {1, 2, 1};
        System.out.println(Arrays.toString(sol.getConcatenation(nums1))); 
        
        int[] nums2 = {1, 3, 2, 1};
        System.out.println(Arrays.toString(sol.getConcatenation(nums2))); 
    }
}
