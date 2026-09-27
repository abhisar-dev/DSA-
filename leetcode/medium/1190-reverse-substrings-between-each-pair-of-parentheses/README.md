# Reverse Substrings Between Each Pair of Parentheses

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given a string `s` that consists of lower case English letters and brackets.

Reverse the strings in each pair of matching parentheses, starting from the innermost one.

Your result should  **not**  contain any brackets.

 

 **Example 1:** 

```
Input: s = "(abcd)"
Output: "dcba"

```

 **Example 2:** 

```
Input: s = "(u(love)i)"
Output: "iloveu"
Explanation: The substring "love" is reversed first, then the whole string is reversed.

```

 **Example 3:** 

```
Input: s = "(ed(et(oc))el)"
Output: "leetcode"
Explanation: First, we reverse the substring "oc", then "etco", and finally, the whole string.

```

 

 **Constraints:** 

- 1 <= s.length <= 2000
- s only contains lower case English characters and parentheses.
- It is guaranteed that all parentheses are balanced.

## Solution

**Language:** Java  
**Runtime:** 25 ms (beats 13.19%)  
**Memory:** 47.4 MB (beats 8.12%)  
**Submitted:** 2026-09-27T15:54:45.001Z  

```java
import java.util.Stack;

public class Solution {
    public String reverseParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (char ch : s.toCharArray()) {
            if (ch == ')') {
                // Collect characters inside the current parentheses
                StringBuilder temp = new StringBuilder();
                while (!stack.isEmpty() && stack.peek() != '(') {
                    temp.append(stack.pop());
                }
                
                // Pop the opening parenthesis '('
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                
                // Push the reversed string back to the stack character by character
                for (int i = 0; i < temp.length(); i++) {
                    stack.push(temp.charAt(i));
                }
            } else {
                stack.push(ch);
            }
        }
        
        // Build the final result from the stack
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        
        return result.reverse().toString();
    }
}

```

---

[View on LeetCode](https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/)