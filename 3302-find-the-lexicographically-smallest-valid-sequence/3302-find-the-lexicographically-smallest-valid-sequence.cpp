// Solution based on codestorywithMIK (LeetCode 3302)
#include <vector>
#include <string>

using namespace std;

class Solution {
public:
    vector<int> validSequence(string word1, string word2) {
        int n = word1.length();
        int m = word2.length();
        
        // Precomputation vector to store right matched length
        vector<int> right_matched_len(n, 0);
        int right_count = 0;
        int j = m - 1;
        
        // Right to Left Pass
        for (int i = n - 1; i >= 0; i--) {
            if (j >= 0 && word1[i] == word2[j]) {
                right_count++;
                j--;
            }
            right_matched_len[i] = right_count;
        }
        
        vector<int> result;
        bool changePower = true; // Flag for 1 allowed modification
        
        int i = 0;
        j = 0;
        
        // Left to Right Greedy Matching
        while (i < n && j < m) {
            if (word1[i] == word2[j]) {
                result.push_back(i);
                j++;
            } else if (changePower && (i + 1 < n ? right_matched_len[i + 1] : 0) >= (m - j - 1)) {
                result.push_back(i);
                j++;
                changePower = false; // Mark change power as used
            }
            i++;
        }
        
        if (j == m) return result;
        return {};
    }
};