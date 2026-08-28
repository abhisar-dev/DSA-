# Lexicographically Smallest Permutation Greater Than Target

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given two strings `s` and `target`, both having length `n`, consisting of lowercase English letters.

Return the  **lexicographically smallest permutation**  of `s` that is  **strictly**  greater than `target`. If no permutation of `s` is lexicographically strictly greater than `target`, return an empty string.

A string `a` is  **lexicographically strictly greater** than a string `b` (of the same length) if in the first position where `a` and `b` differ, string `a` has a letter that appears later in the alphabet than the corresponding letter in `b`.

 

 **Example 1:** 

 **Input:**  s = "abc", target = "bba"

 **Output:**  "bca"

 **Explanation:** 

- The permutations of s (in lexicographical order) are "abc", "acb", "bac", "bca", "cab", and "cba".
- The lexicographically smallest permutation that is strictly greater than target is "bca".

 **Example 2:** 

 **Input:**  s = "leet", target = "code"

 **Output:**  "eelt"

 **Explanation:** 

- The permutations of s (in lexicographical order) are "eelt", "eetl", "elet", "elte", "etel", "etle", "leet", "lete", "ltee", "teel", "tele", and "tlee".
- The lexicographically smallest permutation that is strictly greater than target is "eelt".

 **Example 3:** 

 **Input:**  s = "baba", target = "bbaa"

 **Output:**  ""

 **Explanation:** 

- The permutations of s (in lexicographical order) are "aabb", "abab", "abba", "baab", "baba", and "bbaa".
- None of them is lexicographically strictly greater than target. Therefore, the answer is "".

 

 **Constraints:** 

- 1 <= s.length == target.length <= 300
- s and target consist of only lowercase English letters.

## Solution

**Language:** Java  
**Runtime:** 4 ms (beats 38.71%)  
**Memory:** 46.5 MB (beats 33.87%)  
**Submitted:** 2026-08-27T14:48:47.458Z  

```java
class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;

        StringBuilder result = new StringBuilder();
        if (dfs(0, freq, target, result, true)) {
            return result.toString();
        }
        return "";
    }

    private boolean dfs(int idx, int[] freq, String target, StringBuilder result, boolean tight) {
        if (idx == target.length()) return true;

        int start = 0;
        if (tight) start = target.charAt(idx) - 'a';

        for (int c = start; c < 26; c++) {
            if (freq[c] == 0) continue;

            result.append((char)(c + 'a'));
            freq[c]--;

            boolean nextTight = tight && (c == target.charAt(idx) - 'a');

            if (dfs(idx + 1, freq, target, result, nextTight)) {
                if (!nextTight || result.toString().compareTo(target.substring(0, idx + 1)) > 0) {
                    return true;
                }
            }

            result.deleteCharAt(result.length() - 1);
            freq[c]++;
        }
        return false;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/lexicographically-smallest-permutation-greater-than-target/)