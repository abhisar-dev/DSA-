# Valid Parenthesis String

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given a string `s` containing only three types of characters: `'('`, `')'` and `' *'`, return `true`* if *`s`* is  **valid** *.

The following rules define a  **valid**  string:

- Any left parenthesis '(' must have a corresponding right parenthesis ')'.
- Any right parenthesis ')' must have a corresponding left parenthesis '('.
- Left parenthesis '(' must go before the corresponding right parenthesis ')'.
- '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".

 

 **Example 1:** 

```
Input: s = "()"
Output: true

```

 **Example 2:** 

```
Input: s = "(*)"
Output: true

```

 **Example 3:** 

```
Input: s = "(*))"
Output: true

```

 **Example 4:** 

```
Input: s = "("
Output: false

```

 

 **Constraints:** 

- 1 <= s.length <= 100
- s[i] is '(', ')' or '*'.

## Solution

**Language:** Java  
**Runtime:** 0 ms (beats 100.00%)  
**Memory:** 42.7 MB (beats 67.95%)  
**Submitted:** 2026-10-04T14:42:58.426Z  

```java
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

```

---

[View on LeetCode](https://leetcode.com/problems/valid-parenthesis-string/)