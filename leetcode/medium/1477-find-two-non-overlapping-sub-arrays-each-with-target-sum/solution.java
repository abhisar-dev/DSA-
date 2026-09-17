class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] dp = new int[n];
        int minLen = Integer.MAX_VALUE;
        int res = Integer.MAX_VALUE;
        int sum = 0;
        int left = 0;

        // Initialize dp array with MAX_VALUE
        for (int i = 0; i < n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        for (int right = 0; right < n; right++) {
            sum += arr[right];
            
            // Shrink window if sum exceeds target
            while (sum > target) {
                sum -= arr[left];
                left++;
            }
            
            // If we found a subarray with sum equal to target
            if (sum == target) {
                int currLen = right - left + 1;
                
                // Check if there is a valid non-overlapping subarray before 'left'
                if (left > 0 && dp[left - 1] != Integer.MAX_VALUE) {
                    res = Math.min(res, currLen + dp[left - 1]);
                }
                
                // Update minLen ending at or before 'right'
                minLen = Math.min(minLen, currLen);
            }
            
            // Store the minimum length found so far up to index 'right'
            dp[right] = minLen;
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
