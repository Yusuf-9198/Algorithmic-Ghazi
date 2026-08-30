class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        int minIndx = 0, maxIndx = 0;
        for (int i = 0; i < n; i++) {
            if (nums[minIndx] > nums[i]) minIndx = i;
            if (nums[maxIndx] < nums[i]) maxIndx = i;
        }

        int first = Math.min(minIndx, maxIndx);
        int second = Math.max(minIndx, maxIndx);

        // Calculate all 3 valid strategies and pick the minimum
        int bothFromLeft = second + 1;
        int bothFromRight = n - first;
        int splitBothSides = (first + 1) + (n - second);

        return Math.min(bothFromLeft, Math.min(bothFromRight, splitBothSides));
    }
}