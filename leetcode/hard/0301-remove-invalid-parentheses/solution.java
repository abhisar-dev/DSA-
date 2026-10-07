import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int leftRemove = 0;
        int rightRemove = 0;

        // Step 1: Calculate the minimum number of left and right parentheses to remove
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                leftRemove++;
            } else if (ch == ')') {
                if (leftRemove > 0) {
                    leftRemove--;
                } else {
                    rightRemove++;
                }
            }
        }

        Set<String> validStrings = new HashSet<>();
        // Step 2: Backtrack to find all unique valid combinations
        dfs(s, 0, leftRemove, rightRemove, 0, new StringBuilder(), validStrings);
        return new ArrayList<>(validStrings);
    }

    private void dfs(String s, int index, int leftRemove, int rightRemove, int balance, StringBuilder sb, Set<String> result) {
        // Base case: If we reach the end of the string
        if (index == s.length()) {
            if (leftRemove == 0 && rightRemove == 0 && balance == 0) {
                result.add(sb.toString());
            }
            return;
        }

        // Optimization: If balance becomes negative, it means closed brackets outnumber open ones
        if (balance < 0) {
            return;
        }

        char ch = s.charAt(index);
        int len = sb.length();

        // Option 1: Remove the current character (if allowed)
        if (ch == '(' && leftRemove > 0) {
            dfs(s, index + 1, leftRemove - 1, rightRemove, balance, sb, result);
        }
        if (ch == ')' && rightRemove > 0) {
            dfs(s, index + 1, leftRemove, rightRemove - 1, balance, sb, result);
        }

        // Option 2: Keep the current character
        sb.append(ch);
        if (ch == '(') {
            dfs(s, index + 1, leftRemove, rightRemove, balance + 1, sb, result);
        } else if (ch == ')') {
            dfs(s, index + 1, leftRemove, rightRemove, balance - 1, sb, result);
        } else {
            // Non-parenthesis characters are simply carried over
            dfs(s, index + 1, leftRemove, rightRemove, balance, sb, result);
        }
        
        // Backtrack: Remove the appended character for the next recursive cycle
        sb.setLength(len);
    }
}
