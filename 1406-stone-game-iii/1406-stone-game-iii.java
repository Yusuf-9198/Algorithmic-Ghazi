import java.util.Arrays;

class Solution {
    int[] memo;

    int solve(int[] stoneValue, int i) {
        int n = stoneValue.length;
        if (i >= n) return 0;
        if (memo[i] != Integer.MIN_VALUE) return memo[i];

        int maxDiff = Integer.MIN_VALUE;
        int currentTake = 0;

        for (int k = 0; k < 3 && i + k < n; k++) {
            currentTake += stoneValue[i + k];
            int diff = currentTake - solve(stoneValue, i + k + 1);
            maxDiff = Math.max(maxDiff, diff);
        }

        return memo[i] = maxDiff;
    }

    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        memo = new int[n];
        Arrays.fill(memo, Integer.MIN_VALUE);

        int aliceDiff = solve(stoneValue, 0);

        if (aliceDiff > 0) return "Alice";
        if (aliceDiff < 0) return "Bob";
        return "Tie";
    }
}