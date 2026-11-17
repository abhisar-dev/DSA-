class Solution {
    public String digitSum(String s, int k) {
        while (s.length() > k) {
            StringBuilder nextS = new StringBuilder();
            
            for (int i = 0; i < s.length(); i += k) {
                int sum = 0;
                // Ensure we don't go out of bounds on the final group
                int end = Math.min(i + k, s.length());
                
                for (int j = i; j < end; j++) {
                    sum += s.charAt(j) - '0';
                }
                
                nextS.append(sum);
            }
            
            s = nextS.toString();
        }
        
        return s;
    }
}
