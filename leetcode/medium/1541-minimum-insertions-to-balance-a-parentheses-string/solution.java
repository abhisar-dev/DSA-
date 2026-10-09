class Solution {
    public int minInsertions(String s) {
        int ans = 0;   // Count of insertions needed
        int x = 0;     // Count of unmatched opening '('
        int n = s.length();
        
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '(') {
                // Each '(' requires two consecutive ')'
                ++x;
            } else { // s.charAt(i) == ')'
                // Check if the next character is also ')'
                if (i < n - 1 && s.charAt(i + 1) == ')') {
                    ++i; // Skip the second ')' since they form a pair '))'
                } else {
                    // Only one ')' found, we need to insert one more ')'
                    ++ans;
                }
                
                // Match with an existing opening '(' if available
                if (x == 0) {
                    ++ans; // Need an opening '('
                } else {
                    --x;   // Consumed one opening '('
                }
            }
        }
        
        // Any remaining unmatched '(' need two ')' each
        ans += x << 1;
        return ans;
    }
}


// ### Complexity Analysis
// * **Time Complexity:** $O(n)$ — We iterate through the string of length $n$ once.
// * **Space Complexity:** $O(1)$ — Only a few integer variables (`ans`, `x`, `i`) are used.
