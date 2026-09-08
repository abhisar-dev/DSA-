# Generate Parentheses

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given `n` pairs of parentheses, write a function to  *generate all combinations of well-formed parentheses*.

 

 **Example 1:** 

```
Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]

```

 **Example 2:** 

```
Input: n = 1
Output: ["()"]

```

 

 **Constraints:** 

- 1 <= n <= 8

## Solution

**Language:** Java  
**Runtime:** 2 ms (beats 69.51%)  
**Memory:** 44.8 MB (beats 32.53%)  
**Submitted:** 2026-09-08T16:22:51.599Z  

```java
/*
 * @lc app=leetcode id=22 lang=java
 *
 * [22] Generate Parentheses
 */
class Solution {
    public List<String> generateParenthesis(int n) {
      List<String> list = new ArrayList<>();
      backtrack(list, "", 0, 0, n);
      return list;
    }

    public void backtrack(List<String> list, String str, int open, int close, int n) {
      if (str.length() == 2 * n) {
        list.add(str);
        return;
      }

      if (open < n) {
        backtrack(list, str + "(", open + 1, close, n);
      }
      if (close < open) {
        backtrack(list, str + ")", open, close + 1, n);
      }
    }
}

```

---

[View on LeetCode](https://leetcode.com/problems/generate-parentheses/)