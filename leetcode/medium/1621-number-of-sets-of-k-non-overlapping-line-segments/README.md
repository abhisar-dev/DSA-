# Number of Sets of K Non-Overlapping Line Segments

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given `n` points on a 1-D plane, where the `ith` point (from `0` to `n-1`) is at `x = i`, find the number of ways we can draw  **exactly**  `k`  **non-overlapping**  line segments such that each segment covers two or more points. The endpoints of each segment must have  **integral coordinates**. The `k` line segments  **do not**  have to cover all `n` points, and they are  **allowed**  to share endpoints.

Return  *the number of ways we can draw* `k` *non-overlapping line segments **.*  Since this number can be huge, return it** modulo** `109 + 7`.

 

 **Example 1:** 

```
Input: n = 4, k = 2
Output: 5
Explanation: The two line segments are shown in red and blue.
The image above shows the 5 different ways {(0,2),(2,3)}, {(0,1),(1,3)}, {(0,1),(2,3)}, {(1,2),(2,3)}, {(0,1),(1,2)}.

```

 **Example 2:** 

```
Input: n = 3, k = 1
Output: 3
Explanation: The 3 ways are {(0,1)}, {(0,2)}, {(1,2)}.

```

 **Example 3:** 

```
Input: n = 30, k = 7
Output: 796297179
Explanation: The total number of possible ways to draw 7 line segments is 3796297200. Taking this number modulo 109 + 7 gives us 796297179.

```

 

 **Constraints:** 

- 2 <= n <= 1000
- 1 <= k <= n-1

## Solution

**Language:** Java  
**Runtime:** 199 ms (beats 22.58%)  
**Memory:** 167.3 MB (beats 25.81%)  
**Submitted:** 2026-09-16T14:40:20.909Z  

```java
class Solution {
  public int numberOfSets(int n, int k) {
    Integer[][][] mem = new Integer[n][k + 1][2];
    return numberOfSets(0, k, /*drawing=*/false, n, mem);
  }

  private static final int MOD = 1_000_000_007;

  private int numberOfSets(int i, int k, boolean drawing, int n, Integer[][][] mem) {
    if (k == 0) // Find a way to draw k segments.
      return 1;
    if (i == n) // Reach the end.
      return 0;
    if (mem[i][k][drawing ? 1 : 0] != null)
      return mem[i][k][drawing ? 1 : 0];
    if (drawing)
      // 1. Keep drawing at i and move to i + 1.
      // 2. Stop at i so decrease k. We can start from i for the next segment.
      return mem[i][k][drawing ? 1 : 0] = (numberOfSets(i + 1, k, true, n, mem) + //
                                           numberOfSets(i, k - 1, false, n, mem)) %
                                          MOD;
    // 1. Skip i and move to i + 1.
    // 2. Start at i and move to i + 1.
    return mem[i][k][drawing ? 1 : 0] = (numberOfSets(i + 1, k, false, n, mem) + //
                                         numberOfSets(i + 1, k, true, n, mem)) %
                                        MOD;
  }
}



```

---

[View on LeetCode](https://leetcode.com/problems/number-of-sets-of-k-non-overlapping-line-segments/)