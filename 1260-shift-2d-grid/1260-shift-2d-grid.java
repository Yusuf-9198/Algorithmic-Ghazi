class Solution {
    public void reverse(int[] arr , int s , int t){
        while(s<=t){
            int temp = arr[s];
            arr[s] = arr[t];
            arr[t] = temp;
            s++;t--;
        }

    }
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[] grid1D = new int[m*n];
        int p=0;
        List<List<Integer>> list = new ArrayList<>(m);
        for(int i =0 ; i< m ; i++){
            for(int j =0 ; j < n ; j++){
                grid1D[p] = grid[i][j];
                p++;
            }
        }
        k %= m*n;
        reverse(grid1D,0,m*n-k-1);
        reverse(grid1D,m*n-k,m*n-1);
        reverse(grid1D,0,m*n-1);
        for (int r = 0; r < m; r++) {
            List<Integer> row = new ArrayList<>(n);
            
            for (int c = 0; c < n; c++) {
                int flatIndex = r * n + c; 
                row.add(grid1D[flatIndex]);
            }
            
            list.add(row);
        }
        return list;
 
    }
}

// ----------------------------------------------------------------------
// Not By  me

// import java.util.ArrayList;
// import java.util.List;

// class Solution {
//     public List<List<Integer>> shiftGrid(int[][] grid, int shifts) {
//         int m = grid.length;
//         int n = grid[0].length;
//         int totalElements = m * n;
//         shifts = shifts % totalElements;
//         List<List<Integer>> result = new ArrayList<>();
//         for (int k = 0; k < m; k++) {
//             List<Integer> row = new ArrayList<>();
//             for (int t = 0; t < n; t++) {
//                 row.add(0);
//             }
//             result.add(row);
//         }
//         for (int k = 0; k < m; k++) {
//             for (int t = 0; t < n; t++) {
//                 int oldFlatIndex = k * n + t;
//                 int newFlatIndex = (oldFlatIndex + shifts) % totalElements;
//                 int newRow = newFlatIndex / n;
//                 int newCol = newFlatIndex % n;
//                 result.get(newRow).set(newCol, grid[k][t]);
//             }
//         }
//         return result;
//     }
// }