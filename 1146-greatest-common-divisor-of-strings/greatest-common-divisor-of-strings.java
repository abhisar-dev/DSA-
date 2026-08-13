class Solution {
    public String gcdOfStrings(String str1, String str2) {
        // Check if they have a common base pattern
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }
        
        // Find the GCD of the two string lengths
        int gcdLength = gcd(str1.length(), str2.length());
        
        // Return the prefix substring of that length
        return str1.substring(0, gcdLength);
    }
    
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
