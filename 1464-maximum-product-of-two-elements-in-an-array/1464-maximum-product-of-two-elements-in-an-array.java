class Solution {
    public int maxProduct(int[] nums) {
        int n= nums.length;
        if(n ==2 ) return (nums[0]-1)*(nums[1]-1);
        // int max = 0;
        Arrays.sort(nums);
        return (nums[n-1]-1)*(nums[n-2]-1); 
    }
}