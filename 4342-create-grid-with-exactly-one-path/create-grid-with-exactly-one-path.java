import java.util.Arrays;

class Solution {
    public String[] createGrid(int m, int n) {
        char[][] grid = new char[m][n];
        
        // 1. Fill the entire grid with obstacles ('#')
        for (int i = 0; i < m; i++) {
            Arrays.fill(grid[i], '#');
        }
        
        // 2. Open the first row completely (moving right)
        Arrays.fill(grid[0], '.');
        
        // 3. Open the last column completely (moving down)
        for (int i = 0; i < m; i++) {
            grid[i][n - 1] = '.';
        }
        
        // 4. Convert the character array grid into the final array of Strings
        String[] ans = new String[m];
        for (int i = 0; i < m; i++) {
            ans[i] = new String(grid[i]);
        }
        
        return ans;
    }
}
