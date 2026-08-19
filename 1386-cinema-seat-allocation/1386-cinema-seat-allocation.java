// // correct self: O(M)
// class Solution {
//     public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
//         int row = reservedSeats.length;
//         int max4Grp = n*2;
//         HashMap<Integer , List<Integer>> reserved = new HashMap<>();
//         for(int i =0 ; i< row ;i++){
//             if(reserved.containsKey(reservedSeats[i][0])){
//                 reserved.get(reservedSeats[i][0]).add(reservedSeats[i][1]);
//             }
//             else {
//                 List<Integer> seats = new ArrayList<>();
//                 seats.add(reservedSeats[i][1]);
//                 reserved.put(reservedSeats[i][0],seats);
//             }
//         }
//         for(int key : reserved.keySet()){
//             List<Integer> seats = reserved.get(key);
//             boolean left = true;  
//             boolean right = true; 
//             boolean mid = true;
//             for (int seat : seats) {
//                 if (seat >= 2 && seat <= 5) left = false;
//                 if (seat >= 6 && seat <= 9) right = false;
//                 if (seat >= 4 && seat <= 7) mid = false;
//             }
//             if(left == true && right == true) continue;
//             else if( left || right || mid) max4Grp -=1;
//             else max4Grp -= 2;
//         }
//         return max4Grp;
//     }
// }

// import java.util.HashMap;
// import java.util.HashSet;
// import java.util.Map;
// import java.util.Set;

// class Solution {
//     public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
//         // Map: Row number -> Set of reserved seats in that row
//         Map<Integer, Set<Integer>> reservedMap = new HashMap<>();
//         // Populate the map with reserved seats
//         for (int[] seat : reservedSeats) {
//             int row = seat[0];
//             int col = seat[1];
//             reservedMap.putIfAbsent(row, new HashSet<>());
//             reservedMap.get(row).add(col);
//         }
//         // Completely unreserved rows can fit 2 families each
//         int result = (n - reservedMap.size()) * 2;
//         // Process only rows that have reservations
//         for (Set<Integer> bookedSeats : reservedMap.values()) {
//             // Check availability for the 3 candidate blocks
//             boolean groupA = !bookedSeats.contains(2) && !bookedSeats.contains(3) 
//                           && !bookedSeats.contains(4) && !bookedSeats.contains(5);
//             boolean groupB = !bookedSeats.contains(4) && !bookedSeats.contains(5) 
//                           && !bookedSeats.contains(6) && !bookedSeats.contains(7);
//             boolean groupC = !bookedSeats.contains(6) && !bookedSeats.contains(7) 
//                           && !bookedSeats.contains(8) && !bookedSeats.contains(9);
//             if (groupA && groupC) {
//                 result += 2; // Both Left & Right blocks are free
//             } else if (groupA || groupB || groupC) {
//                 result += 1; // At least one block is free
//             }
//         }
//         return result;
//     }
// }

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // Map: Row number -> Bitmask of reserved seats
        Map<Integer, Integer> rowMasks = new HashMap<>();

        // Build the bitmask for each reserved row
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            rowMasks.put(row, rowMasks.getOrDefault(row, 0) | (1 << col));
        }

        // Unreserved rows can accommodate 2 families each
        int result = (n - rowMasks.size()) * 2;

        // Predefine bitmasks for the 3 target seating blocks
        int maskA = (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5); // Seats 2, 3, 4, 5
        int maskB = (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7); // Seats 4, 5, 6, 7
        int maskC = (1 << 6) | (1 << 7) | (1 << 8) | (1 << 9); // Seats 6, 7, 8, 9

        // Process each modified row using bitwise AND operations
        for (int bookedMask : rowMasks.values()) {
            boolean groupA = (bookedMask & maskA) == 0;
            boolean groupB = (bookedMask & maskB) == 0;
            boolean groupC = (bookedMask & maskC) == 0;

            if (groupA && groupC) {
                result += 2; // Left and Right blocks fit 2 families
            } else if (groupA || groupB || groupC) {
                result += 1; // At least 1 block is free
            }
        }

        return result;
    }
}