class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long totalK = (long) k1 + k2;
        long totalDiff = 0;
        int maxDiff = 0;
        
        // Count frequencies of each absolute difference
        int[] count = new int[100_005];
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            if (diff > 0) {
                count[diff]++;
                totalDiff += diff;
                maxDiff = Math.max(maxDiff, diff);
            }
        }
        
        // If total operations can reduce all differences to 0
        if (totalDiff <= totalK) {
            return 0;
        }
        
        // Greedily reduce the largest differences down
        for (int i = maxDiff; i > 0 && totalK > 0; i--) {
            if (count[i] > 0) {
                long reduce = Math.min((long) count[i], totalK);
                count[i] -= reduce;
                count[i - 1] += reduce;
                totalK -= reduce;
            }
        }
        
        // Calculate the final sum of squared differences
        long minSumSquare = 0;
        for (int i = 1; i <= maxDiff; i++) {
            if (count[i] > 0) {
                minSumSquare += (long) count[i] * i * i;
            }
        }
        
        return minSumSquare;
    }
}
