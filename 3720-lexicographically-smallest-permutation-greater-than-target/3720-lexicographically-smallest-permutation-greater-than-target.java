class Solution { 
    boolean isGreater;
    String result;
    private boolean slove(StringBuilder current , int[] freq, String target,boolean greater, int i){
        if(i == target.length()){
            if(greater){
                result = current.toString();
                return true;
            }
            return false;
        }
        if (greater) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                while (freq[ch - 'a'] > 0) {
                    current.append(ch);
                    freq[ch - 'a']--;
                }
            }
            result = current.toString();
            return true;
        }
        for(char ch='a' ; ch <= 'z' ; ch++){
            if(freq[ch - 'a'] == 0) continue;
            if(greater == false && ch < target.charAt(i)) continue;
            // DO
            current.append(ch);;
            freq[ch - 'a']--;
            // explore
            boolean isGreater = greater || ch>target.charAt(i);
            if(slove(current, freq,target,isGreater,i+1)) return true;
            // undo
            current.deleteCharAt(current.length() - 1);
            freq[ch-'a']++;
        }
        result = current.toString();
        return false;

    }

    public String lexGreaterPermutation(String s, String target) {
        int len = s.length();  
        int[] freq = new int[26];
        result = "";
        StringBuilder current = new StringBuilder();
        isGreater = false;
        for(int i = 0; i< len; i++){
            freq[s.charAt(i)-'a']++;
        }
        if(slove(current,freq,target,isGreater,0)) return result;
        return "";   
    }
}