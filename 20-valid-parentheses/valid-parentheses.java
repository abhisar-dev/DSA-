import java.util.Deque;
import java.util.ArrayDeque;

class Solution {
    public boolean isValid(String s) {
        // Fast failure: a valid string must have an even length
        if (s.length() % 2 != 0) {
            return false;
        }

        // Use Deque for stack operations as it is faster than the legacy Stack class
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            // If it's an opening bracket, push the expected closing bracket onto the stack
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } 
            // If it's a closing bracket, check if it matches the top of the stack
            else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }

        // If the stack is empty, all brackets were correctly matched
        return stack.isEmpty();
    }
}
