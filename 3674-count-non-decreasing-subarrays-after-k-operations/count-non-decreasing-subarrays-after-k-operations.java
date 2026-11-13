import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        long ans = 0;
        long cost = 0;
        // Monotonic deque stores arrays of size 2: [val, count]
        Deque<long[]> dq = new ArrayDeque<>();
        int n = nums.length;
        int j = n - 1;

        for (int i = n - 1; i >= 0; --i) {
            long num = nums[i];
            long count = 1;

            // Maintain monotonic decreasing order in the deque
            while (!dq.isEmpty() && dq.peekLast()[0] < num) {
                long[] prev = dq.pollLast();
                long nextNum = prev[0];
                long nextCount = prev[1];
                count += nextCount;
                cost += (num - nextNum) * nextCount;
            }
            dq.addLast(new long[]{num, count});

            // Shrink the window from the right if the cost exceeds k
            while (cost > k) {
                long[] front = dq.peekFirst();
                long leftNum = front[0];
                long leftCount = front[1];

                cost -= (leftNum - nums[j]);
                if (leftCount == 1) {
                    dq.pollFirst();
                } else {
                    front[1]--;
                }
                j--;
            }

            // All subarrays starting at index i and ending anywhere from i to j are valid
            ans += (j - i + 1);
        }

        return ans;
    }
}
