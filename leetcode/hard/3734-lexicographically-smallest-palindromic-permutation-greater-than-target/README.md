# Lexicographically Smallest Palindromic Permutation Greater Than Target

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given two strings `s` and `target`, each of length `n`, consisting of lowercase English letters.

Return the  **lexicographically smallest string**  that is  **both**  a  **palindromic permutation**  of `s` and  **strictly**  greater than `target`. If no such permutation exists, return an empty string.

 

 **Example 1:** 

 **Input:**  s = "baba", target = "abba"

 **Output:**  "baab"

 **Explanation:** 

- The palindromic permutations of s (in lexicographical order) are "abba" and "baab".
- The lexicographically smallest permutation that is strictly greater than target is "baab".

 **Example 2:** 

 **Input:**  s = "baba", target = "bbaa"

 **Output:**  ""

 **Explanation:** 

- The palindromic permutations of s (in lexicographical order) are "abba" and "baab".
- None of them is lexicographically strictly greater than target. Therefore, the answer is "".

 **Example 3:** 

 **Input:**  s = "abc", target = "abb"

 **Output:**  ""

 **Explanation:** 

`s` has no palindromic permutations. Therefore, the answer is `""`.

 **Example 4:** 

 **Input:**  s = "aac", target = "abb"

 **Output:**  "aca"

 **Explanation:** 

- The only palindromic permutation of s is "aca".
- "aca" is strictly greater than target. Therefore, the answer is "aca".

 

 **Constraints:** 

- 1 <= n == s.length == target.length <= 300
- s and target consist of only lowercase English letters.

## Solution

**Language:** Java  
**Runtime:** 4 ms (beats 100.00%)  
**Memory:** 46.1 MB (beats 95.24%)  
**Submitted:** 2026-08-28T15:33:32.412Z  

```java
class Solution {

    // Builds the complete palindrome from its left half and optional middle character.
    private String buildPalindrome(String half, char middle) {
        StringBuilder result = new StringBuilder(half); // Start with the chosen left half.

        if (middle != 0) {
            result.append(middle); // Add the fixed middle character for odd-length strings.
        }

        // Mirror the left half in reverse order to complete the palindrome.
        for (int i = half.length() - 1; i >= 0; --i) {
            result.append(half.charAt(i));
        }

        return result.toString();
    }

    // Finds the lexicographically smallest permutation of the multiset
    // that is greater than or equal to targetHalf.
    private String smallestGreaterOrEqual(int[] originalCount, String targetHalf) {
        int[] count = originalCount.clone(); // Work on a copy because counts are modified.
        int k = targetHalf.length(); // Number of characters in the first half.
        int matched = 0; // Number of target characters matched exactly so far.

        // Try to match targetHalf from left to right for as long as possible.
        while (matched < k && count[targetHalf.charAt(matched) - 'a'] > 0) {
            count[targetHalf.charAt(matched) - 'a']--; // Use this exact character.
            matched++; // Move to the next position.
        }

        // If every position matched, targetHalf itself is a valid permutation.
        if (matched == k) {
            return targetHalf;
        }

        // Backtrack to find the rightmost position that can be increased.
        for (int pos = matched; pos >= 0; --pos) {
            // Restore a previously matched character when moving left.
            if (pos < matched) {
                count[targetHalf.charAt(pos) - 'a']++;
            }

            // Choose the smallest available character strictly greater than targetHalf[pos].
            for (int c = targetHalf.charAt(pos) - 'a' + 1; c < 26; ++c) {
                if (count[c] == 0) continue; // This character is not available.

                StringBuilder result = new StringBuilder(targetHalf.substring(0, pos));
                result.append((char) ('a' + c)); // Increase this position minimally.
                count[c]--; // Consume the chosen larger character.

                // Fill every remaining position in ascending order.
                for (int ch = 0; ch < 26; ++ch) {
                    while (count[ch]-- > 0) {
                        result.append((char) ('a' + ch));
                    }
                    count[ch] = Math.max(count[ch], 0); // Keep the count non-negative.
                }

                return result.toString();
            }
        }

        return ""; // No valid permutation can reach targetHalf.
    }

    // Returns true and changes half to its next lexicographical permutation.
    private boolean nextPermutation(char[] half) {
        int pivot = half.length - 2; // Search for the rightmost increasing position.

        // Find the rightmost position that can be increased.
        while (pivot >= 0 && half[pivot] >= half[pivot + 1]) {
            pivot--;
        }

        // No larger permutation exists.
        if (pivot < 0) {
            return false;
        }

        int swapPos = half.length - 1; // Search from the end for the next larger character.

        // The first character from the right that is larger gives the smallest increase.
        while (half[swapPos] <= half[pivot]) {
            swapPos--;
        }

        char temp = half[pivot];
        half[pivot] = half[swapPos];
        half[swapPos] = temp;

        // Reverse the suffix so it becomes the smallest possible suffix.
        int left = pivot + 1;
        int right = half.length - 1;

        while (left < right) {
            temp = half[left];
            half[left] = half[right];
            half[right] = temp;
            left++;
            right--;
        }

        return true;
    }

    public String lexPalindromicPermutation(String s, String target) {
        int[] frequency = new int[26]; // Count every character in s.

        for (char ch : s.toCharArray()) {
            frequency[ch - 'a']++;
        }

        char middle = 0; // Stores the unique odd-frequency character.
        int oddCount = 0; // Counts odd frequencies.

        for (int c = 0; c < 26; ++c) {
            if ((frequency[c] & 1) == 1) {
                oddCount++;
                middle = (char) ('a' + c);
            }
        }

        // A palindrome can have at most one odd-frequency character.
        if (oddCount > 1) {
            return "";
        }

        int[] halfCount = new int[26]; // Store characters used in the first half.

        for (int c = 0; c < 26; ++c) {
            halfCount[c] = frequency[c] / 2;
        }

        int k = s.length() / 2; // Length of the first half.
        String targetHalf = target.substring(0, k); // Prefix that controls the first comparison.

        // Find the smallest first-half permutation that is at least targetHalf.
        String halfString = smallestGreaterOrEqual(halfCount, targetHalf);

        if (halfString.isEmpty() && k > 0) {
            return "";
        }

        // Build the corresponding palindrome.
        String candidate = buildPalindrome(halfString, middle);

        // If it is strictly greater, it is already the smallest valid answer.
        if (candidate.compareTo(target) > 0) {
            return candidate;
        }

        char[] half = halfString.toCharArray(); // Convert to an array for in-place permutation.

        // Otherwise, move to the next larger first-half permutation.
        if (!nextPermutation(half)) {
            return "";
        }

        // Build the palindrome from the next possible first half.
        return buildPalindrome(new String(half), middle);
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/lexicographically-smallest-palindromic-permutation-greater-than-target/)