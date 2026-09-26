import java.util.*;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        // Step 1: Store knowledge in a map for O(1) retrieval
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }
        
        StringBuilder result = new StringBuilder();
        StringBuilder key = new StringBuilder();
        boolean inBracket = false;
        
        // Step 2: Parse the string in a single pass
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                inBracket = true;
            } else if (c == ')') {
                inBracket = false;
                // Look up the key and append its value or '?'
                String keyStr = key.toString();
                result.append(map.getOrDefault(keyStr, "?"));
                key.setLength(0); // Clear the key buffer
            } else {
                if (inBracket) {
                    key.append(c);
                } else {
                    result.append(c);
                }
            }
        }
        
        return result.toString();
    }
}
