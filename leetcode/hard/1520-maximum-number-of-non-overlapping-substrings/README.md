# Maximum Number of Non-Overlapping Substrings

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

Given a string `s` of lowercase letters, you need to find the maximum number of  **non-empty**  substrings of `s` that meet the following conditions:

- The substrings do not overlap, that is for any two substrings s[i..j] and s[x..y], either j < x or i > y is true.
- A substring that contains a certain character c must also contain all occurrences of c.

Find  *the maximum number of substrings that meet the above conditions*. If there are multiple solutions with the same number of substrings,  *return the one with minimum total length.* It can be shown that there exists a unique solution of minimum total length.

Notice that you can return the substrings in  **any**  order.

 

 **Example 1:** 

```
Input: s = "adefaddaccc"
Output: ["e","f","ccc"]
Explanation: The following are all the possible substrings that meet the conditions:
[
  "adefaddaccc"
  "adefadda",
  "ef",
  "e",
  "f",
  "ccc",
]
If we choose the first string, we cannot choose anything else and we'd get only 1. If we choose "adefadda", we are left with "ccc" which is the only one that doesn't overlap, thus obtaining 2 substrings. Notice also, that it's not optimal to choose "ef" since it can be split into two. Therefore, the optimal way is to choose ["e","f","ccc"] which gives us 3 substrings. No other solution of the same number of substrings exist.

```

 **Example 2:** 

```
Input: s = "abbaccd"
Output: ["d","bb","cc"]
Explanation: Notice that while the set of substrings ["d","abba","cc"] also has length 3, it's considered incorrect since it has larger total length.

```

 

 **Constraints:** 

- 1 <= s.length <= 105
- s contains only lowercase English letters.

## Solution

**Language:** Java  
**Runtime:** 10 ms (beats 54.73%)  
**Memory:** 47.9 MB (beats 78.26%)  
**Submitted:** 2026-09-18T15:45:39.212Z  

```java
class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] left = new int[26];
        int[] right = new int[26];
        
        java.util.Arrays.fill(left, n);
        java.util.Arrays.fill(right, -1);
        
        // Step 1: Record the first and last occurrence for each character
        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            left[idx] = Math.min(left[idx], i);
            right[idx] = i;
        }
        
        List<String> res = new ArrayList<>();
        int lastRight = -1;
        
        // Step 2: Find valid and optimal substrings greedily
        for (int i = 0; i < n; i++) {
            if (i == left[s.charAt(i) - 'a']) {
                int r = getValidRight(s, i, left, right);
                if (r != -1) {
                    if (i > lastRight) {
                        res.add(s.substring(i, r + 1));
                    } else {
                        res.set(res.size() - 1, s.substring(i, r + 1));
                    }
                    lastRight = r;
                }
            }
        }
        
        return res;
    }
    
    private int getValidRight(String s, int start, int[] left, int[] right) {
        int r = right[s.charAt(start) - 'a'];
        for (int j = start; j <= r; j++) {
            int idx = s.charAt(j) - 'a';
            // If any character in the range appears before our start, 
            // this substring is invalid.
            if (left[idx] < start) {
                return -1;
            }
            // Expand the right boundary if needed.
            r = Math.max(r, right[idx]);
        }
        return r;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/maximum-number-of-non-overlapping-substrings/)