class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] ans = new int[n];
        int depth = 0;

        for (int i = 0; i < n; i++) {
            char c = seq.charAt(i);
            if (c == '(') {
                depth++;
                // Assign to group 0 if depth is even, group 1 if depth is odd
                ans[i] = depth % 2; 
            } else {
                // Assign to group 0 if depth is even, group 1 if depth is odd
                ans[i] = depth % 2; 
                depth--;
            }
        }
        
        return ans;
    }
}
