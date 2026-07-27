import java.util.Arrays;

class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] count = new int[101];
        int n = nums.length;
        int[] result = new int[n];
        
       
        for (int num : nums) {
            count[num]++;
        }
        
       
        int runningSum = 0;
        for (int i = 0; i < 101; i++) {
            int freq = count[i];
            count[i] = runningSum;
            runningSum += freq;
        }
        
      
        for (int i = 0; i < n; i++) {
            result[i] = count[nums[i]];
        }
        
        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        
        System.out.println(Arrays.toString(sol.smallerNumbersThanCurrent(new int[]{8, 1, 2, 2, 3}))); 
      
        
        System.out.println(Arrays.toString(sol.smallerNumbersThanCurrent(new int[]{6, 5, 4, 8})));   
    
        
        System.out.println(Arrays.toString(sol.smallerNumbersThanCurrent(new int[]{7, 7, 7, 7})));   
     
    }
}
