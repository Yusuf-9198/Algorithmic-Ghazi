// import java.util.Arrays;

// class Solution {
//     public int minimumPushes(String word) {
//         int n = word.length();
//         int[] fre = new int[26];
//         int push = 0;
//         for (int i = 0; i < n; i++) {
//             fre[word.charAt(i) - 'a']++;
//         }
//         Arrays.sort(fre);
//         int mul = 1;
//         int k = 25;
//         while (k >= 0 && fre[k] > 0) {
//             for (int j = 0; j < 8 && k >= 0 && fre[k] > 0; j++) {
//                 push += fre[k] * mul;
//                 k--;
//             }
//             mul++;
//         }
//         return push;
//     }
// }

import java.util.Arrays;

class Solution {
    public int minimumPushes(String word) {
        int[] fre = new int[26];
        for (char c : word.toCharArray()) {
            fre[c - 'a']++;
        }
        Arrays.sort(fre);
        int push = 0;
        for (int i = 25; i >= 0 && fre[i] > 0; i--) {
            push += fre[i] * ((25 - i) / 8 + 1);
        }
        return push;
    }
}