class Solution {
    public int finalValueAfterOperations(String[] arr) {
        int x = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("++X")) x += 1;
            else if (arr[i].equals("X++")) x += 1;
            else if (arr[i].equals("--X")) x -= 1;
            else x -= 1;
        }
        return x;
    }
}
