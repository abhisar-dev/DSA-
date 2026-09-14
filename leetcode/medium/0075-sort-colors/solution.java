class Solution {
    public void sortColors(int[] nums) {
        int count = 0 ;
        int count1 = 0 ;
        for (int num : nums){
            if(num==0)count++;
            else if (num == 1 )count1 ++;
        }
     for (int i = 0 ; i< nums.length;i++){
        if (i<count){
            nums[i]= 0 ;
        }
        else if (i<count+count1){
            nums[i]=1;
        }
        else{
            nums[i] = 2 ;
        }
     }
    }
}