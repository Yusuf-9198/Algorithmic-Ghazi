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

// correct
class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        boolean[] hasSingle = new boolean[2048];
        for (int num : nums) {
            hasSingle[num] = true;
        }
        boolean[] hasPair = new boolean[2048];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                hasPair[nums[i] ^ nums[j]] = true;
            }
        }
        boolean[] hasTriplet = new boolean[2048];
        int count = 0;
        for (int pairXor = 0; pairXor < 2048; pairXor++) {
            if (!hasPair[pairXor]) continue;
            for (int single = 0; single < 2048; single++) {
                if (hasSingle[single]) {
                    int tripletVal = pairXor ^ single;
                    if (!hasTriplet[tripletVal]) {
                        hasTriplet[tripletVal] = true;
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
