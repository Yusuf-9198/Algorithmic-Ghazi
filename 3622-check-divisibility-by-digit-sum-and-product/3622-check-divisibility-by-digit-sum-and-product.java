class Solution {
    public boolean checkDivisibility(int n) {
        int digitsum = 0;
        int digitpro = 1;
        int i = n;
        while(i>0){
            digitsum += i%10;
            digitpro *= i%10;
            i /=10;
        }
        int sum = digitsum + digitpro;
        if(n % sum == 0) return true;
        return false;
    }
}

// // correct 
// class Solution {
//     private int digitSum(int n){
//         int sum = 0;
//         while(n>0){
//             sum += n%10;
//             n /= 10;
//         }
//         return sum;
//     }
//     private int digitProduct(int n){
//         int pro = 1;
//         while(n>0){
//             pro *= n%10;
//             n /= 10;
//         }
//         return pro;
//     }
//     public boolean checkDivisibility(int n) {
//         int digitsum = digitSum(n);
//         int digitproduct = digitProduct(n);
//         int sum = digitsum + digitproduct;
//         if(n % sum == 0) return true;
//         return false;
//     }
// }
