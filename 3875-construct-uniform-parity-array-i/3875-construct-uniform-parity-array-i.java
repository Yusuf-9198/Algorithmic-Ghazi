class Solution {
    public boolean uniformArray(int[] nums1) {
        int n = nums1.length;
        int[] nums2 = new int[n];
        if(nums1[0]%2 == 0){
            nums2[0] = nums1[0];
            for(int i = 1; i< n;i++){
                if(nums1[i] %2 != 0) nums1[i] -= nums2[i-1] +1;
                nums2[i] = nums1[i];
            }
        }
        else{
            nums2[0] = nums1[0];
            for(int i = 1; i< n;i++){
                if(nums1[i] %2 == 0) nums1[i] -= nums2[i-1]+1;
                nums2[i] = nums1[i];
            }

        }
        return true;
    }
}

// // Metos jindagi
// class Solution {
//     public boolean uniformArray(int[] nums1) {
//         return true;    
//     }
// }