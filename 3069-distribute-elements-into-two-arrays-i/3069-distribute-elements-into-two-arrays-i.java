// // Correct Self 7 % beat : Stream API is lttle bit slower than for loop but TC same O(n)
// class Solution {
//     public int[] resultArray(int[] nums) {
//         int n = nums.length;
//         List<Integer> l1 = new ArrayList<>();
//         List<Integer> l2 = new ArrayList<>();
//         l1.add(nums[0]);
//         l2.add(nums[1]);
//         for (int i = 2; i < n; i++) {
//             if (l1.get(l1.size() - 1) > l2.get(l2.size() - 1))
//                 l1.add(nums[i]);
//             else
//                 l2.add(nums[i]);
//         }
//         l1.addAll(l2);
//         // Requires Google Guava
//         int[] result = l1.stream().mapToInt(Integer::intValue).toArray();
//         return result;
//     }
// }

// // correct little bit optimised
// class Solution {
//     public int[] resultArray(int[] nums) {
//         int n = nums.length;
//         List<Integer> l1 = new ArrayList<>();
//         List<Integer> l2 = new ArrayList<>();
//         l1.add(nums[0]);
//         l2.add(nums[1]);
//         for (int i = 2; i < n; i++) {
//             if (l1.get(l1.size() - 1) > l2.get(l2.size() - 1))
//                 l1.add(nums[i]);
//             else
//                 l2.add(nums[i]);
//         }
//         l1.addAll(l2);
//         int[] result = new int[n];
//         for (int i = 0; i < l1.size(); i++) {
//             result[i] = l1.get(i); // Auto-unboxing occurs here
//         }
//         return result;
//     }
// }

// Optimised 
class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        int[] arr1 = new int[n];
        int[] arr2 = new int[n];
        arr1[0] = nums[0];
        arr2[0] = nums[1];
        int i = 0; 
        int j = 0; 
        for (int k = 2; k < n; k++) {
            if (arr1[i] > arr2[j]) {
                arr1[++i] = nums[k];
            } else {
                arr2[++j] = nums[k];
            }
        }
        for (int k = 0; k <= j; k++) {
            arr1[++i] = arr2[k];
        }
        return arr1;
    }
}   

