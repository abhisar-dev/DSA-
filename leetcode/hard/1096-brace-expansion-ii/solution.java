import java.util.*;

public class Solution {
    public List<String> braceExpansionII(String expression) {
        // Stack to store state before entering a brace block '{'
        // We push both the current union group and the current product group to the stack.
        Stack<Object> stack = new Stack<>();
        
        List<String> union = new ArrayList<>();
        List<String> prod = new ArrayList<>();
        prod.add(""); // Base case for multiplication anchor

        int i = 0;
        while (i < expression.length()) {
            char c = expression.charAt(i);

            if (Character.isLetter(c)) {
                // Case 1: Alphabet character. Concatenate it with all existing values in the current product group.
                List<String> nextProd = new ArrayList<>();
                for (String p : prod) {
                    nextProd.add(p + c);
                }
                prod = nextProd;
                i++;
            } else if (c == '{') {
                // Case 2: Open brace. Save current context onto stack and reset context for the block.
                stack.push(union);
                stack.push(prod);
                union = new ArrayList<>();
                prod = new ArrayList<>();
                prod.add("");
                i++;
            } else if (c == '}') {
                // Case 3: Close brace. Wrap up the inside block by joining its final union and product groups.
                List<String> innerResult = new ArrayList<>(union);
                innerResult.addAll(prod);

                // Pop previous states
                List<String> prevProd = (List<String>) stack.pop();
                List<String> prevUnion = (List<String>) stack.pop();

                // Compute Cartesian product between outer preceding group and current inner result
                List<String> nextProd = new ArrayList<>();
                for (String p : prevProd) {
                    for (String r : innerResult) {
                        nextProd.add(p + r);
                    }
                }
                
                prod = nextProd;
                union = prevUnion;
                i++;
            } else if (c == ',') {
                // Case 4: Comma delimiter. Add the finished product string list to the union group, then reset product.
                union.addAll(prod);
                prod = new ArrayList<>();
                prod.add("");
                i++;
            }
        }

        // Combine any final remaining products into the union group
        union.addAll(prod);

        // De-duplicate using a Set and sort alphabetically as per requirements
        Set<String> uniqueSorted = new TreeSet<>(union);
        return new ArrayList<>(uniqueSorted);
    }
}
