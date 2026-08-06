class Solution {
    public int pro(int n){
        int product = 1;
        while(n>0){
            product *= n%10;
            n = n/10;
        }
        return product;
    }
    public int smallestNumber(int n, int t) {
        int product = pro(n);
        if(product % t == 0) return n;
        else{
            return smallestNumber(++n,t);
        }
        
    }
}