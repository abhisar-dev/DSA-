import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public int sumCounts(List<Integer> nums) {
        int totalSum = 0;
        int n = nums.size();

        // Fix the starting index of the subarray
        for (int i = 0; i < n; i++) {
            Set<Integer> distinctSet = new HashSet<>();
            // Extend the ending index of the subarray
            for (int j = i; j < n; j++) {
                distinctSet.add(nums.get(j));
                // Add the square of the distinct count to the total sum
                int distinctCount = distinctSet.size();
                totalSum += distinctCount * distinctCount;
            }
        }

        return totalSum;
    }
}
