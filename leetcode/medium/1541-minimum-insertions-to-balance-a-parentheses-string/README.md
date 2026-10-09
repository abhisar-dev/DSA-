# Minimum Insertions to Balance a Parentheses String

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given a parentheses string `s` containing only the characters `'('` and `')'`. A parentheses string is  **balanced**  if:

- Any left parenthesis '(' must have a corresponding two consecutive right parenthesis '))'.
- Left parenthesis '(' must go before the corresponding two consecutive right parenthesis '))'.

In other words, we treat `'('` as an opening parenthesis and `'))'` as a closing parenthesis.

- For example, "())", "())(())))" and "(())())))" are balanced, ")()", "()))" and "(()))" are not balanced.

You can insert the characters `'('` and `')'` at any position of the string to balance it if needed.

Return  *the minimum number of insertions*  needed to make `s` balanced.

 

 **Example 1:** 

```
Input: s = "(()))"
Output: 1
Explanation: The second '(' has two matching '))', but the first '(' has only ')' matching. We need to add one more ')' at the end of the string to be "(())))" which is balanced.

```

 **Example 2:** 

```
Input: s = "())"
Output: 0
Explanation: The string is already balanced.

```

 **Example 3:** 

```
Input: s = "))())("
Output: 3
Explanation: Add '(' to match the first '))', Add '))' to match the last '('.

```

 

 **Constraints:** 

- 1 <= s.length <= 105
- s consists of '(' and ')' only.

## Solution

**Language:** Java  
**Runtime:** 9 ms (beats 87.26%)  
**Memory:** 47.1 MB (beats 96.22%)  
**Submitted:** 2026-10-09T17:53:53.431Z  

```java
class Solution {
    public int minInsertions(String s) {
        int ans = 0;   // Count of insertions needed
        int x = 0;     // Count of unmatched opening '('
        int n = s.length();
        
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '(') {
                // Each '(' requires two consecutive ')'
                ++x;
            } else { // s.charAt(i) == ')'
                // Check if the next character is also ')'
                if (i < n - 1 && s.charAt(i + 1) == ')') {
                    ++i; // Skip the second ')' since they form a pair '))'
                } else {
                    // Only one ')' found, we need to insert one more ')'
                    ++ans;
                }
                
                // Match with an existing opening '(' if available
                if (x == 0) {
                    ++ans; // Need an opening '('
                } else {
                    --x;   // Consumed one opening '('
                }
            }
        }
        
        // Any remaining unmatched '(' need two ')' each
        ans += x << 1;
        return ans;
    }
}


// ### Complexity Analysis
// * **Time Complexity:** $O(n)$ — We iterate through the string of length $n$ once.
// * **Space Complexity:** $O(1)$ — Only a few integer variables (`ans`, `x`, `i`) are used.

```

---

[View on LeetCode](https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string/)