class Solution {
    public int removeElement(int[] nums, int val) {
        int count =0;
        int n= nums.length;
        int[] arr = Arrays.copyOf(nums,n);
        for(int i = 0 ; i<n;i++){
            if(nums[i] != val) count++;

        }
        int k=0;
        for(int i = 0 ; i<n;i++){
            if(arr[i] != val) {
                nums[k] = arr[i];
                k++;
            }

        }
        return count;
        
    }
}