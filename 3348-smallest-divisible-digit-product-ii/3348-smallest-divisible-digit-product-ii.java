class Solution {
    // Mapping of single digits (0-9) to their prime factor contributions:
    // {count of 2s, count of 3s, count of 5s, count of 7s}
    private static final int[][] FACTORS = {
        {0, 0, 0, 0}, // 0 (Not allowed in zero-free numbers, handled separately)
        {0, 0, 0, 0}, // 1
        {1, 0, 0, 0}, // 2 -> 2^1
        {0, 1, 0, 0}, // 3 -> 3^1
        {2, 0, 0, 0}, // 4 -> 2^2
        {0, 0, 1, 0}, // 5 -> 5^1
        {1, 1, 0, 0}, // 6 -> 2^1 * 3^1
        {0, 0, 0, 1}, // 7 -> 7^1
        {3, 0, 0, 0}, // 8 -> 2^3
        {0, 2, 0, 0}  // 9 -> 3^2
    };

    /**
     * Calculates the absolute minimum number of single digits (1-9) required 
     * to supply at least 'ra' 2s, 'rb' 3s, 'rc' 5s, and 'rd' 7s.
     */
    private int minDigits(int ra, int rb, int rc, int rd) {
        ra = Math.max(0, ra);
        rb = Math.max(0, rb);
        rc = Math.max(0, rc);
        rd = Math.max(0, rd);

        // Factors of 5 and 7 must be covered by digits '5' and '7' respectively.
        int baseDigits = rc + rd; 
        int min23Digits = Integer.MAX_VALUE;

        // Try using 'k' 6s (each '6' provides one 2 and one 3).
        // Iterate through all possible counts of 6s to find the optimal combination.
        int maxK = Math.min(ra, rb);
        for (int k = 0; k <= maxK; k++) {
            int remA = Math.max(0, ra - k); // Remaining 2s needed
            int remB = Math.max(0, rb - k); // Remaining 3s needed

            // Remaining 2s are best grouped into '8's (3 twos each), '4's, or '2's -> ceil(remA / 3)
            // Remaining 3s are best grouped into '9's (2 threes each) or '3's -> ceil(remB / 2)
            int digitsFor2And3 = k + (remA + 2) / 3 + (remB + 1) / 2;
            min23Digits = Math.min(min23Digits, digitsFor2And3);
        }

        return baseDigits + (min23Digits == Integer.MAX_VALUE ? 0 : min23Digits);
    }

    public String smallestNumber(String num, long t) {
        // STEP 1: Prime factorize t into 2^a * 3^b * 5^c * 7^d
        int a = 0, b = 0, c = 0, d = 0;
        while (t % 2 == 0) { a++; t /= 2; }
        while (t % 3 == 0) { b++; t /= 3; }
        while (t % 5 == 0) { c++; t /= 5; }
        while (t % 7 == 0) { d++; t /= 7; }

        // If t has any prime factors > 7 (e.g., 11, 13), it's impossible to form with single digits.
        if (t > 1) {
            return "-1";
        }

        int n = num.length();
        int[] prefA = new int[n + 1];
        int[] prefB = new int[n + 1];
        int[] prefC = new int[n + 1];
        int[] prefD = new int[n + 1];
        int firstZero = n;

        // STEP 2: Compute prefix sums of prime factors up to the first '0'
        for (int i = 0; i < n; i++) {
            char ch = num.charAt(i);
            if (ch == '0') {
                if (firstZero == n) firstZero = i;
                // Once a '0' is encountered, prefix product becomes 0; keep previous factor counts
                prefA[i + 1] = prefA[i];
                prefB[i + 1] = prefB[i];
                prefC[i + 1] = prefC[i];
                prefD[i + 1] = prefD[i];
            } else {
                int digit = ch - '0';
                prefA[i + 1] = prefA[i] + FACTORS[digit][0];
                prefB[i + 1] = prefB[i] + FACTORS[digit][1];
                prefC[i + 1] = prefC[i] + FACTORS[digit][2];
                prefD[i + 1] = prefD[i] + FACTORS[digit][3];
            }
        }

        // CASE 1: Check if 'num' itself contains no zeros and satisfies the factor requirements
        if (firstZero == n && a <= prefA[n] && b <= prefB[n] && c <= prefC[n] && d <= prefD[n]) {
            return num;
        }

        // CASE 2: Try to construct a number of the SAME length 'n' that is strictly greater than 'num'
        int bestP = -1; // Pivot position where we diverge from 'num'
        int bestD = -1; // Candidate digit at pivot position p (where bestD > num[p])

        // Search backwards from the end (or right before the first zero) to maximize prefix length
        for (int p = Math.min(n - 1, firstZero); p >= 0; p--) {
            int reqA = Math.max(0, a - prefA[p]);
            int reqB = Math.max(0, b - prefB[p]);
            int reqC = Math.max(0, c - prefC[p]);
            int reqD = Math.max(0, d - prefD[p]);

            int startD = (num.charAt(p) - '0') + 1; // Must be strictly greater at pivot

            for (int dCand = startD; dCand <= 9; dCand++) {
                // Remaining prime factors needed after placing digit 'dCand' at index p
                int ra = Math.max(0, reqA - FACTORS[dCand][0]);
                int rb = Math.max(0, reqB - FACTORS[dCand][1]);
                int rc = Math.max(0, reqC - FACTORS[dCand][2]);
                int rd = Math.max(0, reqD - FACTORS[dCand][3]);

                int remainingLen = n - 1 - p;
                
                // Check if remaining positions can hold all required prime factors
                if (minDigits(ra, rb, rc, rd) <= remainingLen) {
                    bestP = p;
                    bestD = dCand;
                    break; // Pick the smallest valid candidate digit at position p
                }
            }
            if (bestP != -1) break; // Found the longest matching prefix
        }

        // If a valid same-length configuration was found, complete the suffix greedily
        if (bestP != -1) {
            StringBuilder sb = new StringBuilder();
            sb.append(num, 0, bestP); // Keep matching prefix
            sb.append(bestD);          // Append chosen higher digit at pivot

            // Update remaining required prime factors
            int ra = Math.max(0, a - prefA[bestP] - FACTORS[bestD][0]);
            int rb = Math.max(0, b - prefB[bestP] - FACTORS[bestD][1]);
            int rc = Math.max(0, c - prefC[bestP] - FACTORS[bestD][2]);
            int rd = Math.max(0, d - prefD[bestP] - FACTORS[bestD][3]);

            int remainingLen = n - 1 - bestP;

            // Greedily fill remaining positions with the smallest valid digit (1-9)
            for (int i = 0; i < remainingLen; i++) {
                for (int x = 1; x <= 9; x++) {
                    int nra = Math.max(0, ra - FACTORS[x][0]);
                    int nrb = Math.max(0, rb - FACTORS[x][1]);
                    int nrc = Math.max(0, rc - FACTORS[x][2]);
                    int nrd = Math.max(0, rd - FACTORS[x][3]);

                    if (minDigits(nra, nrb, nrc, nrd) <= remainingLen - 1 - i) {
                        sb.append(x);
                        ra = nra; rb = nrb; rc = nrc; rd = nrd;
                        break;
                    }
                }
            }
            return sb.toString();
        }

        // CASE 3: No valid number of length 'n' exists; expand length to > n
        int targetLen = Math.max(n + 1, minDigits(a, b, c, d));
        int ra = a, rb = b, rc = c, rd = d;
        StringBuilder sb = new StringBuilder();

        // Construct the smallest valid number of length 'targetLen' greedily from left to right
        for (int i = 0; i < targetLen; i++) {
            for (int x = 1; x <= 9; x++) {
                int nra = Math.max(0, ra - FACTORS[x][0]);
                int nrb = Math.max(0, rb - FACTORS[x][1]);
                int nrc = Math.max(0, rc - FACTORS[x][2]);
                int nrd = Math.max(0, rd - FACTORS[x][3]);

                if (minDigits(nra, nrb, nrc, nrd) <= targetLen - 1 - i) {
                    sb.append(x);
                    ra = nra; rb = nrb; rc = nrc; rd = nrd;
                    break;
                }
            }
        }

        return sb.toString();
    }
}