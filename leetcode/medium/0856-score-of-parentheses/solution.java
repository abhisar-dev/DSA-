class Solution {
    public int scoreOfParentheses(String s) {
        int score = 0;
        int depth = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                depth++; // Increase nesting level
            } else {
                depth--; // Decrease nesting level
                // If it is a core "()", calculate its weight based on current depth
                if (s.charAt(i - 1) == '(') {
                    score += 1 << depth; // Equivalent to 2^depth
                }
            }
        }
        return score;
    }
}
