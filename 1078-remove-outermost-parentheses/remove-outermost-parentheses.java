class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        int opened = 0; // Tracks the current nesting depth
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                // If opened > 0, it means this '(' is not the outermost one
                if (opened > 0) {
                    sb.append(c);
                }
                opened++;
            } else { // c == ')'
                opened--;
                // If opened > 0, it means this ')' is not the outermost one
                if (opened > 0) {
                    sb.append(c);
                }
            }
        }
        
        return sb.toString();
    }
}
