# Evaluate the Bracket Pairs of a String

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given a string `s` that contains some bracket pairs, with each pair containing a  **non-empty**  key.

- For example, in the string "(name)is(age)yearsold", there are two bracket pairs that contain the keys "name" and "age".

You know the values of a wide range of keys. This is represented by a 2D string array `knowledge` where each `knowledge[i] = [keyi, valuei]` indicates that key `keyi` has a value of `valuei`.

You are tasked to evaluate  **all**  of the bracket pairs. When you evaluate a bracket pair that contains some key `keyi`, you will:

- Replace keyi and the bracket pair with the key's corresponding valuei.
- If you do not know the value of the key, you will replace keyi and the bracket pair with a question mark "?" (without the quotation marks).

Each key will appear at most once in your `knowledge`. There will not be any nested brackets in `s`.

Return  *the resulting string after evaluating  **all**  of the bracket pairs.* 

 

 **Example 1:** 

```
Input: s = "(name)is(age)yearsold", knowledge = [["name","bob"],["age","two"]]
Output: "bobistwoyearsold"
Explanation:
The key "name" has a value of "bob", so replace "(name)" with "bob".
The key "age" has a value of "two", so replace "(age)" with "two".

```

 **Example 2:** 

```
Input: s = "hi(name)", knowledge = [["a","b"]]
Output: "hi?"
Explanation: As you do not know the value of the key "name", replace "(name)" with "?".

```

 **Example 3:** 

```
Input: s = "(a)(a)(a)aaa", knowledge = [["a","yes"]]
Output: "yesyesyesaaa"
Explanation: The same key can appear multiple times.
The key "a" has a value of "yes", so replace all occurrences of "(a)" with "yes".
Notice that the "a"s not in a bracket pair are not evaluated.

```

 

 **Constraints:** 

- 1 <= s.length <= 105
- 0 <= knowledge.length <= 105
- knowledge[i].length == 2
- 1 <= keyi.length, valuei.length <= 10
- s consists of lowercase English letters and round brackets '(' and ')'.
- Every open bracket '(' in s will have a corresponding close bracket ')'.
- The key in each bracket pair of s will be non-empty.
- There will not be any nested bracket pairs in s.
- keyi and valuei consist of lowercase English letters.
- Each keyi in knowledge is unique.

## Solution

**Language:** Java  
**Runtime:** 34 ms (beats 79.64%)  
**Memory:** 91.2 MB (beats 58.82%)  
**Submitted:** 2026-09-26T15:51:45.379Z  

```java
import java.util.*;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        // Step 1: Store knowledge in a map for O(1) retrieval
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }
        
        StringBuilder result = new StringBuilder();
        StringBuilder key = new StringBuilder();
        boolean inBracket = false;
        
        // Step 2: Parse the string in a single pass
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                inBracket = true;
            } else if (c == ')') {
                inBracket = false;
                // Look up the key and append its value or '?'
                String keyStr = key.toString();
                result.append(map.getOrDefault(keyStr, "?"));
                key.setLength(0); // Clear the key buffer
            } else {
                if (inBracket) {
                    key.append(c);
                } else {
                    result.append(c);
                }
            }
        }
        
        return result.toString();
    }
}

```

---

[View on LeetCode](https://leetcode.com/problems/evaluate-the-bracket-pairs-of-a-string/)