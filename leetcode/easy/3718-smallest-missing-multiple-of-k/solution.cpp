class Solution {
public:
    int missingMultiple(vector<int>& nums, int k) {
        sort(nums.begin(),nums.end());
        int x=k;
        for(int num:nums){
            if(num<k) continue;
            else if(num==k){
                k+=x;
            }
            else return k;
        }
        return k;
    }
};