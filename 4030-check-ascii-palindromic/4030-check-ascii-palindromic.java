class Solution {
    public boolean isPalindromic(String s) {
        int len = s.length();
        StringBuilder ascii = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int charAscii = s.charAt(i); 
            for (int j = 0; j < 8; j++) {
                char charOf = (char) ((charAscii % 2) + '0');
                ascii.append(charOf); 
                charAscii /= 2;
            }
        }
        int asciiLen = ascii.length();
        for (int i = 0; i < asciiLen / 2; i++) { 
            if (ascii.charAt(i) != ascii.charAt(asciiLen - 1 - i)) {
                return false;
            }
        }

        return true;
    }
}