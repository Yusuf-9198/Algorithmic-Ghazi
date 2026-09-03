/**
 * Approach / Logic:
 * 1. Find the minimum odd and even numbers in the array.
 * 2. Parity rule: Subtracting an odd number flips parity (Even -> Odd, Odd -> Even).
 *    Subtracting an even number keeps parity unchanged.
 * 3. To convert all numbers to ODD:
 *    - Existing odd numbers remain unchanged.
 *    - Every even number must subtract a smaller odd number (nums1[i] - minOdd >= 1).
 * 4. To convert all numbers to EVEN:
 *    - Existing even numbers remain unchanged.
 *    - Every odd number must subtract a smaller odd number (nums1[i] - minOdd >= 1).
 * 5. If either conversion succeeds (isOddDone || isEvenDone), return true.
 *
 * Time Complexity: O(n)
 * - Three linear passes over the array of size n:
 *   Pass 1: Find minOdd and minEven -> O(n)
 *   Pass 2: Verify all-odd target condition -> O(n)
 *   Pass 3: Verify all-even target condition -> O(n)
 * Total Time = O(3n) = O(n).
 *
 * Space Complexity: O(1)
 * - Uses only primitive variables (minOdd, minEven, flags) requiring constant space O(1).
 */
class Solution {
    public boolean uniformArray(int[] nums1) {
        int n = nums1.length;
        int minOdd = Integer.MAX_VALUE;
        int minEven = Integer.MAX_VALUE;
        boolean isOddDone = true;
        boolean isEvenDone = true;

        for (int i = 0; i < n; i++) {
            if (nums1[i] % 2 != 0) {
                minOdd = Math.min(minOdd, nums1[i]);
            } else {
                minEven = Math.min(minEven, nums1[i]);
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums1[i] % 2 == 0) {
                if (minOdd == Integer.MAX_VALUE || nums1[i] - minOdd < 1) {
                    isOddDone = false;
                    break;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums1[i] % 2 != 0) {
                if (minOdd == Integer.MAX_VALUE || nums1[i] - minOdd < 1) {
                    isEvenDone = false;
                    break;
                }
            }
        }

        return isOddDone || isEvenDone;
    }
}

// // optimised
// class Solution {
//     public boolean uniformArray(int[] nums1) {
//         int minVal = Integer.MAX_VALUE;
//         boolean hasOdd = false;
//         boolean hasEven = false;
//         for (int num : nums1) {
//             if (num % 2 != 0) hasOdd = true;
//             else hasEven = true;
//             minVal = Math.min(minVal, num);
//         }
//         // Already uniform parity OR the smallest overall element is odd
//         return (!hasOdd || !hasEven) || (minVal % 2 != 0);
//     }
// }