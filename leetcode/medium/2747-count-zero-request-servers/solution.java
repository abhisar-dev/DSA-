import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        // Sort logs by time
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        
        int m = queries.length;
        Integer[] qIdx = new Integer[m];
        for (int i = 0; i < m; i++) {
            qIdx[i] = i;
        }
        // Sort query indices based on query time values
        Arrays.sort(qIdx, (a, b) -> Integer.compare(queries[a], queries[b]));
        
        int[] ans = new int[m];
        Map<Integer, Integer> countMap = new HashMap<>();
        
        int j = 0, k = 0;
        // Process queries in increasing order of their time
        for (int idx : qIdx) {
            int r = queries[idx];
            int l = r - x;
            
            // Expand right boundary: add logs with time <= r
            while (k < logs.length && logs[k][1] <= r) {
                countMap.put(logs[k][0], countMap.getOrDefault(logs[k][0], 0) + 1);
                k++;
            }
            
            // Shrink left boundary: remove logs with time < l
            while (j < logs.length && logs[j][1] < l) {
                int serverId = logs[j][0];
                countMap.put(serverId, countMap.get(serverId) - 1);
                if (countMap.get(serverId) == 0) {
                    countMap.remove(serverId);
                }
                j++;
            }
            
            // Active servers are the number of keys in map
            ans[idx] = n - countMap.size();
        }
        
        return ans;
    }
}
