class Solution {
    public int[] sortedSquares(int[] nums) {
        int n=nums.length;
        int left = 0;
        int right = n-1;
        int k=right;
        int []ans= new int[n];
        while(left<=right){
          if (nums[left]*nums[left]>nums[right]*nums[right]){
            ans[k--]= nums[left]*nums[left];
            left ++ ;
          }
          else {
            ans[k--]= nums[right]*nums[right];
            right--;
          }
        }
        return ans ; 
    }
}