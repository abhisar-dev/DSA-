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
