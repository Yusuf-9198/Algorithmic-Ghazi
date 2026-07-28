class Solution {
    public String sorting(String s) {
        int n = s.length();
        char[] charc = s.toCharArray();
        Arrays.sort(charc);
        s = "";
        for (int i = 0; i < n; i++) {
            s +=charc[i];
        }
        return s;
    }

  
    public String smallestPalindrome(String s) {
        int n = s.length();
        if (n == 1)
            return s;
        int i = 0, j = n - 1;
        String t = "";
        String tr = "";
        if (n % 2 == 0) {
            t = sorting(s.substring(0, (n / 2) ));
            tr = new StringBuilder(t).reverse().toString();
            return t + tr;
        } else {
            t = sorting(s.substring(0, n / 2));
            tr = new StringBuilder(t).reverse().toString();
            return t + s.charAt((n / 2)) + tr;

        }

    }
}