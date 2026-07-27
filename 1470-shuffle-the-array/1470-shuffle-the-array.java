import java.util.Arrays;

class Solution {
    public int[] shuffle(int[] nums, int n) {
        int[] ans = new int[2 * n];
        for (int i = 0; i < n; i++) {
            ans[2 * i] = nums[i];
            ans[2 * i + 1] = nums[i + n];
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        
        int[] nums1 = {2, 5, 1, 3, 4, 7};
        System.out.println(Arrays.toString(sol.shuffle(nums1, 3))); 
        
        int[] nums2 = {1, 2, 3, 4, 4, 3, 2, 1};
        System.out.println(Arrays.toString(sol.shuffle(nums2, 4))); 
        
        int[] nums3 = {1, 1, 2, 2};
        System.out.println(Arrays.toString(sol.shuffle(nums3, 2))); 
    }
}
