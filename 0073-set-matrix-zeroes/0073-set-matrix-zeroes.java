class Solution {
    public void setZeroes(int[][] arr) {
        int n=arr.length;
        int m=arr[0].length;
        boolean[] a=new boolean[n];
        boolean[] b=new boolean[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(arr[i][j]==0){
                    a[i]=true;
                    b[j]=true;
                }   
            } 
        }
        for (int i = 0; i < n; i++) {
            if(a[i]){
                for (int j = 0; j <m; j++) {
                 arr[i][j] =0;
                
            }}
        }
        for (int j = 0; j <m; j++) {
            if(b[j]){
                for (int i = 0; i<n; i++) {
                 arr[i][j] =0;
                
            }}
        }
         
    }
}
  
