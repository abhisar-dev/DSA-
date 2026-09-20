class Solution {
    public int reverseDegree(String s) {
        int ans = 0;
        
        for (int i = 0; i < s.length(); ++i) {
            // Find the 1-based position in the reversed alphabet ('a'=26, 'b'=25, ..., 'z'=1)
            final int reversePos = 26 - (s.charAt(i) - 'a');
            
            // Multiply by the 1-based position in the string (i + 1) and add to total
            ans += reversePos * (i + 1);
        }
        
        return ans;
    }
}
