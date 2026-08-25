class Solution {
    public int missingMultiple(int[] nums, int k) {
        int n = nums.length;
        int ans = k;
        Set<Integer> set = new HashSet<>();
        for(int num:nums) set.add(num);
        for(int i =0 ; i< set.size() ;i++){
            if(set.contains(ans)) ans += k;
            else break;
        }
        return ans;
        
    }
}