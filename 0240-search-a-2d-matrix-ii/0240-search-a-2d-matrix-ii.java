class Solution {
    public boolean searchMatrix(int[][] a, int x) {
        int n=a.length;
        int m = a[0].length;
        int i=0,j=m-1;
        while(i<n && j >=0){
            if(a[i][j]==x) return true;
            else if(a[i][j]<x) i++;
            else j--; 
            }
        return false;  
        
    }
}