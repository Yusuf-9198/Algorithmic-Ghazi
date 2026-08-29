import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        // Step 1: Clone and sort to place reachable numbers adjacent to each other.
        int[] copyNums = nums.clone();
        Arrays.sort(copyNums);
        // Map to store: Group ID -> Sorted list of numbers belonging to that group.
        Map<Integer, List<Integer>> groups = new HashMap<>();
        // Map to store: Number -> Assigned Group ID.
        Map<Integer, Integer> numToGrp = new HashMap<>();
        List<Integer> grp = new ArrayList<>();
        int grpNum = 0;
        // Step 2: Seed group 0 with the smallest element.
        grp.add(copyNums[0]);
        numToGrp.put(copyNums[0], grpNum);
        // Step 3: Group numbers whose adjacent difference is <= limit.
        for (int i = 1; i < n; i++) {
            if (copyNums[i] - copyNums[i - 1] <= limit) {
                grp.add(copyNums[i]);
            } else {
                groups.put(grpNum, new ArrayList<>(grp));
                grpNum++;
                grp.clear();
                grp.add(copyNums[i]);
            }
            numToGrp.put(copyNums[i], grpNum);
        }
        groups.put(grpNum, new ArrayList<>(grp));
        // Step 4: Track read pointers for each group during array reconstruction.
        Map<Integer, Integer> groupIndex = new HashMap<>();
        // Step 5: Replace original positions with smallest remaining values from their group.
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int g = numToGrp.get(nums[i]);
            int idx = groupIndex.getOrDefault(g, 0);
            result[i] = groups.get(g).get(idx);
            groupIndex.put(g, idx + 1);
        }
        return result;
    }
}