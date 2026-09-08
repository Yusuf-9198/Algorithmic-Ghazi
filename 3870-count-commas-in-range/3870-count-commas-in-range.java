class Solution {
    public int countCommas(int n) {
        if(n<1000) return 0;
        return n-999;
    }
}

// class Solution {
//     public int countCommas(int n) {
//         if (n < 1000)
//             return 0;
//         int count = 0;
//         for (int i = 1000; i <= n; i++) {
//             count++;
//         }
//         return count;
//     }
// }

// // correct self : not preffered
// class Solution {
//     private int countDigit(int num){
//         int digit = 0;
//         while(num>0){
//             digit++;
//             num/=10;
//         }
//         return digit;
//     }
//     public int countCommas(int n) {
//         int count = 0;
//         if(n<1000) return 0;
//         for(int  i = 1000 ; i<= n ; i++){
//             if(countDigit(i) %3 == 0) count += (countDigit(i)/3)-1;
//             else count+=  countDigit(i)/3;
//         }
//         return count;

//     }
// }