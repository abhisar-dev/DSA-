class Solution {
    public int maxPalindromes(String s, int k) {
        int count = 0;
        int n = s.length();
        int i = 0;
        
        while (i <= n - k) {
            // Check for a palindrome of length k starting at index i
            if (isPalindrome(s, i, i + k - 1)) {
                count++;
                i += k; // Skip over the matched palindrome
            } 
            // Check for a palindrome of length k + 1 starting at index i
            else if (i + k < n && isPalindrome(s, i, i + k)) {
                count++;
                i += k + 1; // Skip over the matched palindrome
            } 
            // If no palindrome of length k or k+1 starts here, move 1 step forward
            else {
                i++;
            }
        }
        
        return count;
    }
    
    // Helper method to verify if a substring is a palindrome
    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
