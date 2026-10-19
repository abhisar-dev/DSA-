import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int countLessThanTarget = 0;
        int countTarget = 0;
        
        // Single pass to count smaller elements and target occurrences
        for (int num : nums) {
            if (num < target) {
                countLessThanTarget++;
            } else if (num == target) {
                countTarget++;
            }
        }
        
        List<Integer> result = new ArrayList<>();
        // Populate consecutive indices based on our counts
        for (int i = 0; i < countTarget; i++) {
            result.add(countLessThanTarget + i);
        }
        
        return result;
    }
}
