# Palindromic Path Queries in a Tree

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given an undirected tree with `n` nodes labeled 0 to `n - 1`. This is represented by a 2D array `edges` of length `n - 1`, where `edges[i] = [ui, vi]` indicates an undirected edge between nodes `ui` and `vi`.

You are also given a string `s` of length `n` consisting of lowercase English letters, where `s[i]` represents the character assigned to node `i`.

You are also given a string array `queries`, where each `queries[i]` is either:

- "update ui c": Change the character at node ui to c. Formally, update s[ui] = c.
- "query ui vi": Determine whether the string formed by the characters on the unique path from ui to vi (inclusive) can be rearranged into a palindrome.

Return a boolean array `answer`, where `answer[j]` is `true` if the `jth` query of type `"query ui vi"​​​​​​​` can be rearranged into a  **palindrome**, and `false` otherwise.

 

 **Example 1:** 

 **Input:**  n = 3, edges = [[0,1],[1,2]], s = "aac", queries = ["query 0 2","update 1 b","query 0 2"]

 **Output:**  [true,false]

 **Explanation:** 

- "query 0 2": Path 0 → 1 → 2 gives "aac", which can be rearranged to form "aca", a palindrome. Thus, answer[0] = true.
- "update 1 b": Update node 1 to 'b', now s = "abc".
- "query 0 2": Path characters are "abc", which cannot be rearranged to form a palindrome. Thus, answer[1] = false.

Thus, `answer = [true, false]`.

 **Example 2:** 

 **Input:**  n = 4, edges = [[0,1],[0,2],[0,3]], s = "abca", queries = ["query 1 2","update 0 b","query 2 3","update 3 a","query 1 3"]

 **Output:**  [false,false,true]

 **Explanation:** 

- "query 1 2": Path 1 → 0 → 2 gives "bac", which cannot be rearranged to form a palindrome. Thus, answer[0] = false.
- "update 0 b": Update node 0 to 'b', now s = "bbca".
- "query 2 3": Path 2 → 0 → 3 gives "cba", which cannot be rearranged to form a palindrome. Thus, answer[1] = false.
- "update 3 a": Update node 3 to 'a', s = "bbca".
- "query 1 3": Path 1 → 0 → 3 gives "bba", which can be rearranged to form "bab", a palindrome. Thus, answer[2] = true.

Thus, `answer = [false, false, true]`.

 

 **Constraints:** 

- 1 <= n == s.length <= 5 * 104
- edges.length == n - 1
- edges[i] = [ui, vi]
- 0 <= ui, vi <= n - 1
- s consists of lowercase English letters.
- The input is generated such that edges represents a valid tree.
- 1 <= queries.length <= 5 * 104​​​​​​​queries[i] = "update ui c" or queries[i] = "query ui vi" 0 <= ui, vi <= n - 1 c is a lowercase English letter.

## Solution

**Language:** Java  
**Runtime:** 169 ms (beats 47.62%)  
**Memory:** 239.5 MB (beats 33.33%)  
**Submitted:** 2026-10-29T13:41:23.307Z  

```java
import java.util.*;

public class Solution {
    private int n;
    private List<Integer>[] adj;
    private int[] tin, tout;
    private int timer;
    private int[][] up;
    private int LOG;
    private int[] bit; // Fenwick Tree array
    private char[] currentChars;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        this.currentChars = s.toCharArray();
        this.adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        // Initialize Euler Tour and Binary Lifting parameters
        this.tin = new int[n];
        this.tout = new int[n];
        this.timer = 1;
        this.LOG = (int) (Math.log(n) / Math.log(2)) + 2;
        this.up = new int[n][LOG];
        this.bit = new int[n + 2];

        // First pass: Build LCA lift table and register entry/exit steps
        int[] initialPrefixMask = new int[n];
        dfs(0, 0, 0, initialPrefixMask);

        // Populate Fenwick Tree with the initial root-to-node prefix masks
        for (int i = 0; i < n; i++) {
            int mask = initialPrefixMask[i];
            updateBit(tin[i], mask);
            updateBit(tin[i] + 1, mask);
        }

        List<Boolean> result = new ArrayList<>();
        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                
                int oldMask = 1 << (currentChars[u] - 'a');
                int newMask = 1 << (c - 'a');
                int changeMask = oldMask ^ newMask;
                
                // An update modifies the prefix mask of the entire subtree under node 'u'
                updateRange(tin[u], tout[u], changeMask);
                currentChars[u] = c;
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int lcaNode = getLCA(u, v);
                
                int maskU = queryBit(tin[u]);
                int maskV = queryBit(tin[v]);
                int maskLcaElement = 1 << (currentChars[lcaNode] - 'a');
                
                int pathMask = maskU ^ maskV ^ maskLcaElement;
                // Check if at most 1 bit is set
                result.add((pathMask & (pathMask - 1)) == 0);
            }
        }
        return result;
    }

    private void dfs(int u, int p, int currentMask, int[] initialPrefixMask) {
        tin[u] = timer++;
        up[u][0] = p;
        for (int i = 1; i < LOG; i++) {
            up[u][i] = up[up[u][i - 1]][i - 1];
        }
        
        currentMask ^= (1 << (currentChars[u] - 'a'));
        initialPrefixMask[u] = currentMask;

        for (int v : adj[u]) {
            if (v != p) {
                dfs(v, u, currentMask, initialPrefixMask);
            }
        }
        tout[u] = timer - 1;
    }

    private int getLCA(int u, int v) {
        if (isAncestor(u, v)) return u;
        if (isAncestor(v, u)) return v;
        for (int i = LOG - 1; i >= 0; i--) {
            if (!isAncestor(up[u][i], v)) {
                u = up[u][i];
            }
        }
        return up[u][0];
    }

    private boolean isAncestor(int u, int v) {
        return tin[u] <= tin[v] && tout[u] >= tout[v];
    }

    // Fenwick tree helper operations
    private void updateBit(int idx, int val) {
        for (; idx <= n + 1; idx += idx & -idx) {
            bit[idx] ^= val;
        }
    }

    private void updateRange(int l, int r, int val) {
        updateBit(l, val);
        updateBit(r + 1, val);
    }

    private int queryBit(int idx) {
        int sum = 0;
        for (; idx > 0; idx -= idx & -idx) {
            sum ^= bit[idx];
        }
        return sum;
    }
}

```

---

[View on LeetCode](https://leetcode.com/problems/palindromic-path-queries-in-a-tree/)