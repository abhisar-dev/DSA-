# Distinct Subsequences

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

Given two strings s and t, return  *the number of distinct*   ***subsequences** ** of  *s*  which equals *t.

The test cases are generated so that the answer fits on a 32-bit signed integer.

 

 **Example 1:** 

```
Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from s.
rabbbit
rabbbit
rabbbit

```

 **Example 2:** 

```
Input: s = "babgbag", t = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from s.
babgbag
babgbag
babgbag
babgbag
babgbag
```

 

 **Constraints:** 

- 1 <= s.length, t.length <= 1000
- s and t consist of English letters.

## Solution

**Language:** Java  
**Runtime:** 20 ms (beats 53.25%)  
**Memory:** 54.6 MB (beats 20.83%)  
**Submitted:** 2026-09-06T14:41:36.621Z  

```java
class Solution {
    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[m + 1][n + 1];
        for(int j = 0; j <= n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i + 1][j + 1] = dp[i + 1][j];
                if (t.charAt(i) == s.charAt(j)) {
                    dp[i + 1][j + 1] += dp[i][j];
                }
            }
        }
        return dp[m][n];
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/distinct-subsequences/)