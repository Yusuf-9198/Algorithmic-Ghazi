class Solution {
    public double myPow(double x, int n) {
        if(x == 0) return 0;
        if (n == 0)
            return 1;
        double numPow = myPow(x, n / 2);
        if (n > 0) {
            numPow = numPow * numPow;
            if (n % 2 == 0)
                return numPow;
            return x * numPow;
        } else {
            numPow = numPow * numPow;
            if (n % 2 == 0)
                return numPow;
            return  numPow/x;
        }
    }
}