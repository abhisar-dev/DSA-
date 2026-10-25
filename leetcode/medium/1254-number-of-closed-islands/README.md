# Number of Closed Islands

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given a 2D `grid` consists of `0s` (land) and `1s` (water).  An  *island*  is a maximal 4-directionally connected group of `0s` and a  *closed island*  is an island  **totally**  (all left, top, right, bottom) surrounded by `1s.`

Return the number of  *closed islands*.

 

 **Example 1:** 

```
Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
Output: 2
Explanation: 
Islands in gray are closed because they are completely surrounded by water (group of 1s).
```

 **Example 2:** 

```
Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
Output: 1

```

 **Example 3:** 

```
Input: grid = [[1,1,1,1,1,1,1],
               [1,0,0,0,0,0,1],
               [1,0,1,1,1,0,1],
               [1,0,1,0,1,0,1],
               [1,0,1,1,1,0,1],
               [1,0,0,0,0,0,1],
               [1,1,1,1,1,1,1]]
Output: 2

```

 

 **Constraints:** 

- 1 <= grid.length, grid[0].length <= 100
- 0 <= grid[i][j] <=1

## Solution

**Language:** Java  
**Runtime:** 3 ms (beats 38.41%)  
**Memory:** 46.4 MB (beats 69.41%)  
**Submitted:** 2026-10-25T15:59:28.726Z  

```java
class Solution {
    public int closedIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Step 1: Remove all land connected to the boundary edges
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // If the cell is on the border and is land (0)
                if (i == 0 || j == 0 || i == rows - 1 || j == cols - 1) {
                    if (grid[i][j] == 0) {
                        dfs(grid, i, j);
                    }
                }
            }
        }
        
        // Step 2: Count the remaining closed islands
        int closedIslandsCount = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) {
                    closedIslandsCount++;
                    dfs(grid, i, j); // Sink the counted island
                }
            }
        }
        
        return closedIslandsCount;
    }
    
    private void dfs(int[][] grid, int r, int c) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Base case: check bounds and if the cell is water (1)
        if (r < 0 || c < 0 || r >= rows || c >= cols || grid[r][c] == 1) {
            return;
        }
        
        // Mark the current land cell as water (visited)
        grid[r][c] = 1;
        
        // Visit all 4 adjacent directions
        dfs(grid, r + 1, c); // Down
        dfs(grid, r - 1, c); // Up
        dfs(grid, r, c + 1); // Right
        dfs(grid, r, c - 1); // Left
    }
}

```

---

[View on LeetCode](https://leetcode.com/problems/number-of-closed-islands/)