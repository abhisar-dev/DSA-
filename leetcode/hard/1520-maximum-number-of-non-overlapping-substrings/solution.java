class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] left = new int[26];
        int[] right = new int[26];
        
        java.util.Arrays.fill(left, n);
        java.util.Arrays.fill(right, -1);
        
        // Step 1: Record the first and last occurrence for each character
        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            left[idx] = Math.min(left[idx], i);
            right[idx] = i;
        }
        
        List<String> res = new ArrayList<>();
        int lastRight = -1;
        
        // Step 2: Find valid and optimal substrings greedily
        for (int i = 0; i < n; i++) {
            if (i == left[s.charAt(i) - 'a']) {
                int r = getValidRight(s, i, left, right);
                if (r != -1) {
                    if (i > lastRight) {
                        res.add(s.substring(i, r + 1));
                    } else {
                        res.set(res.size() - 1, s.substring(i, r + 1));
                    }
                    lastRight = r;
                }
            }
        }
        
        return res;
    }
    
    private int getValidRight(String s, int start, int[] left, int[] right) {
        int r = right[s.charAt(start) - 'a'];
        for (int j = start; j <= r; j++) {
            int idx = s.charAt(j) - 'a';
            // If any character in the range appears before our start, 
            // this substring is invalid.
            if (left[idx] < start) {
                return -1;
            }
            // Expand the right boundary if needed.
            r = Math.max(r, right[idx]);
        }
        return r;
    }
}