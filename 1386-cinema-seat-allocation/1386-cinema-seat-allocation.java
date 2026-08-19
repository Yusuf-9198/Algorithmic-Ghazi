class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        int row = reservedSeats.length;
        int max4Grp = n*2;
        HashMap<Integer , List<Integer>> reserved = new HashMap<>();
        for(int i =0 ; i< row ;i++){
            if(reserved.containsKey(reservedSeats[i][0])){
                reserved.get(reservedSeats[i][0]).add(reservedSeats[i][1]);
            }
            else {
                List<Integer> seats = new ArrayList<>();
                seats.add(reservedSeats[i][1]);
                reserved.put(reservedSeats[i][0],seats);
            }
        }
        for(int key : reserved.keySet()){
            List<Integer> seats = reserved.get(key);
            boolean left = true;  
            boolean right = true; 
            boolean mid = true;
            for (int seat : seats) {
                if (seat >= 2 && seat <= 5) left = false;
                if (seat >= 6 && seat <= 9) right = false;
                if (seat >= 4 && seat <= 7) mid = false;
            }
            if(left == true && right == true) continue;
            else if( left || right || mid) max4Grp -=1;
            else max4Grp -= 2;

        }
        return max4Grp;

        
    }
}