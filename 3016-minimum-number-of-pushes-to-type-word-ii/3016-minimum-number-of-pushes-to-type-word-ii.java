class Solution {
    public int minimumPushes(String word) {
        int n = word.length();
        int[] freq = new int[26];
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }
        Arrays.sort(freq);
        int push = 0;
        for (int i = 25; i >= 0 && freq[i] > 0; i--) {
            push += freq[i] * ((25 - i) / 8 + 1);
        }
        return push;
        
    }
}