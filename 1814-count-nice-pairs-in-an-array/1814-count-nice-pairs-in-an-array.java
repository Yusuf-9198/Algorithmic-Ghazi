class Solution {
    public int countNicePairs(int[] nums) {
        int n = nums.length;
    HashMap<Integer,Integer> map = new HashMap<>();
    int count=0;
    for (int i = 0; i < n; i++) {
        int temp = nums[i] - rev(nums[i]);
        if(map.containsKey(temp)) {
            count += map.get(temp);
            count %= 1000000007;
            map.put(temp,map.get(temp) +1);
        }
        else map.put(temp, 1);
    }
  
    return count;
}
    static int  rev(int n){
        int temp=0;
        while(n>0){
            temp = temp*10;
            temp = temp + (n%10);

            n=n/10;
        }
        return temp;
    }
}