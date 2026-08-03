class Solution {
    int[][] memo;

    int solve(int[] piles, int i, int j) {
        if (i > j) return 0;
        if (memo[i][j] != 0) return memo[i][j];

        int iTake = piles[i] + Math.min(solve(piles, i + 2, j), solve(piles, i + 1, j - 1));
        int jTake = piles[j] + Math.min(solve(piles, i, j - 2), solve(piles, i + 1, j - 1));

        return memo[i][j] = Math.max(iTake, jTake);
    }

    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        memo = new int[n][n];
        int sum = 0;
        for (int pile : piles) sum += pile;

        return solve(piles, 0, n - 1) > sum / 2;
    }
}

// // correct but don't known how?
// class Solution {
//     public boolean stoneGame(int[] piles) {
//         return true;
//     }
// }

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