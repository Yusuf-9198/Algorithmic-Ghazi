// myself : correct but not optimised

class Solution {
    public int maximumProduct(int[] nums) {
        int n = nums.length;
        if (n == 3)
            return nums[0] * nums[1] * nums[2];
        Arrays.sort(nums);
        int max1 = nums[0] * nums[1] * nums[n - 1];
        int max2 = nums[n - 1] * nums[n - 2] * nums[n - 3];
        if (max1 > max2)
            return max1;
        else
            return max2;
    }
}

