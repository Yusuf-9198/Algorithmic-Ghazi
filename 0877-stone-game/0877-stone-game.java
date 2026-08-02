class Solution {
    public boolean stoneGame(int[] piles) {
        return true;
        
    }
}













// class Solution {
//     public boolean stoneGame(int[] nums) {
//         int n = nums.length;
//         int[][] memo = new int[n][n];
//         return maxScoreDiff(nums, 0, n - 1, memo) >= 0;
//     }

//     private int maxScoreDiff(int[] nums, int i, int j, int[][] memo) {
//         if (i == j) return nums[i];
//         if (memo[i][j] != 0) return memo[i][j];
        
//         int takeLeft = nums[i] - maxScoreDiff(nums, i + 1, j, memo);
//         int takeRight = nums[j] - maxScoreDiff(nums, i, j - 1, memo);
        
//         return memo[i][j] = Math.max(takeLeft, takeRight);
    
        
//     }
// }