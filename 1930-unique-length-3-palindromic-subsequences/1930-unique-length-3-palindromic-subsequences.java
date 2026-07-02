class Solution {
    public int countPalindromicSubsequence(String s) {
        int len = s.length();
        HashMap<Character,Integer> fmap = new HashMap<>();
        HashMap<Character,Integer> lmap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if(!fmap.containsKey(ch)) fmap.put(ch,i);
            lmap.put(ch,i);
        }
        int count=0;
        HashSet<Character> set = new HashSet<>();
        for(char key: fmap.keySet()){
            if(!(fmap.get(key) == lmap.get(key))){
                for (int i = fmap.get(key)+1; i <lmap.get(key) ; i++) {
                    set.add(s.charAt(i));
                }
                count += set.size();
                set.clear();
            }
        }
        return count;


    }
}