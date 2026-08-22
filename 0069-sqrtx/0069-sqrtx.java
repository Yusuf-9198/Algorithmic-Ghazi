class Solution {
    public int mySqrt(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;
    int low = 0 , high = n; 
    while(low<=high){
        int mid = (high - low)/2 + low;
        if(mid == n/mid){
           return mid;
        } else if (mid >  n/mid) { high = mid -1;
        }else low = mid + 1;
    }
    return high;
        
    }
}