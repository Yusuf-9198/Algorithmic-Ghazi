class Solution {
    public List<List<Integer>> generate(int n) {
        List<List<Integer>> arr = new ArrayList<>();
        for(int i=0 ; i<n; i++){
            List<Integer> l =new ArrayList<>();
            for(int j=0; j<=i;j++){
                if(j==0 || j==i) l.add(1);
                else l.add(arr.get(i-1).get(j)+arr.get(i-1).get(j-1));
            }
            arr.add(l);
        }
        return arr;
    }
}