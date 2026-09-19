class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // Find the closest x and y coordinates on the rectangle to the circle's center
        int closestX = clamp(xCenter, x1, x2);
        int closestY = clamp(yCenter, y1, y2);
        
        // Calculate the distance components from the circle's center to this closest point
        int distX = xCenter - closestX;
        int distY = yCenter - closestY;
        
        // Compare the squared distance with the squared radius to avoid square root operations
        return (distX * distX) + (distY * distY) <= (radius * radius);
    }
    
    // Helper function to clamp a value within a specific range [min, max]
    private int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }
}
