class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // A single digit can contribute at most 9.
        // If the target sum exceeds num * 9, it is impossible.
        if (sum > 9 * num) {
            return "";
        }
        
        int nines = sum / 9;
        int remainder = sum % 9;
        StringBuilder ans = new StringBuilder(num);
        
        // Add as many '9's as possible to maximize the sum of squares
        for (int i = 0; i < nines; i++) {
            ans.append('9');
        }
        
        // Add the single remainder digit, if any
        if (remainder > 0) {
            ans.append((char) ('0' + remainder));
        }
        
        // Pad the remaining length with '0's
        while (ans.length() < num) {
            ans.append('0');
        }
        
        return ans.toString();
    }
}
