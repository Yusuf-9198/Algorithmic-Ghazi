class Solution {
    // Upper limit threshold to prevent long overflow
    private static final long LIMIT = 1_000_001L;

    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int[] half = new int[26];
        int halfLen = 0;
        char midChar = 0;

        // Populate half frequencies and detect odd center character if any
        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            halfLen += half[i];
            if (freq[i] % 2 != 0) {
                midChar = (char) ('a' + i);
            }
        }

        // If total possible palindromes is less than k, return empty string
        if (countWays(half, halfLen) < k) {
            return "";
        }

        StringBuilder leftHalf = new StringBuilder();

        // Construct the first half slot by slot
        for (int pos = 0; pos < halfLen; pos++) {
            for (int i = 0; i < 26; i++) {
                if (half[i] == 0) continue; // Skip unavailable characters

                // Temporarily select character 'a' + i
                half[i]--;

                // How many combinations remain?
                long ways = countWays(half, halfLen - pos - 1);

                if (ways >= k) {
                    // Valid prefix! Lock in this character
                    leftHalf.append((char) ('a' + i));
                    break;
                } else {
                    // Skip these 'ways' palindromes and backtrack
                    k -= ways;
                    half[i]++;
                }
            }
        }

        // Assemble full palindrome using symmetry
        StringBuilder result = new StringBuilder();
        result.append(leftHalf);

        if (midChar != 0) {
            result.append(midChar);
        }

        result.append(new StringBuilder(leftHalf).reverse());

        return result.toString();
    }

    // Helper to calculate distinct permutations safely without long overflow
    private long countWays(int[] cnt, int total) {
        long res = 1;
        int remaining = total;

        for (int i = 0; i < 26; i++) {
            int c = cnt[i];
            if (c == 0) continue;

            // Calculate combinations nCr: remaining choose c
            for (int j = 1; j <= c; j++) {
                res = res * (remaining - c + j) / j;
                if (res >= LIMIT) {
                    return LIMIT; // Cap at limit to avoid overflow
                }
            }
            remaining -= c;
        }

        return res;
    }
}