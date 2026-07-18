class Solution {
    public int findGCD(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        if(nums[n-1] % nums[0] == 0) return nums[0];
        int hcf = 1;
        // for(int i =1 ; i<nums[n-1]*nums[0];i++){
        for(int i = nums[0] ; i>1 ; i--){    
            if(nums[n-1] % i ==0 && nums[0] % i ==0) return i;
        }
        return hcf;
    }
}