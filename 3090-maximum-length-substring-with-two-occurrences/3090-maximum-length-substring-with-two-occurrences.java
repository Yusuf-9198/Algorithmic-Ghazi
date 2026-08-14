class Solution {
    public int maximumLengthSubstring(String s) {
        int len = s.length();
        Map<Character, Integer> map = new HashMap<>();
        int maxLen = 0;
        int i = 0, j = 0;
        while (j < len) {
            map.put(s.charAt(j), map.getOrDefault(s.charAt(j), 0) + 1);
            while (map.get(s.charAt(j)) > 2) {
                map.put(s.charAt(i), map.get(s.charAt(i)) - 1);
                i++;
            }

            maxLen = Math.max(maxLen, j - i + 1);
            j++;

        }
        return maxLen;
    }
}