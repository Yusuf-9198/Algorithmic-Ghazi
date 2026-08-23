import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> spiralOrder(int[][] arr) {
        ArrayList<Integer> num=new ArrayList<>();
        int row = arr.length;
        int col = arr[0].length;
        int minR = 0, minC = 0, maxR = row - 1, maxC = col - 1;
        while(minR<=maxR && minC<=maxC) {
            for (int j = minC; j <= maxC ; j++) {
                num.add(arr[minR][j]);
                

            }
            minR++;
            if(minR>maxR || minC>maxC) break;
            for (int i = minR; i <= maxR; i++) {
                num.add(arr[i][maxC]);
            }
            maxC--;
            if(minR>maxR || minC>maxC) break;

            for (int j = maxC; j >= minC ; j--) {
                num.add(arr[maxR][j]);

            }
            maxR--;
            if(minR>maxR || minC>maxC) break;

            for (int i = maxR; i >= minR ; i--) {
                num.add(arr[i][minC]);

            }
            minC++;

        }
        return num;
        
    }
}