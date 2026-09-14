class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        long midx1, midx2, midy1, midy2;
        midx1 = (long)rec1[2] - rec1[0];
        midx2 = (long)rec2[2] - rec2[0];
        midy1 = (long)rec1[3] - rec1[1];
        midy2 = (long)rec2[3] - rec2[1];

        // Cast coordinates to long before adding to prevent 32-bit integer overflow
        long actulLengthx = Math.abs(((long)rec1[0] + rec1[2]) - ((long)rec2[0] + rec2[2]));
        long actualLengthy = Math.abs(((long)rec1[1] + rec1[3]) - ((long)rec2[1] + rec2[3]));

        long midlenX1 = midx1 + midx2;
        long midlenY1 = midy1 + midy2;

        if (actulLengthx < midlenX1) {
            if (actualLengthy < midlenY1) return true;
        }

        return false;
    }
}