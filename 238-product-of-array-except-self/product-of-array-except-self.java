class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];
        
        // Step 1: Calculate prefix products and store in answer array
        answer[0] = 1;
        for (int i = 1; i < n; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }
        
        // Step 2: Calculate suffix products on the fly and multiply with prefix
        int suffixProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            answer[i] = answer[i] * suffixProduct;
            suffixProduct *= nums[i];
        }
        
        return answer;
    }
}
