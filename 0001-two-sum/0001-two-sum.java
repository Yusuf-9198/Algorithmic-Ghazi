class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] ans= {-1,-1};
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int remain = target - nums[i];
            if(map.containsKey(remain)){
                ans[0] = i;
                ans[1] = map.get(remain);
                break;
            }else map.put(nums[i],i);
        }

        return ans;
    }
}