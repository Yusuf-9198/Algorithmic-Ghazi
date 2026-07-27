class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] priPro = new int[n];
        int[] sufPro = new int[n];
        sufPro[n-1] = 1;
        priPro[0] = 1;
        for(int  i = 1; i < n ; i++){
            priPro[i] = priPro[i-1] * nums[i-1];
        }
        for(int i = n-2; i >=0 ; i--){
            sufPro[i] = sufPro[i+1]*nums[i+1];
        }
        int[] answer = new int[n];
        for(int i =0 ; i< n ; i++){
            answer[i] = priPro[i] * sufPro[i];
        }
        return answer;

        
    }
}