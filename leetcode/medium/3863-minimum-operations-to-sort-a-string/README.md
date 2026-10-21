# Minimum Operations to Sort a String

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given a string `s` consisting of lowercase English letters.

In one operation, you can select any  **substring**  of `s` that is  **not**  the entire string and  **sort**  it in  **non-descending alphabetical**  order.

Return the  **minimum**  number of operations required to make `s` sorted in  **non-descending**  order. If it is not possible, return -1.

 

 **Example 1:** 

 **Input:**  s = "dog"

 **Output:**  1

 **Explanation:** ​​​​​​​

- Sort substring "og" to "go".
- Now, s = "dgo", which is sorted in ascending order. Thus, the answer is 1.

 **Example 2:** 

 **Input:**  s = "card"

 **Output:**  2

 **Explanation:** 

- Sort substring "car" to "acr", so s = "acrd".
- Sort substring "rd" to "dr", making s = "acdr", which is sorted in ascending order. Thus, the answer is 2.

 **Example 3:** 

 **Input:**  s = "gf"

 **Output:**  -1

 **Explanation:** 

- It is impossible to sort s under the given constraints. Thus, the answer is -1.

 

 **Constraints:** 

- 1 <= s.length <= 105
- s consists of only lowercase English letters.

## Solution

**Language:** Java  
**Runtime:** 16 ms (beats 86.76%)  
**Memory:** 47.5 MB (beats 92.65%)  
**Submitted:** 2026-10-21T15:31:22.759Z  

```java
class Solution {
    public int minOperations(String s) {
        boolean isSorted = true;
        char[] cs = s.toCharArray();
        int n = cs.length;
        char mn = cs[0], mx = cs[0];
        
        for (int i = 1; i < n; ++i) {
            mn = (char) Math.min(mn, cs[i]);
            mx = (char) Math.max(mx, cs[i]);
            if (cs[i] < cs[i - 1]) {
                isSorted = false;
            }
        }
        
        // Case 0: Already sorted
        if (isSorted) {
            return 0;
        }
        
        // Case -1: Length 2 and not sorted
        if (n == 2) {
            return -1;
        }
        
        // Case 1: First char is min or last char is max
        if (cs[0] == mn || cs[n - 1] == mx) {
            return 1;
        }
        
        // Case 2: Min or max is anywhere in the middle portion [1...n-2]
        for (int i = 1; i < n - 1; ++i) {
            if (cs[i] == mn || cs[i] == mx) {
                return 2;
            }
        }
        
        // Case 3: Max is at the very beginning and min is at the very end
        return 3;
    }
}


// ### Approach & Logic

// * **0 Operations:** If the string is already in non-descending order, return `0`.
// * **-1 Operations:** If `n == 2` and the characters are out of order, you cannot select a proper substring (since you cannot pick the whole string), making it impossible to sort (`-1`).
// * **1 Operation:** If the smallest character is already at index `0` (`cs[0] == mn`) or the largest character is at the last index (`cs[n-1] == mx`), sorting the remaining proper substring fixes it in `1` move.
// * **2 Operations:** If `mn` or `mx` resides somewhere in the interior `[1...n-2]`, you can position them properly within `2` operations.
// * **3 Operations:** Worst-case scenario occurs when the maximum character is trapped at index `0` and the minimum character is trapped at the last index `n - 1`, requiring `3` targeted sorting operations.

```

---

[View on LeetCode](https://leetcode.com/problems/minimum-operations-to-sort-a-string/)