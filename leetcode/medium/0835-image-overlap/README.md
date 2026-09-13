# Image Overlap

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given two images, `img1` and `img2`, represented as binary, square matrices of size `n x n`. A binary matrix has only `0`s and `1`s as values.

We  **translate**  one image however we choose by sliding all the `1` bits left, right, up, and/or down any number of units. We then place it on top of the other image. We can then calculate the  **overlap**  by counting the number of positions that have a `1` in  **both**  images.

Note also that a translation does  **not**  include any kind of rotation. Any `1` bits that are translated outside of the matrix borders are erased.

Return  *the largest possible overlap*.

 

 **Example 1:** 

```
Input: img1 = [[1,1,0],[0,1,0],[0,1,0]], img2 = [[0,0,0],[0,1,1],[0,0,1]]
Output: 3
Explanation: We translate img1 to right by 1 unit and down by 1 unit.

The number of positions that have a 1 in both images is 3 (shown in red).

```

 **Example 2:** 

```
Input: img1 = [[1]], img2 = [[1]]
Output: 1

```

 **Example 3:** 

```
Input: img1 = [[0]], img2 = [[0]]
Output: 0

```

 

 **Constraints:** 

- n == img1.length == img1[i].length
- n == img2.length == img2[i].length
- 1 <= n <= 30
- img1[i][j] is either 0 or 1.
- img2[i][j] is either 0 or 1.

## Solution

**Language:** Java  
**Runtime:** 225 ms (beats 8.71%)  
**Memory:** 47.6 MB (beats 18.18%)  
**Submitted:** 2026-09-13T14:42:25.046Z  

```java
import java.util.*;

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> onesImg1 = new ArrayList<>();
        List<int[]> onesImg2 = new ArrayList<>();
        
        // Step 1: Collect coordinates of all 1s from both matrices
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) onesImg1.add(new int[]{i, j});
                if (img2[i][j] == 1) onesImg2.add(new int[]{i, j});
            }
        }
        
        // Step 2: Calculate shifts and count their frequencies
        Map<String, Integer> shiftCounts = new HashMap<>();
        int maxOverlap = 0;
        
        for (int[] p1 : onesImg1) {
            for (int[] p2 : onesImg2) {
                // Calculate the delta/shift between the two points
                int dx = p2[0] - p1[0];
                int dy = p2[1] - p1[1];
                
                // Represent the unique vector shift as a string key
                String key = dx + "," + dy;
                
                // Update the count for this specific translation vector
                shiftCounts.put(key, shiftCounts.getOrDefault(key, 0) + 1);
                
                // Keep track of the highest frequency encountered
                maxOverlap = Math.max(maxOverlap, shiftCounts.get(key));
            }
        }
        
        return maxOverlap;
    }
}

```

---

[View on LeetCode](https://leetcode.com/problems/image-overlap/)