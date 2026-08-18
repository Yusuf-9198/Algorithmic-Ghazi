class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        int maxEle = -1;
        // Map<Integer,Integer> map = new HashMap<>();
        int[] freq = new int[51];
        for(int i =0 ; i< n ; i++){
            // map.put(nums[i],map.getOrDefault(nums[i], 0)+1);
            freq[nums[i]] += 1;
            maxEle = Math.max(maxEle , nums[i]);
        }
        if(k ==1){
            for(int i=50;i>=0;i--){
                if(freq[i]==1) return i;
            }
        }
        else if(k == n){
            return maxEle;
        }
        else{
            int a = -1;
            int b= -1;
            if(freq[nums[0]] == 1) a = nums[0];
            if(freq[nums[n-1]] == 1) b = nums[n-1];
            return Math.max(a,b);
        }
        return -1;
        
    }
}