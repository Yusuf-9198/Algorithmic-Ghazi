// class Solution {
//     public int maxActiveSectionsAfterTrade(String s) {
//         int n = s.length();
//         StringBuilder s1 = new StringBuilder("1" + s + "1");
//         int m = s1.length();

//         int p = -1, q = -1;
//         boolean check = true;

//         for (int i = 1; i < m - 1; i++) {
//             if (s1.charAt(i) == '1' && s1.charAt(i - 1) == '0' && check) {
//                 p = i;
//                 check = false;
//             }
//             if (!check && s1.charAt(i) == '1' && s1.charAt(i + 1) == '0') {
//                 q = i;
//                 break;
//             }
//         }

//         if (p != -1 && q != -1) {
//             for (int i = p; i <= q; i++) {
//                 s1.setCharAt(i, '0');
//             }
//         }

//         p = -1;
//         q = -1;
//         check = true;

//         for (int i = 1; i < m - 1; i++) {
//             if (s1.charAt(i) == '0' && s1.charAt(i - 1) == '1' && check) {
//                 p = i;
//                 check = false;
//             }
//             if (!check && s1.charAt(i) == '0' && s1.charAt(i + 1) == '1') {
//                 q = i;
//                 break;
//             }
//         }

//         if (p != -1 && q != -1) {
//             for (int i = p; i <= q; i++) {
//                 s1.setCharAt(i, '1');
//             }
//         }

//         int count = 0;
//         for (int i = 1; i < m - 1; i++) {
//             if (s1.charAt(i) == '1') {
//                 count++;
//             }
//         }

//         return count;
//     }
// }
class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int ones = 0, n = s.length();
        java.util.List<Integer> zeros = new java.util.ArrayList<>();
        
        for (int i = 0; i < n; ) {
            int j = i;
            while (j < n && s.charAt(j) == s.charAt(i)) j++;
            int len = j - i;
            if (s.charAt(i) == '1') {
                ones += len;
            } else {
                zeros.add(len);
            }
            i = j;
        }

        int maxZeroSum = 0;
        for (int i = 0; i < zeros.size() - 1; i++) {
            maxZeroSum = Math.max(maxZeroSum, zeros.get(i) + zeros.get(i + 1));
        }

        return ones + maxZeroSum;
    }
}