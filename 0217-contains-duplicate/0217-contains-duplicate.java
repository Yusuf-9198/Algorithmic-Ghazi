class Solution {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for(int i = 0; i < n -1;i++){
            if(nums[i] == nums[i+1]) return true;
        }
        return false;
    }
}

// correct self
// class Solution {
//     public boolean containsDuplicate(int[] nums) {
//         int n = nums.length;
//         HashSet<Integer> set = new HashSet<>();
//         for(int i =0 ; i< n; i++){
//             set.add(nums[i]);
//         }
//         if(n == set.size()) return false;
//         return true;
//     }
// }