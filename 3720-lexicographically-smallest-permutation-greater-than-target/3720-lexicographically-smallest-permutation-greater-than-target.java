class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for (int i = 0; i < n; i++) {
            freq[s.charAt(i) - 'a']++;
        }

        // Step 1: Try matching target as far as possible
        int matchLen = 0;
        int[] freqCopy = freq.clone();
        while (matchLen < n) {
            int charIdx = target.charAt(matchLen) - 'a';
            if (freqCopy[charIdx] > 0) {
                freqCopy[charIdx]--;
                matchLen++;
            } else {
                break;
            }
        }

        // Step 2: Backtrack from matchLen down to 0 to find the rightmost position
        // where we can insert a character strictly greater than target.charAt(i)
        for (int i = matchLen; i >= 0; i--) {
            // Reconstruct available frequencies for target[0...i-1]
            int[] currentFreq = freq.clone();
            for (int j = 0; j < i; j++) {
                currentFreq[target.charAt(j) - 'a']--;
            }

            int targetCharIdx = (i < n) ? (target.charAt(i) - 'a') : -1;

            // Look for a character larger than target.charAt(i)
            for (int c = targetCharIdx + 1; c < 26; c++) {
                if (currentFreq[c] > 0) {
                    StringBuilder result = new StringBuilder();
                    // Append matching prefix target[0...i-1]
                    result.append(target.substring(0, i));
                    
                    // Append larger character
                    result.append((char) ('a' + c));
                    currentFreq[c]--;

                    // Append remaining characters in ascending order
                    for (int rem = 0; rem < 26; rem++) {
                        while (currentFreq[rem] > 0) {
                            result.append((char) ('a' + rem));
                            currentFreq[rem]--;
                        }
                    }
                    return result.toString();
                }
            }
        }

        return "";
    }
}

// // correct 
// class Solution { 
//     boolean isGreater;
//     String result;
//     private boolean slove(StringBuilder current , int[] freq, String target,boolean greater, int i){
//         if(i == target.length()){
//             if(greater){
//                 result = current.toString();
//                 return true;
//             }
//             return false;
//         }
//         if (greater) {
//             for (char ch = 'a'; ch <= 'z'; ch++) {
//                 while (freq[ch - 'a'] > 0) {
//                     current.append(ch);
//                     freq[ch - 'a']--;
//                 }
//             }
//             result = current.toString();
//             return true;
//         }
//         for(char ch='a' ; ch <= 'z' ; ch++){
//             if(freq[ch - 'a'] == 0) continue;
//             if(greater == false && ch < target.charAt(i)) continue;
//             // DO
//             current.append(ch);;
//             freq[ch - 'a']--;
//             // explore
//             boolean isGreater = greater || ch>target.charAt(i);
//             if(slove(current, freq,target,isGreater,i+1)) return true;
//             // undo
//             current.deleteCharAt(current.length() - 1);
//             freq[ch-'a']++;
//         }
//         result = current.toString();
//         return false;

//     }
//     public String lexGreaterPermutation(String s, String target) {
//         int len = s.length();  
//         int[] freq = new int[26];
//         result = "";
//         StringBuilder current = new StringBuilder();
//         isGreater = false;
//         for(int i = 0; i< len; i++){
//             freq[s.charAt(i)-'a']++;
//         }
//         if(slove(current,freq,target,isGreater,0)) return result;
//         return "";   
//     }
// }