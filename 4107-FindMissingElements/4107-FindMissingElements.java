// Last updated: 07/08/2026, 00:12:50
class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        int min = 100;
        int max = 0;
        List<Integer> list = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        for (int i =0;i < nums.length ;i++){
            min = Math.min(min,nums[i]);
            max = Math.max(max,nums[i]);
            set.add(nums[i]);
        }
        for (int i = min;i <= max ; i++){
            if (!set.contains(i)){
                list.add(i);
            }
        }
        return list;
    }
}