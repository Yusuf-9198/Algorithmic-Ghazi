class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int i =0 , j = n-1, k =n- 1;
        int sqr1 = 0 , sqr2 = 0;
        while(i<=j){
            sqr1 = nums[i] * nums[i];
            sqr2 = nums[j] * nums[j];
            if(sqr1 >= sqr2){
                ans[k] = sqr1;
                i++;
                k--;
            }
            else{
                ans[k] = sqr2;
                j--;
                k--;
                
            }
        }
        return ans;

    }
}