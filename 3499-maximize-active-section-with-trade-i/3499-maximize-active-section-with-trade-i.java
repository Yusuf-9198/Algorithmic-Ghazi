//correct
import java.util.ArrayList;
class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int ones = 0, n = s.length();
        List<Integer> zeros = new ArrayList<>();
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