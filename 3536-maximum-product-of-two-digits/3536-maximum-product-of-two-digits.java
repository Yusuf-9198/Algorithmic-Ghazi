// // correct but not optimised
// class Solution {
//     public int maxProduct(int n) {
//         if( n < 10) return n;
//         List<Integer> digits = new ArrayList<>();
//         while(n>=1){
//             digits.add(n%10);
//             n /= 10;
//         }
//         int maxPro = 0;
//         int i = 0 , j=1;
//         while(i< digits.size()){
//             if(j< digits.size()){
//                 maxPro = Math.max(maxPro , digits.get(i) * digits.get(j));
//                 j++;
//             }else {
//                 i++;
//                 j=i+1;
//             }
//         }
//         return maxPro;
//     }
// }

// Also correct
class Solution {
    public int maxProduct(int n) {
        int max1 = 0; // Largest digit seen so far
        int max2 = 0; // Second-largest digit seen so far
        
        while (n > 0) {
            int digit = n % 10;
            n /= 10;
            
            if (digit > max1) {
                max2 = max1; // Current max becomes second max
                max1 = digit;
            } else if (digit > max2) {
                max2 = digit;
            }
        }
        
        return max1 * max2;
    }
}

