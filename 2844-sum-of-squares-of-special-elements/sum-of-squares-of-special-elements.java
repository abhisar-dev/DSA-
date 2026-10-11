class Solution {
    public int sumOfSquares(int[] nums) {
        int n = nums.length;
        int sum = 0;
        
        for (int i = 0; i < n; i++) {
            // Convert 0-based index to 1-based index by adding 1
            if (n % (i + 1) == 0) {
                sum += nums[i] * nums[i];
            }
        }
        
        return sum;
    }
}
