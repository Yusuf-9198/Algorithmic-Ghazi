class Solution {
    // public int maxOperations(int[] nums, int k) {
    //     int n = nums.length;
    //     int countop= 0;
    //     HashMap<Integer,Integer> map = new HashMap<>();
    //     for(int i =0 ; i<n; i++){
    //         int key = k- nums[i];
    //         if(map.containsKey(key) && map.get(key) > 0){
    //             map.put(key,map.get(key)-1);
    //             countop++;

    //         }
    //         else map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
    //     }
    //     return countop;
    // }
    public int maxOperations(int[] nums, int k) {
        Arrays.sort(nums);
        var left = 0; 
        var right = nums.length - 1;
        var count = 0;
        while (left < right) {
            if (nums[left] + nums[right] < k) {
                left++;
            } else if (nums[left] + nums[right] > k) {
                right--;
            } else if (nums[left] + nums[right] == k) {
                left++;
                right--;
                count++;
            }
        }
        System.gc();
        return count;
    }
}