import java.util.Arrays;

class Solution {
    public int minimumPushes(String word) {
        int n = word.length();
        int[] fre = new int[26];
        int push = 0;
        for (int i = 0; i < n; i++) {
            fre[word.charAt(i) - 'a']++;
        }
        Arrays.sort(fre);
        int mul = 1;
        int k = 25;
        while (k >= 0 && fre[k] > 0) {
            for (int j = 0; j < 8 && k >= 0 && fre[k] > 0; j++) {
                push += fre[k] * mul;
                k--;
            }
            mul++;
        }
        return push;
    }
}