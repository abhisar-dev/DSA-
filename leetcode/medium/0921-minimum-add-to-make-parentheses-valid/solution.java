class Solution {
    public int minAddToMakeValid(String s) {
        int openCount = 0;
        int closeRequired = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                openCount++;
            } else {
                // If we see ')' and have an unmatched '(', they balance out
                if (openCount > 0) {
                    openCount--;
                } else {
                    // No unmatched '(' available, so we need to add an '(' before this ')'
                    closeRequired++;
                }
            }
        }
        
        // Total moves = unmatched '(' that need ')' + unmatched ')' that need '('
        return openCount + closeRequired;
    }
}
