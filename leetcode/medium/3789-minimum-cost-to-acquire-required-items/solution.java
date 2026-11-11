class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        // Option 1: Buy all requirements using individual items (Type 1 and Type 2)
        long a = (long) need1 * cost1 + (long) need2 * cost2;
        
        // Option 2: Buy enough bundle items (Type 3) to cover the maximum requirement
        long b = (long) costBoth * Math.max(need1, need2);
        
        // Option 3: Buy bundle items (Type 3) for the minimum of need1 and need2,
        // then buy the remaining required items using individual Type 1 or Type 2 items
        int mn = Math.min(need1, need2);
        long c = (long) costBoth * mn + (long) (need1 - mn) * cost1 + (long) (need2 - mn) * cost2;
        
        // Return the minimum total cost among all three strategies
        return Math.min(a, Math.min(b, c));
    }
}