// // correct self

// class Solution {
//     public String sorting(String s) {
//         int n = s.length();
//         char[] charc = s.toCharArray();
//         Arrays.sort(charc);
//         s = "";
//         for (int i = 0; i < n; i++) {
//             s +=charc[i];
//         }
//         return s;
//     }
//     public String smallestPalindrome(String s) {
//         int n = s.length();
//         if (n == 1)
//             return s;
//         int i = 0, j = n - 1;
//         String t = "";
//         String tr = "";
//         if (n % 2 == 0) {
//             t = sorting(s.substring(0, (n / 2) ));
//             tr = new StringBuilder(t).reverse().toString();
//             return t + tr;
//         } else {
//             t = sorting(s.substring(0, n / 2));
//             tr = new StringBuilder(t).reverse().toString();
//             return t + s.charAt((n / 2)) + tr;
//         }
//     }
// }

// // my code improved

// import java.util.Arrays;
// class Solution {
//     public String sorting(String s) {
//         char[] charc = s.toCharArray();
//         Arrays.sort(charc);
//         return new String(charc); // Fast O(K) conversion
//     }
//     public String smallestPalindrome(String s) {
//         int n = s.length();
//         if (n == 1) return s;
//         // s.substring(0, n / 2) gets the exact left half for both even and odd lengths
//         String t = sorting(s.substring(0, n / 2));
//         String tr = new StringBuilder(t).reverse().toString();
//         if (n % 2 == 0) {
//             return t + tr;
//         } else {
//             return t + s.charAt(n / 2) + tr;
//         }
//     }
// }

// optimised
import java.util.Arrays;

class Solution {
    public String smallestPalindrome(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Count odd frequency characters
        int oddCount = 0;
        char midChar = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 != 0) {
                oddCount++;
                midChar = (char) ('a' + i);
            }
        }

        // If more than 1 character has odd frequency, a palindrome isn't possible
        if (oddCount > 1) {
            return ""; // or return s depending on problem requirements
        }

        // Build the left half in alphabetical order
        StringBuilder left = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < freq[i] / 2; j++) {
                left.append((char) ('a' + i));
            }
        }

        // Middle character (if any)
        String middle = (oddCount == 1) ? String.valueOf(midChar) : "";

        // Right half is simply the reverse of the left half
        String right = new StringBuilder(left).reverse().toString();

        return left.toString() + middle + right;
    }
}