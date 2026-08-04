class Solution {
    public List<Integer> findMissingElements(int[] nums) {
         int n = nums.length;
        int max = nums[0];
        int min = nums[0];
        List<Integer> list = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        for(int i =0 ; i< n ; i++){
            set.add(nums[i]);
            if(nums[i]> max) max = nums[i];
            if(nums[i] < min) min = nums[i];
        }
        for(int i = min ; i< max ; i++){
            if(!set.contains(i)) list.add(i);
        }
        return list;
    }
}

// correct( self)
// class Solution {
//     public List<Integer> findMissingElements(int[] nums) {
//         int n = nums.length;
//         int max = nums[0];
//         int min = nums[0];
//         List<Integer> list = new ArrayList<>();
//         for(int i =0 ; i< n ; i++){
//             list.add(nums[i]);
//             if(nums[i]> max) max = nums[i];
//             if(nums[i] < min) min = nums[i];
//         }    
//         for(int i =min ; i<=max ; i++){
//             if(list.contains(i)) list.remove(Integer.valueOf(i));
//             else list.add(i);
//         }
//         return list; 
//     }
// }