/*
Story:
0. Indegree array.
1. K -> Traversal (BFS)
2. Mark suspicious nodes & update Indegree of nodes.
3. (suspicious && Indegree != 0) -> {0, 1, 2, ... n - 1}
   else remove that node.
*/

import java.util.*;

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        int[] indegree = new int[n];
        for (int[] inv : invocations) {
            adj.get(inv[0]).add(inv[1]);
            indegree[inv[1]]++;
        }

        boolean[] suspicious = new boolean[n];
        Queue<Integer> q = new LinkedList<>();

        q.add(k);
        suspicious[k] = true;

        while (!q.isEmpty()) {
            int curr = q.poll();

            for (int neighbor : adj.get(curr)) {
                indegree[neighbor]--;
                if (!suspicious[neighbor]) {
                    suspicious[neighbor] = true;
                    q.add(neighbor);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (suspicious[i] && indegree[i] != 0) {
                List<Integer> allMethods = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    allMethods.add(j);
                }
                return allMethods;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) {
                result.add(i);
            }
        }

        return result;
    }
}