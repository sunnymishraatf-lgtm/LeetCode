class Solution {
    public int maxProduct(int[] nums) {
        int max1 = -1;
        int max2 = -1;
        
        for (int num : nums) {
            if (num > max1) {
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max2 = num;
            }
        }
        
        return (max1 - 1) * (max2 - 1);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        
        System.out.println(sol.maxProduct(new int[]{3, 4, 5, 2}));
        System.out.println(sol.maxProduct(new int[]{1, 5, 4, 5}));
        System.out.println(sol.maxProduct(new int[]{3, 7}));
    }
}
