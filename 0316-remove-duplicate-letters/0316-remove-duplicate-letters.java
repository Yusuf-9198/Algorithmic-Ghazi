
import java.util.Stack;
class Solution {
    public String removeDuplicateLetters(String s) {
        int[] lastIndex = new int[26];
        // Store the last index where each character appears
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        boolean[] seen = new boolean[26];
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int idx = c - 'a';

            // If we already included this character in our current window, skip it
            if (seen[idx]) continue;

            // Maintain the smallest lexicographical order in the stack
            while (!stack.isEmpty() && stack.peek() > c && lastIndex[stack.peek() - 'a'] > i) {
                seen[stack.pop() - 'a'] = false;
            }

            stack.push(c);
            seen[idx] = true;
        }

        // Build the final string from the stack
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        
        return sb.toString();
        
    }
}