import java.util.function.IntPredicate;

class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int zero = 0;
        
        // Find the index of 0
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zero = i;
                break;
            }
        }
        
        final int finalZero = zero;
        
        // Helper function to check if sequence is increasing with a given step direction (+1 or -1)
        IntPredicate check = step -> {
            for (int i = 1; i < n; i++) {
                int prev = (finalZero + (i - 1) * step + n) % n;
                int curr = (finalZero + i * step + n) % n;
                if (nums[prev] > nums[curr]) {
                    return false;
                }
            }
            return true;
        };
        
        int ans = Integer.MAX_VALUE;
        
        // Check moving right (step = 1)
        if (check.test(1)) {
            ans = Math.min(ans, zero);          // Rotate left 'zero' times
            ans = Math.min(ans, n - zero + 2);  // Reverse, then rotate/reverse variations
        }
        
        // Check moving left (step = -1)
        if (check.test(-1)) {
            ans = Math.min(ans, zero + 2);      // Reverse and rotate combinations
            ans = Math.min(ans, n - zero);      // Rotate right / shift variations
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
