class Solution {
    private int m, n;
    private char[][] grid;
    private boolean[][][] memo;

    public boolean hasValidPath(char[][] grid) {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;
        
        // Quick pruning: odd total length, starts with ')', or ends with '('
        if ((m + n - 1) % 2 != 0 || grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }
        
        // memo[i][j][k] stores whether state (i, j, k) has been visited/solved
        // Max possible balance k is (m + n)
        this.memo = new boolean[m][n][m + n];
        boolean[][][] visited = new boolean[m][n][m + n];
        
        return dfs(0, 0, 0, visited);
    }

    private boolean dfs(int i, int j, int k, boolean[][][] visited) {
        k += (grid[i][j] == '(') ? 1 : -1;
        
        // If balance goes negative or exceeds remaining steps, prune path
        if (k < 0 || k > (m - 1 - i) + (n - 1 - j)) {
            return false;
        }
        
        // Reached bottom-right corner
        if (i == m - 1 && j == n - 1) {
            return k == 0;
        }
        
        if (visited[i][j][k]) {
            return memo[i][j][k];
        }
        
        visited[i][j][k] = true;
        
        // Move Down (i + 1, j) or Move Right (i, j + 1)
        boolean res = false;
        if (i + 1 < m && dfs(i + 1, j, k, visited)) {
            res = true;
        } else if (j + 1 < n && dfs(i, j + 1, k, visited)) {
            res = true;
        }
        
        return memo[i][j][k] = res;
    }
}

// ### Complexity Analysis

// * **Time Complexity:** $\mathcal{O}(m \cdot n \cdot (m + n))$ where $m$ is the number of rows and $n$ is the number of columns. Each state `(i, j, k)` is computed at most once.
// * **Space Complexity:** $\mathcal{O}(m \cdot n \cdot (m + n))$ to store the recursive call stack and the memoization/visited table.
