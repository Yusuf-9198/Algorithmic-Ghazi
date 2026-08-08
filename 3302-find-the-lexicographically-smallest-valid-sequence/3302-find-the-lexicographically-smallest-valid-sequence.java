// Solution based on codestorywithMIK (LeetCode 3302)
class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length(),  m = word2.length();
        // Step 1: Precomputation Array (Right-to-Left Traversal)
        // right_matched_len[i] batayega ki word1 me index i se lekar end tak
        // word2 ke kitne characters right side se match ho rahe hain.
        int[] right_matched_len = new int[n];
        int right_count = 0;
        int j = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                right_count++;
                j--; // word2 ka ek char match ho gaya, piche badho
            }
            right_matched_len[i] = right_count;
        }
        // Step 2: Greedy Matching (Left-to-Right Traversal)
        int[] result = new int[m];
        boolean changePower = true; // 1 character modification ka power abhi bacha hai
        int i = 0;
        j = 0;
        int k = 0;
        while (i < n && j < m) {
            // Case 1: Exact match mil gaya
            if (word1.charAt(i) == word2.charAt(j)) {
                result[k++] = i; // Leftmost index greedily pick kar lo
                j++;
            } 
            // Case 2: Match nahi hua, toh dekhte hain kya 1 modification power use kar sakte hain
            else {
                // Remainder required characters from word2 = m - j - 1
                int rem_required = m - j - 1;
                // Index i + 1 ke baad word1 me kitne matching chars available hain
                int available_chars = (i + 1 < n) ? right_matched_len[i + 1] : 0;
                // Agar changePower bachi hai AUR aage ke bache hue chars word1 me mil jayenge:
                if (changePower && available_chars >= rem_required) {
                    result[k++] = i;   // Modification power use karke index i pick kar liya
                    j++;
                    changePower = false; // Power ab use ho chuki hai, dobara use nahi kar sakte
                }
            }
            i++;
        }
        // Agar word2 ke saare characters match ho gaye (j == m), tabhi valid result return karo
        return (j == m) ? result : new int[0];
    }
}