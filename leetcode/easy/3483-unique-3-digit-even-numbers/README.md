# Unique 3-Digit Even Numbers

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

You are given an array of digits called `digits`. Your task is to determine the number of  **distinct**  three-digit even numbers that can be formed using these digits.

 **Note** : Each  *copy*  of a digit can only be used  **once per number**, and there may  **not**  be leading zeros.

 

 **Example 1:** 

 **Input:**  digits = [1,2,3,4]

 **Output:**  12

 **Explanation:**  The 12 distinct 3-digit even numbers that can be formed are 124, 132, 134, 142, 214, 234, 312, 314, 324, 342, 412, and 432. Note that 222 cannot be formed because there is only 1 copy of the digit 2.

 **Example 2:** 

 **Input:**  digits = [0,2,2]

 **Output:**  2

 **Explanation:**  The only 3-digit even numbers that can be formed are 202 and 220. Note that the digit 2 can be used twice because it appears twice in the array.

 **Example 3:** 

 **Input:**  digits = [6,6,6]

 **Output:**  1

 **Explanation:**  Only 666 can be formed.

 **Example 4:** 

 **Input:**  digits = [1,3,5]

 **Output:**  0

 **Explanation:**  No even 3-digit numbers can be formed.

 

 **Constraints:** 

- 3 <= digits.length <= 10
- 0 <= digits[i] <= 9

## Solution

**Language:** C++  
**Runtime:** 77 ms (beats 10.71%)  
**Memory:** 40.8 MB (beats 12.37%)  
**Submitted:** 2026-09-11T14:52:44.015Z  

```cpp
class Solution {
public:
   set<int>st;
   int solve(string no,vector<bool>used,vector<int>&digits){
      if(no.size()==3){
           if(no[0]=='0')return 0;
           int ns=stoi(no);
           if(ns%2==0 && st.find(ns)==st.end()){
            st.insert(ns);
            return 1;
           }
           return 0;
      }
      int ans=0;
      for(int i=0;i<digits.size();i++){
        if(used[i]==false){
            used[i]=true;
            no+=to_string(digits[i]);
            ans+=solve(no,used,digits);
            no.pop_back();
            used[i]=false;
        }
      }
      return ans;
   }
    int totalNumbers(vector<int>& digits) {
        int n=digits.size();
        vector<bool>used(n,false);
       return solve("",used,digits); 
    }
};
```

---

[View on LeetCode](https://leetcode.com/problems/unique-3-digit-even-numbers/)