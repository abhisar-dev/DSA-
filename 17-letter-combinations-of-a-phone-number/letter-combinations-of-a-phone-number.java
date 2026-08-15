import java.util.ArrayList;
import java.util.List;

public class Solution {
    // Mapping array where index matches the digit (e.g., index 2 corresponds to "abc")
    private static final String[] KEYPAD = {
        "",     "",     "abc",  "def", 
        "ghi",  "jkl",  "mno",  "pqrs", 
        "tuv",  "wxyz"
    };

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        
        // Base case: return empty list if input is empty
        if (digits == null || digits.length() == 0) {
            return result;
        }
        
        // Start the recursive backtracking helper
        backtrack(digits, 0, new StringBuilder(), result);
        return result;
    }

    private void backtrack(String digits, int index, StringBuilder current, List<String> result) {
        // Base case: if the current combination matches the length of input digits
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        // Get the letters corresponding to the current digit
        String letters = KEYPAD[digits.charAt(index) - '0'];

        // Loop through all letters mapped to this digit
        for (int i = 0; i < letters.length(); i++) {
            current.append(letters.charAt(i)); // Choose
            backtrack(digits, index + 1, current, result); // Explore next digit
            current.deleteCharAt(current.length() - 1); // Backtrack (Undo choice)
        }
    }
}
