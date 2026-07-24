// // // TLE Error 
// import java.util.HashSet;
// import java.util.Set;
// class Solution {
//     public int uniqueXorTriplets(int[] nums) {
//         int n = nums.length;
//         Set<Integer> xor = new HashSet<>();
//         for (int i = 0; i < n; i++) {
//             for (int j = i; j < n; j++) {
//                 xor.add(nums[i] ^ nums[j]);
//             }
//         }
//         Set<Integer> set = new HashSet<>();
//         for (int k = 0; k < n; k++) {
//             for (int num : xor) {
//                 set.add(nums[k] ^ num);
//             }
//         }
//         return set.size();
//     }
// }

// // correct
// class Solution {
//     public int uniqueXorTriplets(int[] nums) {
//         int n = nums.length;
//         boolean[] hasSingle = new boolean[2048];
//         for (int num : nums) {
//             hasSingle[num] = true;
//         }
//         boolean[] hasPair = new boolean[2048];
//         for (int i = 0; i < n; i++) {
//             for (int j = i; j < n; j++) {
//                 hasPair[nums[i] ^ nums[j]] = true;
//             }
//         }
//         boolean[] hasTriplet = new boolean[2048];
//         int count = 0;
//         for (int pairXor = 0; pairXor < 2048; pairXor++) {
//             if (!hasPair[pairXor]) continue;
//             for (int single = 0; single < 2048; single++) {
//                 if (hasSingle[single]) {
//                     int tripletVal = pairXor ^ single;
//                     if (!hasTriplet[tripletVal]) {
//                         hasTriplet[tripletVal] = true;
//                         count++;
//                     }
//                 }
//             }
//         }
//         return count;
//     }
// }

// Also correct
class Solution {
    public int uniqueXorTriplets(int[] nums) {
        // Find maximum value to compute tight bitwise boundary
        int mx = 0;
        for (int x : nums) {
            if (x > mx) mx = x;
        }

        // Bound XOR limit: smallest power of 2 > mx
        int bound = Integer.highestOneBit(mx) << 1;

        // Stage 1: Mark pair XOR combinations
        boolean[] hasPair = new boolean[bound];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                hasPair[nums[i] ^ nums[j]] = true;
            }
        }

        // Stage 2: Combine pairs with a 3rd element
        boolean[] hasTriplet = new boolean[bound];
        int count = 0;

        for (int pairVal = 0; pairVal < bound; pairVal++) {
            if (!hasPair[pairVal]) continue;

            for (int c : nums) {
                int tripletVal = pairVal ^ c;
                if (!hasTriplet[tripletVal]) {
                    hasTriplet[tripletVal] = true;
                    count++;
                }
            }
        }

        return count;
    }
}
