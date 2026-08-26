// import java.util.*;

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

import java.util.*;

class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int len = s.length();
        int i = 0, j = 0;
        int count = 0;
        String sub = "";

        while (j < len) {
            if (s.charAt(j) == '1') {
                count++;
            }

            while (count == k) {
                // Trim leading zeros
                while (s.charAt(i) == '0') {
                    i++;
                }

                // Current valid window
                String temp = s.substring(i, j + 1);

                // Update 'sub' if 'temp' is shorter OR same length but lexicographically smaller
                if (sub.isEmpty() || temp.length() < sub.length() || 
                   (temp.length() == sub.length() && temp.compareTo(sub) < 0)) {
                    sub = temp;
                }

                // Move left pointer forward
                if (s.charAt(i) == '1') {
                    count--;
                }
                i++;
            }
            j++;
        }

        return sub;
    }
}