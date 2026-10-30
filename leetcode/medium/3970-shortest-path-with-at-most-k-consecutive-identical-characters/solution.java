import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // 1. Build the adjacency list for the directed graph
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            adj.get(u).add(new int[]{v, w});
        }

        // 2. Initialize the distance matrix: dist[node][streak]
        // Node numbers range from 0 to n-1. 
        // Streak lengths range from 1 to k.
        int[][] dist = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        // 3. Min-Heap for Dijkstra: stores states as {total_weight, node, current_streak}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        // Base case: Starting at node 0 with an initial streak of 1
        dist[0][1] = 0;
        pq.offer(new int[]{0, 0, 1});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currWeight = curr[0];
            int u = curr[1];
            int streak = curr[2];

            // If the destination is reached, return the cost immediately
            if (u == n - 1) {
                return currWeight;
            }

            // Skip outdated states
            if (currWeight > dist[u][streak]) {
                continue;
            }

            // Traverse adjacent neighbors
            for (int[] neighbor : adj.get(u)) {
                int v = neighbor[0];
                int weight = neighbor[1];
                
                int nextStreak;
                if (labels.charAt(v) == labels.charAt(u)) {
                    nextStreak = streak + 1;
                } else {
                    nextStreak = 1;
                }

                // Only proceed if the new streak does not exceed k
                if (nextStreak <= k) {
                    if (currWeight + weight < dist[v][nextStreak]) {
                        dist[v][nextStreak] = currWeight + weight;
                        pq.offer(new int[]{dist[v][nextStreak], v, nextStreak});
                    }
                }
            }
        }

        return -1;
    }
}
