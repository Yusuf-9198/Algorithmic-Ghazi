class Solution {
    public boolean containsDuplicate(int[] nums) {
        int n = nums.length;
        HashSet<Integer> set = new HashSet<>();
        for(int i =0 ; i< n; i++){
            set.add(nums[i]);
        }
        if(n == set.size()) return false;
        return true;
    }
}