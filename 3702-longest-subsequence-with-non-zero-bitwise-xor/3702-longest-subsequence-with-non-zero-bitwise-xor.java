class Solution {
    public int longestSubsequence(int[] nums) {
        int n = nums.length;
        int totalXor = 0;
        boolean isTolZero = true;
        for(int num : nums){
            if(num !=0) isTolZero = false;
            totalXor ^= num;
        }
        if(totalXor != 0) return n;
        else if(totalXor == 0 && isTolZero) return 0;
        return n-1;


    }
}