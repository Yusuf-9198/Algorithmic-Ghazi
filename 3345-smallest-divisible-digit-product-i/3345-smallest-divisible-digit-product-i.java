class Solution {
    private int pro(int n) {
        int product = 1;
        while (n > 0) {
            product *= n % 10;
            n /= 10;
        }
        return product;
    }
    public int smallestNumber(int n, int t) {
        while (pro(n) % t != 0) {
            n++;
        }
        return n;
    }
}





// // correct self
// class Solution {
//     public int pro(int n) {
//         int product = 1;
//         while (n > 0) {
//             product *= n % 10;
//             n = n / 10;
//         }
//         return product;
//     }
//     public int smallestNumber(int n, int t) {
//         int product = pro(n);
//         if (product % t == 0)
//             return n;
//         else {
//             return smallestNumber(++n, t);
//         }
//     }
// }