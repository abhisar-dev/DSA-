class Solution {
    public String reverseWords(String s) {
        // 1. Trim leading and trailing spaces, then split by 1 or more spaces
        String[] words = s.trim().split("\\s+");
        
        // 2. Use StringBuilder to assemble the reversed components
        StringBuilder reversed = new StringBuilder();
        
        // 3. Iterate backwards through the array
        for (int i = words.length - 1; i >= 0; i--) {
            reversed.append(words[i]);
            if (i > 0) {
                reversed.append(" "); // Add a single space between words
            }
        }
        
        return reversed.toString();
    }
}
