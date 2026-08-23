class Solution {
    public int candy(int[] rating){
        int n = rating.length;
        int[] candy1 = new int[n];
        for (int i = 0; i <n; i++) {
            candy1[i]=1;
        }
        for (int i = 1; i < n; i++) {
            if(rating[i]>rating[i-1] ){
                candy1[i] = candy1[i-1] +1;
            }
        }
        for (int i = n-2; i >= 0; i--) {
            if(rating[i] > rating[i+1] ){
                candy1[i] = Math.max(candy1[i], candy1[i+1]+1);
            } 
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += candy1[i];      
        } 
        return sum;
    }
}