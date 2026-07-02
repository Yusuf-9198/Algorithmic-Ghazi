class Solution {
    public int countPalindromicSubsequence(String s) {
        HashMap<Character,Integer> fmap = new HashMap<>();
        HashMap<Character,Integer> lmap = new HashMap<>();
        for (int i = 0; i <s.length(); i++) {
            if(!fmap.containsKey(s.charAt(i))) fmap.put(s.charAt(i),i);
            lmap.put(s.charAt(i),i);
        }
        int count=0;
        for(char key: fmap.keySet()){
            if(!(fmap.get(key) == lmap.get(key))){
                HashSet<Character> set = new HashSet<>();
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