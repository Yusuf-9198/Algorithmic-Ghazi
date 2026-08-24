
class Solution {
    private int Solve(int j , int[] preSum){
        int n = preSum.length; 
        if(j == n-1) return preSum[n-1];
        int nextState = Solve(j+1,preSum);
        int take = preSum[j] - nextState;
        int skip = nextState;
        return Math.max(take,skip);
    }
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        int[] preSum = new int[n];
        preSum[0] = stones[0];
        for(int i =1 ;i<n ;i++)
            preSum[i] = preSum[i-1] + stones[i];
        return Solve(1,preSum);
    }
}

// class Solution {
//     private int n;
//     private int[] memo;
//     private int[] preSum;
//     private int solve(int j) {
//         if (j == n - 1) {
//             return preSum[n - 1];
//         }
//         if (memo[j] != Integer.MIN_VALUE) {
//             return memo[j];
//         }
//         int take = preSum[j] - solve(j + 1);
//         int skip = solve(j + 1);
//         return memo[j] = Math.max(take, skip);
//     }
//     public int stoneGameVIII(int[] stones) {
//         n = stones.length;
//         preSum = new int[n];
//         memo = new int[n];
//         Arrays.fill(memo, Integer.MIN_VALUE);
//         preSum[0] = stones[0];
//         for (int i = 1; i < n; i++) {
//             preSum[i] = preSum[i - 1] + stones[i];
//         }
//         return solve(1);
//     }
// }