class Solution {
    public void rev(int[] arr , int l , int r){
        int i = l, j = r;
        while(i<j){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;j--;
        }
    }
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k%n;
        rev(nums,0,n-k-1);
        rev(nums,n-k,n-1);
        rev(nums,0,n-1);




        // if(n ==1 ) return;
        // int[] result = IntStream.concat(Arrays.stream(nums), Arrays.stream(nums)).toArray();
        // int[] subArray = Arrays.copyOfRange(result, n-k, n-k + 1);
        // for(int i =0 ; i < n ; i++){
        //     nums[i] = subArray[i];
        // }

        
    }
}