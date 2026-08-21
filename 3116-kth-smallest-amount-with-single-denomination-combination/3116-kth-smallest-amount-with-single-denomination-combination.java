import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    // Custom class: Har subset ka LCM aur Inclusion-Exclusion Sign (+1 ya -1) store karega
    private static class Subset {
        long lcm;
        int sign; // +1 agar odd size set hai (Inclusion), -1 agar even size set hai (Exclusion)

        Subset(long lcm, int sign) {
            this.lcm = lcm;
            this.sign = sign;
        }
    }

    public long findKthSmallest(int[] coins, int k) {
        // STEP 1: Redundant Coins ko Filter (Prune) out karo
        // Pehle array ko sort karo taaki chhote coins pehle aayein
        Arrays.sort(coins);
        
        List<Integer> filteredCoins = new ArrayList<>();
        for (int coin : coins) {
            boolean isRedundant = false;
            // Check karo kya yeh coin kisi chhote coin ka multiple hai
            for (int existingCoin : filteredCoins) {
                if (coin % existingCoin == 0) {
                    isRedundant = true; // Jaise 3 ke hote hue 6 ya 9 redundant hai
                    break;
                }
            }
            // Jo redundant nahi hai, bas usko hi filter list mein rakho
            if (!isRedundant) {
                filteredCoins.add(coin);
            }
        }

        int n = filteredCoins.size();
        long minCoin = filteredCoins.get(0);
        // Maximum Search Boundary: kth smallest amount (minCoin * k) se bada nahi ho sakta
        long maxBound = minCoin * (long) k;

        // STEP 2: Precalculate Subsets (Subsets ka LCM aur Sign pehle hi store kar lo)
        // Isse Binary Search ke loop ke andhar baar-baar LCM aur GCD calculate nahi karna padega
        List<Subset> precomputedSubsets = new ArrayList<>();
        
        // Bitmask se 1 se (2^n - 1) tak ke saare non-empty subsets generate karenge
        for (int mask = 1; mask < (1 << n); mask++) {
            long currentLcm = 1;
            int bitCount = 0;
            boolean isOverflow = false;

            for (int i = 0; i < n; i++) {
                // Agar mask ka i-th bit set (1) hai, toh current coin subset ka part hai
                if (((mask >> i) & 1) == 1) {
                    bitCount++;
                    currentLcm = lcm(currentLcm, filteredCoins.get(i));
                    
                    // Agar LCM search limit (maxBound) se bada ho gaya, toh aage calculate karne ka fayda nahi
                    if (currentLcm > maxBound) {
                        isOverflow = true;
                        break;
                    }
                }
            }

            // Range ke andhar wala subset save kar lo
            if (!isOverflow) {
                // Odd length subsets ke liye +1 (add karo), Even length ke liye -1 (subtract karo)
                int sign = (bitCount % 2 == 1) ? 1 : -1;
                precomputedSubsets.add(new Subset(currentLcm, sign));
            }
        }

        // STEP 3: Binary Search Answer calculate karne ke liye
        long low = 1;
        long high = maxBound;
        long answer = maxBound;

        while (low <= high) {
            long mid = low + (high - low) / 2;

            // Inclusion-Exclusion formula ko precomputed array par apply karo
            long totalCount = 0;
            for (Subset subset : precomputedSubsets) {
                totalCount += subset.sign * (mid / subset.lcm);
            }

            // Binary search decision logic
            if (totalCount >= k) {
                answer = mid;       // Mid threshold meet kar raha hai, chhota answer dhoondhne high ko kam karo
                high = mid - 1;
            } else {
                low = mid + 1;      // Amounts target k se kam hain, range badao
            }
        }

        return answer; // kth smallest valid amount return kar do
    }

    // GCD Calculate karne ka Euclidean Helper Method
    private long gcd(long a, long b) {
        while (b != 0) {
            long remainder = a % b;
            a = b;
            b = remainder;
        }
        return a;
    }

    // LCM Calculate karne ka Helper Method
    // (a / gcd) pehle karne se long variable overflow hone se bach jata hai
    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}

// class Solution {
//     public long findKthSmallest(int[] coins, int k) {
//         long low = 1;
//         long high = (long) coins[0] * k;
//         for (int coin : coins) {
//             high = Math.min(high, (long) coin * k);
//         }
//         long ans = high;
//         while (low <= high) {
//             long mid = low + (high - low) / 2;
//             if (countValid(mid, coins) >= k) {
//                 ans = mid;
//                 high = mid - 1; // Search for smaller valid amount
//             } else {
//                 low = mid + 1;
//             }
//         }
//         return ans;
//     }
//     private long countValid(long x, int[] coins) {
//         int n = coins.length;
//         long count = 0;

//         for (int mask = 1; mask < (1 << n); mask++) {
//             long lcmVal = 1;
//             int bits = 0;
//             boolean valid = true;
//             for (int i = 0; i < n; i++) {
//                 // Corrected bitwise shift check
//                 if ((mask & (1 << i)) != 0) {
//                     bits++;
//                     lcmVal = lcm(lcmVal, coins[i]);
//                     if (lcmVal > x) {
//                         valid = false;
//                         break;
//                     }
//                 }
//             }
//             if (!valid) continue;
//             long mul = x / lcmVal;
//             if (bits % 2 == 1) {
//                 count += mul;
//             } else {
//                 count -= mul;
//             }
//         }
//         return count;
//     }

//     private long gcd(long a, long b) {
//         while (b != 0) {
//             long temp = a % b;
//             a = b;
//             b = temp;
//         }
//         return a;
//     }
//     private long lcm(long a, long b) {
//         return (a / gcd(a, b)) * b;
//     }
// }


// // correct : but TLE {brute force}
// class Solution {
//     public long findKthSmallest(int[] coins, int k) {
//         int n = coins.length;
//         Set<Long> set = new TreeSet<>();
//         for (int coin: coins) {
//             for (int j = 1 ; j<=k : j++) {
//                 set.add((long) (j * coin));
//             }
//         }
//        List<Long> lst = new ArrayList<>(set);
//         return lst.get(k-1);
//     }
// }