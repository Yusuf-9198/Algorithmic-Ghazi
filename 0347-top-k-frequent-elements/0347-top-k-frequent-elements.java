class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int n = nums.length;
        if( k== n) return nums; 
        HashMap<Integer , Integer> map = new HashMap<>();
        for(int i = 0 ; i< n ; i++){
            map.put(nums[i], map.getOrDefault(nums[i],0) + 1);
            
        }
        Queue<Integer> heap = new PriorityQueue<>(
            (a,b) -> map.get(a) - map.get(b)
        );
        for(int key : map.keySet()){
            heap.add(key);
            if(heap.size() >k){
                heap.poll();
            }
        }
        int[] ans = new int[k];
        for(int i = 0;i<k ;i++){
            ans[i] = heap.poll();
        }
        return ans;

        
    }
}