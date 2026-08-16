class Solution {
    public boolean stoneGameIX(int[] stones) {
        int n = stones.length;
        int turn = 1; // 1 -> Alice , 0 -> Bob
        int sum = 0;
        int[] count = new int[3];
        for(int stone : stones){
            if(stone%3 == 0) count[0]++;
            else if(stone%3 == 1) count[1]++;
            else count[2]++;
        }
        if(count[0] % 2 == 0) 
            return count[1]>=1 && count[2]>=1;
        return count[2] - count[1] > 2 || count[1] - count[2] > 2;


    }
}