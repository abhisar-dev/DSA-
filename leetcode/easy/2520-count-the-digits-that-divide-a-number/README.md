# Count the Digits That Divide a Number

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

Given an integer `num`, return  *the number of digits in `num` that divide* `num`.

An integer `val` divides `nums` if `nums % val == 0`.

 

 **Example 1:** 

```
Input: num = 7
Output: 1
Explanation: 7 divides itself, hence the answer is 1.

```

 **Example 2:** 

```
Input: num = 121
Output: 2
Explanation: 121 is divisible by 1, but not 2. Since 1 occurs twice as a digit, we return 2.

```

 **Example 3:** 

```
Input: num = 1248
Output: 4
Explanation: 1248 is divisible by all of its digits, hence the answer is 4.

```

 

 **Constraints:** 

- 1 <= num <= 109
- num does not contain 0 as one of its digits.

## Solution

**Language:** Java  
**Runtime:** 0 ms (beats 100.00%)  
**Memory:** 42.1 MB (beats 36.73%)  
**Submitted:** 2026-09-02T07:07:28.023Z  

```java
class Solution {
    public int countDigits(int n) {
        int count = 0 ;
        int x = n ;
      while(n!=0){
    int digit=n%10;
      if (x%digit==0) count++;
     n/=10;
      }
      return count ;
 }
}
```

---

[View on LeetCode](https://leetcode.com/problems/count-the-digits-that-divide-a-number/)