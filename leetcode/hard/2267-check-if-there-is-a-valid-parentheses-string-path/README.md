# Check if There Is a Valid Parentheses String Path

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

A parentheses string is a  **non-empty**  string consisting only of `'('` and `')'`. It is  **valid**  if  **any**  of the following conditions is  **true** :

- It is ().
- It can be written as AB (A concatenated with B), where A and B are valid parentheses strings.
- It can be written as (A), where A is a valid parentheses string.

You are given an `m x n` matrix of parentheses `grid`. A  **valid parentheses string path**  in the grid is a path satisfying  **all**  of the following conditions:

- The path starts from the upper left cell (0, 0).
- The path ends at the bottom-right cell (m - 1, n - 1).
- The path only ever moves down or right.
- The resulting parentheses string formed by the path is valid.

Return `true`  *if there exists a  **valid parentheses string path**  in the grid.*  Otherwise, return `false`.

 

 **Example 1:** 

```
Input: grid = [["(","(","("],[")","(",")"],["(","(",")"],["(","(",")"]]
Output: true
Explanation: The above diagram shows two possible paths that form valid parentheses strings.
The first path shown results in the valid parentheses string "()(())".
The second path shown results in the valid parentheses string "((()))".
Note that there may be other valid parentheses string paths.

```

 **Example 2:** 

```
Input: grid = [[")",")"],["(","("]]
Output: false
Explanation: The two possible paths form the parentheses strings "))(" and ")((". Since neither of them are valid parentheses strings, we return false.

```

 

 **Constraints:** 

- m == grid.length
- n == grid[i].length
- 1 <= m, n <= 100
- grid[i][j] is either '(' or ')'.

## Solution

**Language:** Java  
**Runtime:** 9 ms (beats 75.24%)  
**Memory:** 57.4 MB (beats 69.52%)  
**Submitted:** 2026-09-29T14:47:41.036Z  

```java
class Solution {
    private int m, n;
    private char[][] grid;
    private boolean[][][] memo;

    public boolean hasValidPath(char[][] grid) {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;
        
        // Quick pruning: odd total length, starts with ')', or ends with '('
        if ((m + n - 1) % 2 != 0 || grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }
        
        // memo[i][j][k] stores whether state (i, j, k) has been visited/solved
        // Max possible balance k is (m + n)
        this.memo = new boolean[m][n][m + n];
        boolean[][][] visited = new boolean[m][n][m + n];
        
        return dfs(0, 0, 0, visited);
    }

    private boolean dfs(int i, int j, int k, boolean[][][] visited) {
        k += (grid[i][j] == '(') ? 1 : -1;
        
        // If balance goes negative or exceeds remaining steps, prune path
        if (k < 0 || k > (m - 1 - i) + (n - 1 - j)) {
            return false;
        }
        
        // Reached bottom-right corner
        if (i == m - 1 && j == n - 1) {
            return k == 0;
        }
        
        if (visited[i][j][k]) {
            return memo[i][j][k];
        }
        
        visited[i][j][k] = true;
        
        // Move Down (i + 1, j) or Move Right (i, j + 1)
        boolean res = false;
        if (i + 1 < m && dfs(i + 1, j, k, visited)) {
            res = true;
        } else if (j + 1 < n && dfs(i, j + 1, k, visited)) {
            res = true;
        }
        
        return memo[i][j][k] = res;
    }
}

// ### Complexity Analysis

// * **Time Complexity:** $\mathcal{O}(m \cdot n \cdot (m + n))$ where $m$ is the number of rows and $n$ is the number of columns. Each state `(i, j, k)` is computed at most once.
// * **Space Complexity:** $\mathcal{O}(m \cdot n \cdot (m + n))$ to store the recursive call stack and the memoization/visited table.

```

---

[View on LeetCode](https://leetcode.com/problems/check-if-there-is-a-valid-parentheses-string-path/)