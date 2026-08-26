import java.util.*;

class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int len = s.length(); // Get total length of input string s
        int i = 0, j = 0; // Initialize left pointer (i) and right pointer (j)
        int count = 0; // Count of '1's in current window [i, j]
        String sub = ""; // Stores best (shortest & lexicographically smallest) substring found
        while (j < len) { // Expand right pointer through s
            if (s.charAt(j) == '1') { // Increment count when a '1' enters window
                count++;
            }
            while (count == k) { // Shrink window while maintaining k '1's
                while (s.charAt(i) == '0') { // Trim leading zeros from left to minimize length
                    i++;
                }
                String temp = s.substring(i, j + 1); // Extract candidate valid substring
                // Update sub if temp is first valid, shorter, or same length but lexicographically smaller
                if (sub.isEmpty() || temp.length() < sub.length() || 
                   (temp.length() == sub.length() && temp.compareTo(sub) < 0)) {
                    sub = temp;
                }
                if (s.charAt(i) == '1') { // Remove leftmost '1' before shifting left pointer
                    count--;
                }
                i++; // Shift left pointer forward
            }
            j++; // Move right pointer forward
        }
        return sub; // Return optimal shortest beautiful substring
    }
}

// // correct but not optimised 
// class Solution {
//     public String shortestBeautifulSubstring(String s, int k) {
//         int len = s.length();
//         List<String> list = new ArrayList<>();    
//         int i = 0;
//         int count = 0;
//         for (int j = 0; j < len; j++) {
//             if (s.charAt(j) == '1') {
//                 count++;
//             }
//             while (count == k) {
//                 while (s.charAt(i) == '0') {
//                     i++;
//                 }
//                 list.add(s.substring(i, j + 1));
//                 if (s.charAt(i) == '1') {
//                     count--;
//                 }
//                 i++;
//             }
//         }
//         if (list.isEmpty()) {
//             return "";
//         }
//         Collections.sort(list, (a, b) -> {
//             if (a.length() != b.length()) {
//                 return Integer.compare(a.length(), b.length());
//             }
//             return a.compareTo(b);
//         });
//         return list.get(0);
//     }
// }

