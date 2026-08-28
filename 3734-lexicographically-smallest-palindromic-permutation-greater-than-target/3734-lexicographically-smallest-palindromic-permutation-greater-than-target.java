class Solution { 
    String result;

    private boolean solve(StringBuilder current, int[] freq, String target, boolean greater, int i, char midChar) {
        // Base case: formed half of the palindrome
        if (i == target.length() / 2) {
            String half = current.toString();
            String revHalf = new StringBuilder(half).reverse().toString();
            String full = (midChar == '\0') ? half + revHalf : half + midChar + revHalf;
            
            if (greater || full.compareTo(target) > 0) {
                result = full;
                return true;
            }
            return false;
        }

        if (greater) {
            // Greedy choice: append smallest available characters
            for (char ch = 'a'; ch <= 'z'; ch++) {
                while (freq[ch - 'a'] > 0) {
                    current.append(ch);
                    freq[ch - 'a']--;
                }
            }
            return solve(current, freq, target, true, target.length() / 2, midChar);
        }

        char targetChar = target.charAt(i);

        for (char ch = 'a'; ch <= 'z'; ch++) {
            if (freq[ch - 'a'] == 0) continue;
            if (ch < targetChar) continue;

            // DO
            current.append(ch);
            freq[ch - 'a']--;

            // EXPLORE
            boolean isGreater = greater || (ch > targetChar);
            if (solve(current, freq, target, isGreater, i + 1, midChar)) {
                return true;
            }

            // UNDO
            current.deleteCharAt(current.length() - 1);
            freq[ch - 'a']++;
        }

        return false;
    }

    public String lexPalindromicPermutation(String s, String target) {
        int[] freq = new int[26];
        result = "";
        
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }

        int oddCount = 0;
        char midChar = '\0';

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 != 0) {
                oddCount++;
                midChar = (char) ('a' + i);
            }
            freq[i] /= 2; // Keep half frequency for building the first half
        }

        // Cannot form a palindrome if more than 1 character has an odd count
        if (oddCount > 1) return "";

        StringBuilder current = new StringBuilder();
        if (solve(current, freq, target, false, 0, midChar)) {
            return result;
        }

        return "";   
    }
}