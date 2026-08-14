class Solution {
    public int maximumLengthSubstring(String s) {
        int[] count = new int[26];
        int left = 0, maxLength = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            count[c - 'a']++;
            
            // If character count exceeds 2, move left pointer
            while (count[c - 'a'] > 2) {
                char leftChar = s.charAt(left);
                count[leftChar - 'a']--;
                left++;
            }
            
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}
