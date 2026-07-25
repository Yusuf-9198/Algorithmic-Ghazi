// correct but not optimised
class Solution {
    public int maxProduct(int n) {
        if( n < 10) return n;
        List<Integer> digits = new ArrayList<>();
        while(n>=1){
            digits.add(n%10);
            n /= 10;
        }
        int maxPro = 0;
        int i = 0 , j=1;
        while(i< digits.size()){
            if(j< digits.size()){
                maxPro = Math.max(maxPro , digits.get(i) * digits.get(j));
                j++;
            }else {
                i++;
                j=i+1;
            }
        }
        return maxPro;
    }
}

