
class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        double sum = 0;
        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            int sign = (i < n / 2) ? 1 : -1;
            if (c == '?') {
                sum += sign * 4.5;
            } else {
                sum += sign * (c - '0');
            }
        }
        return sum != 0;
    }
}

// correct 
// class Solution {
//     public boolean sumGame(String num) {
//         int len = num.length();
//         int markLeft = 0 , markRight = 0;
//         int firstSum = 0, secondSum =0;
//         Set<Integer> locOf2 = new HashSet<>();
//         for(int i = 0 ;i< len ;i++){
//             if(i < len/2) {
//                 if(num.charAt(i)!='?')  firstSum += Character.getNumericValue(num.charAt(i));
//                 else markLeft++;
//             }
//             else {
//                 if(num.charAt(i)!='?') secondSum += Character.getNumericValue(num.charAt(i));
//                 else markRight++;
//             }
//         }
//         int diff = firstSum - secondSum;
//         int qDiff = markLeft - markRight;
            
//        if ((markLeft + markRight) % 2 != 0)  return true;
//         if (diff + (qDiff / 2) * 9 == 0) {
//             return false; 
//         } 
//          else  return true; 
//     }
// }




