# Reverse Integer

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given a signed 32-bit integer `x`, return `x` *with its digits reversed*. If reversing `x` causes the value to go outside the signed 32-bit integer range `[-231, 231 - 1]`, then return `0`.

 **Assume the environment does not allow you to store 64-bit integers (signed or unsigned).** 

 

 **Example 1:** 

```
Input: x = 123
Output: 321

```

 **Example 2:** 

```
Input: x = -123
Output: -321

```

 **Example 3:** 

```
Input: x = 120
Output: 21

```

 

 **Constraints:** 

- -231 <= x <= 231 - 1

## Solution

**Language:** Java  
**Runtime:** 0 ms  
**Memory:** 42.2 MB  
**Submitted:** 2026-08-24T09:00:19.298Z  

```java
class Solution {
    public int reverse(int x) {

        int rev = 0;

        while (x != 0) {

           rev = rev*10+x%10;
           x/=10;
        }
if(rev<Long.MIN_VALUE || rev>Long.MAX_VALUE) return 0;
        return (int) rev;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/reverse-integer/)