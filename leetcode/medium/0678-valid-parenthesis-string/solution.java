class Solution {
    public boolean checkValidString(String s) {
        int low = 0;  // Minimum possible open brackets
        int high = 0; // Maximum possible open brackets

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                low++;
                high++;
            } else if (c == ')') {
                low--;
                high--;
            } else if (c == '*') {
                // If '*' acts as ')', open count decreases
                // If '*' acts as '(', open count increases
                low--; 
                high++;
            }

            // If high is negative, even treating every '*' as '(' cannot save it
            if (high < 0) {
                return false;
            }

            // We can never have a negative amount of valid open brackets
            if (low < 0) {
                low = 0; 
            }
        }

        // If low is 0, it means we can successfully balance all brackets
        return low == 0;
    }
}
