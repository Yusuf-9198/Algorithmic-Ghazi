// // correct but not optimised O(nlogn)
// class Solution {
//     public int maxProduct(int[] nums) {
//         int n= nums.length;
//         Arrays.sort(nums);
//         return (nums[n-1]-1)*(nums[n-2]-1); 
//     }
// }

// optimised 
class Solution {
    public int maxProduct(int[] nums) {
        int n= nums.length;
        int max1 = 0 , max2 = 0;
        for(int i =0 ; i< n ; i++){
            if(nums[i]> max1){
                max2 = max1;
                max1 = nums[i];
            }
            else if(max2 < nums[i]){
                max2 = nums[i];
            }
        }
        return (max1 - 1) * (max2 - 1);
    }
}