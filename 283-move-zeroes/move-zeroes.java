class Solution {
    public void moveZeroes(int[] nums) {
        // Pointer to keep track of the position for the next non-zero element
        int insertPos = 0;
        
        // Step 1: Shift all non-zero elements to the front
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[insertPos] = nums[i];
                insertPos++;
            }
        }
        
        // Step 2: Fill the remaining indices with zeros
        while (insertPos < nums.length) {
            nums[insertPos] = 0;
            insertPos++;
        }
    }
}
