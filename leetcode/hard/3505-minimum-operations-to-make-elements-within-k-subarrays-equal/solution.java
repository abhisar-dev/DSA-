import java.util.*;

class Solution {
    // Helper class to manage a balanced half of the sliding window
    private static class WindowPart {
        private final TreeMap<Integer, Integer> map = new TreeMap<>();
        private int count = 0;
        private long sum = 0;

        public void add(int val) {
            map.put(val, map.getOrDefault(val, 0) + 1);
            count++;
            sum += val;
        }

        public void remove(int val) {
            if (!map.containsKey(val)) return;
            int currentCount = map.get(val);
            if (currentCount == 1) {
                map.remove(val);
            } else {
                map.put(val, currentCount - 1);
            }
            count--;
            sum -= val;
        }

        public int minKey() {
            return map.firstKey();
        }

        public int maxKey() {
            return map.lastKey();
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        
        // Step 1: Compute minimum operations for all possible size-x subarrays
        // minOps[i] stores the L1 cost to make all elements equal in nums[i ... i + x - 1]
        long[] minOps = new long[n - x + 1];
        
        WindowPart left = new WindowPart();  // Stores the smaller half (includes median)
        WindowPart right = new WindowPart(); // Stores the larger half

        for (int i = 0; i < n; i++) {
            // Add new element to the sliding window
            if (left.count == 0 || nums[i] <= left.maxKey()) {
                left.add(nums[i]);
            } else {
                right.add(nums[i]);
            }

            // Remove outbound element that falls out of the sliding window
            if (i >= x) {
                int outNum = nums[i - x];
                if (left.map.containsKey(outNum)) {
                    left.remove(outNum);
                } else {
                    right.remove(outNum);
                }
            }

            // Balance the parts so that left.count >= right.count and difference <= 1
            if (left.count < right.count) {
                int val = right.minKey();
                right.remove(val);
                left.add(val);
            } else if (left.count - right.count > 1) {
                int val = left.maxKey();
                left.remove(val);
                right.add(val);
            }

            // Once the window reaches size x, calculate operations using the median
            if (i >= x - 1) {
                long median = left.maxKey();
                long cost = (median * left.count - left.sum) + (right.sum - median * right.count);
                minOps[i - x + 1] = cost;
            }
        }

        // Step 2: DP to choose k non-overlapping intervals of size x with minimum cost
        // dp[j] stores the minimum cost to pick j subarrays
        long INF = Long.MAX_VALUE / 2;
        long[] dp = new long[k + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        // Space optimization: We use a window of previous DP states
        // queue template to track valid dp transitions efficiently
        long[][] memo = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(memo[i], -1);
        }

        return solve(0, k, x, minOps, n, memo);
    }

    private long solve(int i, int k, int x, long[] minOps, int n, long[][] memo) {
        if (k == 0) return 0;
        if (i + x * k > n) return Long.MAX_VALUE / 2; // Impossible to fit remaining windows
        if (memo[i][k] != -1) return memo[i][k];

        // Option 1: Skip the current element
        long skip = solve(i + 1, k, x, minOps, n, memo);
        
        // Option 2: Choose the size-x subarray starting at index i
        long pick = minOps[i] + solve(i + x, k - 1, x, minOps, n, memo);

        return memo[i][k] = Math.min(skip, pick);
    }
}
