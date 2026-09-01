import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length, n = classroom[0].length();
        int startR = -1, startC = -1;
        List<int[]> litters = new ArrayList<>();
        // Locate starting position 'S' and store coordinates of all litter cells 'L'
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = classroom[r].charAt(c);
                if (ch == 'S') { startR = r; startC = c; }
                else if (ch == 'L') litters.add(new int[]{r, c});
            }
        }
        int totalLitter = litters.size();
        int targetMask = (1 << totalLitter) - 1; // Bitmask representing all litters collected (all 1s)
        // Map grid coordinates to unique bit index (0 to totalLitter - 1)
        int[][] litterIdx = new int[m][n];
        for (int i = 0; i < totalLitter; i++) {
            int[] pos = litters.get(i);
            litterIdx[pos[0]][pos[1]] = i;
        }
        // maxEnergy[r][c][mask] tracks max remaining energy for a (position, mask) state
        int[][][] maxEnergy = new int[m][n][1 << totalLitter];
        for (int[][] layer : maxEnergy) {
            for (int[] row : layer) Arrays.fill(row, -1);
        }
        // Queue stores primitive bit-packed ints: [mask (10b) | energy (8b) | col (5b) | row (5b)]
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(pack(startR, startC, energy, 0));
        maxEnergy[startR][startC][0] = energy;
        int moves = 0;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        // Level-order BFS traversal guarantees finding the minimum number of moves
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                int r = curr & 31;            // Unpack row (bits 0-4)
                int c = (curr >> 5) & 31;      // Unpack col (bits 5-9)
                int e = (curr >> 10) & 255;    // Unpack energy (bits 10-17)
                int mask = curr >> 18;         // Unpack collected mask (bits 18+)
                if (mask == targetMask) return moves; // Goal reached: all litter collected
                if (e == 0) continue;          // Cannot move without remaining energy
                for (int[] d : dirs) {
                    int nr = r + d[0], nc = c + d[1];
                    // Boundary check and obstacle check ('X')
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n || classroom[nr].charAt(nc) == 'X') continue;
                    int nextEnergy = e - 1, nextMask = mask;
                    char cell = classroom[nr].charAt(nc);
                    if (cell == 'L') nextMask |= (1 << litterIdx[nr][nc]); // Set litter bit as collected
                    else if (cell == 'R') nextEnergy = energy;           // Reset energy to max capacity
                    // Prune state unless it yields strictly higher remaining energy
                    if (nextEnergy > maxEnergy[nr][nc][nextMask]) {
                        maxEnergy[nr][nc][nextMask] = nextEnergy;
                        queue.offer(pack(nr, nc, nextEnergy, nextMask));
                    }
                }
            }
            moves++; // Increment step count per BFS level
        }
        return -1; // Return -1 if unreachable
    }
    // Packs (row, col, energy, mask) into a single 32-bit int to eliminate garbage collection
    private int pack(int r, int c, int e, int mask) {
        return r | (c << 5) | (e << 10) | (mask << 18);
    }
}