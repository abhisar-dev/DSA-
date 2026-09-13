import java.util.*;

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> onesImg1 = new ArrayList<>();
        List<int[]> onesImg2 = new ArrayList<>();
        
        // Step 1: Collect coordinates of all 1s from both matrices
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) onesImg1.add(new int[]{i, j});
                if (img2[i][j] == 1) onesImg2.add(new int[]{i, j});
            }
        }
        
        // Step 2: Calculate shifts and count their frequencies
        Map<String, Integer> shiftCounts = new HashMap<>();
        int maxOverlap = 0;
        
        for (int[] p1 : onesImg1) {
            for (int[] p2 : onesImg2) {
                // Calculate the delta/shift between the two points
                int dx = p2[0] - p1[0];
                int dy = p2[1] - p1[1];
                
                // Represent the unique vector shift as a string key
                String key = dx + "," + dy;
                
                // Update the count for this specific translation vector
                shiftCounts.put(key, shiftCounts.getOrDefault(key, 0) + 1);
                
                // Keep track of the highest frequency encountered
                maxOverlap = Math.max(maxOverlap, shiftCounts.get(key));
            }
        }
        
        return maxOverlap;
    }
}
