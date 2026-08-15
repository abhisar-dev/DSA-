import java.util.Arrays;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        // Initialize the closest sum with the first possible triplet
        int closestSum = nums[0] + nums[1] + nums[2];
        
        // Sort the array to use the two-pointer technique
        Arrays.sort(nums);
        
        // Iterate through the array, fixing the first element of the triplet
        for (int i = 0; i < nums.length - 2; i++) {
            // Optional optimization: Skip duplicate elements to reduce unnecessary checks
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            int left = i + 1;
            int right = nums.length - 1;
            
            // Use two pointers to find the remaining two elements
            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];
                
                // If we find an exact match, return it immediately
                if (currentSum == target) {
                    return currentSum;
                }
                
                // Update closestSum if the current triplet is closer to the target
                if (Math.abs(currentSum - target) < Math.abs(closestSum - target)) {
                    closestSum = currentSum;
                }
                
                // Move pointers based on the sum comparison with target
                if (currentSum < target) {
                    left++; // We need a larger sum
                } else {
                    right--; // We need a smaller sum
                }
            }
        }
        
        return closestSum;
    }
}
