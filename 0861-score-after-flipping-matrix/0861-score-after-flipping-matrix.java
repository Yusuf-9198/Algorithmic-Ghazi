class Solution {
    public int matrixScore(int[][] arr) {
        // TRY TO MAKE 1 TO EVERY 0th POSITION OF EACH ROW
        int n = arr.length;
        int m = arr[0].length;
        for (int i = 0; i < n; i++) {
            if (arr[i][0] == 0) {// flip that row whoes 0th is '0'
                for (int j = 0; j < m; j++) {
                    if (arr[i][j] == 0)
                        arr[i][j] = 1;
                    else
                        arr[i][j] = 0;

                }

            }
        }
        // flip those column where (no. of 0's > no.of 1's)

        for (int j = 1; j < m; j++) {
            int count0 = 0, count1 = 0;
            for (int i = 0; i < n; i++) {
                if (arr[i][j] == 0)
                    count0++;
                else
                    count1++;

            }
            if (count0 > count1) {// flip that column
                for (int i = 0; i < n; i++) {
                    if (arr[i][j] == 0)
                        arr[i][j] = 1;
                    else
                        arr[i][j] = 0;

                }
            }

        }
        int score = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = m-1; j >= 0; j--) {
                if (arr[i][j] == 1)
                    sum += Math.pow(2, m -1- j);
                else
                    sum += 0;
            }
            score += sum;
        }

        return (Integer) score;

    }
}