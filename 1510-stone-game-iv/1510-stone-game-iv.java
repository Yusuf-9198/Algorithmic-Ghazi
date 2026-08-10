// class Solution {
//     public boolean winnerSquareGame(int n) {
//         // dp[i] will store whether the current player can WIN starting with 'i' stones.
//         // True  -> Current player wins
//         // False -> Current player loses
//         boolean[] dp = new boolean[n + 1];
//         // Base Case:
//         // dp[0] is false by default. 
//         // If 0 stones are left, the player whose turn it is cannot make any move, 
//         // so that player loses the game.
//         // Build the solution from 1 stone up to 'n' stones (Bottom-Up DP)
//         for (int i = 1; i <= n; i++) {
//             // Try every valid non-zero square number (k^2) that is <= i
//             for (int k = 1; k * k <= i; k++) {
//                 // RECURSION / DP LOGIC:
//                 // If the current player takes k^2 stones, (i - k * k) stones remain for the opponent.
//                 // If dp[i - k * k] is false, it means the opponent loses from that state.
//                 // Since our opponent loses, the current player WINS!
//                 if (!dp[i - k * k]) {
//                     dp[i] = true;
//                     break; // Found at least one winning move for state 'i', no need to check further k values
//                 }
//             }
//             // If the loop finishes and dp[i] is still false, it means EVERY choice of k^2 
//             // left the opponent in a winning position, so state 'i' is a guaranteed losing state.
//         }
//         // Return whether Alice (who moves first at state 'n') wins the game
//         return dp[n];
//     }
// }

class Solution {
    // memo[i] stores:
    // true  -> Player starting with 'i' stones wins
    // false -> Player starting with 'i' stones loses
    // null  -> State 'i' hasn't been solved yet
    private Boolean[] memo;

    public boolean winnerSquareGame(int n) {
        memo = new Boolean[n + 1];
        
        // Solve for 'n' stones starting with Alice's move
        return solve(n);
    }

    private boolean solve(int n) {
        // Base Case: 0 stones left -> whoever's turn it is loses immediately
        if (n == 0) {
            return false;
        }

        // Memoization Check: return cached answer if already calculated
        if (memo[n] != null) {
            return memo[n];
        }

        // Try picking 1^2, 2^2, 3^2, ... as long as k^2 <= n
        for (int k = 1; k * k <= n; k++) {
            
            // "Recursion Leap of Faith":
            // Pass (n - k * k) stones to the opponent.
            // solve(n - k * k) asks: "Does the opponent win with (n - k * k) stones?"
            // If it returns false, the opponent lost, which means current player wins!
            if (!solve(n - k * k)) {
                return memo[n] = true; // Current player wins from state 'n'
            }
        }

        // If no choice of k^2 resulted in an opponent loss, current player loses from state 'n'
        return memo[n] = false;
    }
}