class Solution {
    public int numOfSubarrays(int[] arr) {
        int MOD = 1_000_000_007;
        long totalOddSubarrays = 0;
        
        // Initialize evenCount to 1 for the empty prefix sum (0 is even)
        int evenCount = 1;
        int oddCount = 0;
        int prefixSum = 0;

        for (int num : arr) {
            prefixSum += num;

            if (prefixSum % 2 == 0) {
                // Even prefix sum minus an odd prefix sum yields an odd subarray sum
                totalOddSubarrays = (totalOddSubarrays + oddCount) % MOD;
                evenCount++;
            } else {
                // Odd prefix sum minus an even prefix sum yields an odd subarray sum
                totalOddSubarrays = (totalOddSubarrays + evenCount) % MOD;
                oddCount++;
            }
        }

        return (int) totalOddSubarrays;
    }
}
