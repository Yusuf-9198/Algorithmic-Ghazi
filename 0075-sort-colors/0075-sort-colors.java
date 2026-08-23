class Solution {
    public static void swap(int[] A,int x,int y){
        int temp = A[x];
        A[x]= A[y];
        A[y]=temp;}
    public void sortColors(int[] arr) {
    int n =arr.length;    
    int low=0;
    int mid= 0;
    int hig= n-1;
    for (int i = 0; mid <= hig; i++) {
        if(arr[mid]==0){
            swap( arr,low,mid);
            low++;
            mid++;
        }
        else if(arr[mid]==1){
            mid++;
        }
        else if(arr[mid]==2){
            swap(arr ,mid,hig);
            hig--;
        }

        
    }
        
    }
}