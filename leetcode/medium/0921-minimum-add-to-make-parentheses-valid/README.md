# Minimum Add to Make Parentheses Valid

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

A parentheses string is valid if and only if:

- It is the empty string,
- It can be written as AB (A concatenated with B), where A and B are valid strings, or
- It can be written as (A), where A is a valid string.

You are given a parentheses string `s`. In one move, you can insert a parenthesis at any position of the string.

- For example, if s = "()))", you can insert an opening parenthesis to be "(()))" or a closing parenthesis to be "())))".

Return  *the minimum number of moves required to make* `s` *valid*.

 

 **Example 1:** 

```
Input: s = "())"
Output: 1

```

 **Example 2:** 

```
Input: s = "((("
Output: 3

```

 

 **Constraints:** 

- 1 <= s.length <= 1000
- s[i] is either '(' or ')'.

## Solution

**Language:** Java  
**Runtime:** 0 ms (beats 100.00%)  
**Memory:** 42.7 MB (beats 74.52%)  
**Submitted:** 2026-10-06T14:42:49.350Z  

```java
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

```

---

[View on LeetCode](https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/)