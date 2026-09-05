//  * LOGIC:
//  * For each index 'i' in the array from 0 to n-1:
//  * 1. Find the maximum value in the prefix subarray from index 0 to 'i' (inclusive).
//  * 2. Find the minimum value in the suffix subarray from index 'i' to 'n-1' (inclusive).
//  * 3. Check if the difference between this maximum prefix value and minimum suffix value 
//  *    is less than or equal to 'k' ((maxInt - minInt) <= k).
//  * 4. If the condition holds, record the current index 'i' as a valid stable index and 
//  *    keep track of the minimum valid index.
//  * 5. Return the first (smallest) stable index found, or -1 if no index satisfies the condition.
//  * TC = O(n)
class Solution 
{
    public int firstStableIndex(int[] nums, int k) 
    {
        int n = nums.length;
        int[] mini = new int[n];

        int mint = Integer.MAX_VALUE;
        for(int i = n - 1; i >= 0; i--)
        {
            if (nums[i] < mint) mint = nums[i];
            mini[i] = mint;
        }

        int maxt = 0;
        for(int i = 0; i < n; i++)
        {
            if(nums[i] > maxt) maxt = nums[i];
            if(maxt - mini[i] <= k) return i;
        }

        return -1;
    }
}

// // correct and perfect 
// class Solution {
//     public int firstStableIndex(int[] nums, int k) {
//         int n = nums.length;
//         int[] prefMax = new int[n];
//         int[] suffMin = new int[n];
//         prefMax[0] = nums[0];
//         for (int i = 1; i < n; i++) prefMax[i] = Math.max(prefMax[i - 1], nums[i]);
//         suffMin[n - 1] = nums[n - 1];
//         for (int i = n - 2; i >= 0; i--) suffMin[i] = Math.min(suffMin[i + 1], nums[i]);
//         for (int i = 0; i < n; i++) {
//             if (prefMax[i] - suffMin[i] <= k) return i;
//         }
//         return -1;
//     }
// }


