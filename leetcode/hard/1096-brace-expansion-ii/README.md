# Brace Expansion II

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

Under the grammar given below, strings can represent a set of lowercase words. Let `R(expr)` denote the set of words the expression represents.

The grammar can best be understood through simple examples:

- Single letters represent a singleton set containing that word. R("a") = {"a"} R("w") = {"w"}
- When we take a comma-delimited list of two or more expressions, we take the union of possibilities. R("{a,b,c}") = {"a","b","c"} R("{{a,b},{b,c}}") = {"a","b","c"} (notice the final set only contains each word at most once)
- When we concatenate two expressions, we take the set of possible concatenations between two words where the first word comes from the first expression and the second word comes from the second expression. R("{a,b}{c,d}") = {"ac","ad","bc","bd"} R("a{b,c}{d,e}f{g,h}") = {"abdfg", "abdfh", "abefg", "abefh", "acdfg", "acdfh", "acefg", "acefh"}

Formally, the three rules for our grammar:

- For every lowercase letter x, we have R(x) = {x}.
- For expressions e1, e2,..., ek with k >= 2, we have R({e1, e2,...}) = R(e1) ∪ R(e2) ∪...
- For expressions e1 and e2, we have R(e1 + e2) = {a + b for (a, b) in R(e1) × R(e2)}, where + denotes concatenation, and × denotes the cartesian product.

Given an expression representing a set of words under the given grammar, return  *the sorted list of words that the expression represents*.

 

 **Example 1:** 

```
Input: expression = "{a,b}{c,{d,e}}"
Output: ["ac","ad","ae","bc","bd","be"]

```

 **Example 2:** 

```
Input: expression = "{{a,z},a{b,c},{ab,z}}"
Output: ["a","ab","ac","z"]
Explanation: Each distinct word is written only once in the final answer.

```

 

 **Constraints:** 

- 1 <= expression.length <= 60
- expression[i] consists of '{', '}', ','or lowercase English letters.
- The given expression represents a set of words based on the grammar given in the description.

## Solution

**Language:** Java  
**Runtime:** 14 ms (beats 38.40%)  
**Memory:** 52.5 MB (beats 14.40%)  
**Submitted:** 2026-09-25T15:39:24.243Z  

```java
import java.util.*;

public class Solution {
    public List<String> braceExpansionII(String expression) {
        // Stack to store state before entering a brace block '{'
        // We push both the current union group and the current product group to the stack.
        Stack<Object> stack = new Stack<>();
        
        List<String> union = new ArrayList<>();
        List<String> prod = new ArrayList<>();
        prod.add(""); // Base case for multiplication anchor

        int i = 0;
        while (i < expression.length()) {
            char c = expression.charAt(i);

            if (Character.isLetter(c)) {
                // Case 1: Alphabet character. Concatenate it with all existing values in the current product group.
                List<String> nextProd = new ArrayList<>();
                for (String p : prod) {
                    nextProd.add(p + c);
                }
                prod = nextProd;
                i++;
            } else if (c == '{') {
                // Case 2: Open brace. Save current context onto stack and reset context for the block.
                stack.push(union);
                stack.push(prod);
                union = new ArrayList<>();
                prod = new ArrayList<>();
                prod.add("");
                i++;
            } else if (c == '}') {
                // Case 3: Close brace. Wrap up the inside block by joining its final union and product groups.
                List<String> innerResult = new ArrayList<>(union);
                innerResult.addAll(prod);

                // Pop previous states
                List<String> prevProd = (List<String>) stack.pop();
                List<String> prevUnion = (List<String>) stack.pop();

                // Compute Cartesian product between outer preceding group and current inner result
                List<String> nextProd = new ArrayList<>();
                for (String p : prevProd) {
                    for (String r : innerResult) {
                        nextProd.add(p + r);
                    }
                }
                
                prod = nextProd;
                union = prevUnion;
                i++;
            } else if (c == ',') {
                // Case 4: Comma delimiter. Add the finished product string list to the union group, then reset product.
                union.addAll(prod);
                prod = new ArrayList<>();
                prod.add("");
                i++;
            }
        }

        // Combine any final remaining products into the union group
        union.addAll(prod);

        // De-duplicate using a Set and sort alphabetically as per requirements
        Set<String> uniqueSorted = new TreeSet<>(union);
        return new ArrayList<>(uniqueSorted);
    }
}

```

---

[View on LeetCode](https://leetcode.com/problems/brace-expansion-ii/)