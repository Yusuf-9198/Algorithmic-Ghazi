import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    // Stores precomputed LCM and inclusion-exclusion sign (+1 or -1)
    private static class Subset {
        long lcm;
        int sign;

        Subset(long lcm, int sign) {
            this.lcm = lcm;
            this.sign = sign;
        }
    }

    public long findKthSmallest(int[] coins, int k) {
        // 1. Prune redundant coins
        Arrays.sort(coins);
        List<Integer> filtered = new ArrayList<>();
        for (int coin : coins) {
            boolean redundant = false;
            for (int existing : filtered) {
                if (coin % existing == 0) {
                    redundant = true;
                    break;
                }
            }
            if (!redundant) {
                filtered.add(coin);
            }
        }

        int n = filtered.size();
        long minCoin = filtered.get(0);
        long maxBound = minCoin * (long) k;

        // 2. Precompute subset LCMs and signs
        List<Subset> subsets = new ArrayList<>();
        for (int mask = 1; mask < (1 << n); mask++) {
            long lcmVal = 1;
            int bits = 0;
            boolean overflow = false;

            for (int i = 0; i < n; i++) {
                if (((mask >> i) & 1) == 1) {
                    bits++;
                    lcmVal = lcm(lcmVal, filtered.get(i));
                    if (lcmVal > maxBound) { // Early exit if LCM exceeds max possible search range
                        overflow = true;
                        break;
                    }
                }
            }

            if (!overflow) {
                int sign = (bits % 2 == 1) ? 1 : -1;
                subsets.add(new Subset(lcmVal, sign));
            }
        }

        // 3. Binary Search with simple array loop
        long low = 1, high = maxBound, ans = maxBound;
        while (low <= high) {
            long mid = low + (high - low) / 2;

            long count = 0;
            for (Subset s : subsets) {
                count += s.sign * (mid / s.lcm);
            }

            if (count >= k) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

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