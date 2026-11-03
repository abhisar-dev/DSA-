class Solution {
    public int minMoves(int[] nums) {
        int max = nums[0];
        // Find the maximum element in the array
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }
        
        int moves = 0;
        // Calculate the total moves required to bring all elements to max
        for (int num : nums) {
            moves += (max - num);
        }
        
        return moves;
    }
}
