import java.util.*;

class Solution {
    static class Server {
        int weight;
        int index;
        long freeTime;

        Server(int weight, int index, long freeTime) {
            this.weight = weight;
            this.index = index;
            this.freeTime = freeTime;
        }
    }

    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        // Free servers heap: sorted by weight, then by index
        PriorityQueue<Server> free = new PriorityQueue<>((a, b) -> {
            if (a.weight != b.weight) return Integer.compare(a.weight, b.weight);
            return Integer.compare(a.index, b.index);
        });

        // Used servers heap: sorted by available time, then weight, then index
        PriorityQueue<Server> used = new PriorityQueue<>((a, b) -> {
            if (a.freeTime != b.freeTime) return Long.compare(a.freeTime, b.freeTime);
            if (a.weight != b.weight) return Integer.compare(a.weight, b.weight);
            return Integer.compare(a.index, b.index);
        });

        // Initialize free servers
        for (int i = 0; i < n; i++) {
            free.offer(new Server(servers[i], i, 0));
        }

        long currentTime = 0;
        for (int j = 0; j < m; j++) {
            currentTime = Math.max(currentTime, j);

            // Free up servers whose task completion time <= current time
            while (!used.isEmpty() && used.peek().freeTime <= currentTime) {
                free.offer(used.poll());
            }

            // If no free servers are available, jump time to the earliest server release time
            if (free.isEmpty()) {
                currentTime = used.peek().freeTime;
                while (!used.isEmpty() && used.peek().freeTime <= currentTime) {
                    free.offer(used.poll());
                }
            }

            // Assign the task to the best available server
            Server server = free.poll();
            ans[j] = server.index;
            server.freeTime = currentTime + tasks[j];
            used.offer(server);
        }

        return ans;
    }
}
