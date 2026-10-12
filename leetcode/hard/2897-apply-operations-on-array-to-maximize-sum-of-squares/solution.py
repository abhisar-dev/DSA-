class Solution:
    def maxSum(self, nums: list[int], k: int) -> int:
        MOD = 1_000_000_007
        MAX_BIT = 30
        bits = [0] * MAX_BIT
        
        # Count the frequency of each bit position
        for num in nums:
            for i in range(MAX_BIT):
                if (num >> i) & 1:
                    bits[i] += 1
                    
        ans = 0
        # Greedily build the k largest possible numbers
        for _ in range(k):
            cur = 0
            for i in range(MAX_BIT):
                if bits[i] > 0:
                    cur |= (1 << i)
                    bits[i] -= 1
            ans = (ans + cur * cur) % MOD
            
        return ans
