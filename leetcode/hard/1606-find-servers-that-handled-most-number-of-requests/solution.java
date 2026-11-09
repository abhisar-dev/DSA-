import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        // Track the count of requests handled by each server
        int[] count = new int[k];
        
        // TreeSet to keep track of available server IDs in sorted order
        TreeSet<Integer> availableServers = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            availableServers.add(i);
        }
        
        // PriorityQueue to track busy servers: stores int[] {end_time, server_id}
        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        int maxHandled = 0;
        
        for (int i = 0; i < arrival.length; i++) {
            int currTime = arrival[i];
            
            // Free up servers whose tasks have finished by currTime
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= currTime) {
                int[] freed = busyServers.poll();
                availableServers.add(freed[1]);
            }
            
            // If no servers are available, drop the request
            if (availableServers.isEmpty()) {
                continue;
            }
            
            // Preferred server is i % k
            Integer serverId = availableServers.ceiling(i % k);
            
            // If no server >= i % k is available, wrap around to the first available server
            if (serverId == null) {
                serverId = availableServers.first();
            }
            
            // Assign the request to this server
            availableServers.remove(serverId);
            busyServers.offer(new int[] { currTime + load[i], serverId });
            count[serverId]++;
            maxHandled = Math.max(maxHandled, count[serverId]);
        }
        
        // Collect all servers that handled the maximum number of requests
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (count[i] == maxHandled) {
                result.add(i);
            }
        }
        
        return result;
    }
}
