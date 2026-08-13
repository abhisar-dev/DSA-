class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n == 0) return true;
        
        for (int i = 0; i < flowerbed.length; i++) {
            // Check if current plot is empty and both adjacent plots are empty or out of bounds
            boolean leftEmpty = (i == 0) || (flowerbed[i - 1] == 0);
            boolean rightEmpty = (i == flowerbed.length - 1) || (flowerbed[i + 1] == 0);
            
            if (flowerbed[i] == 0 && leftEmpty && rightEmpty) {
                flowerbed[i] = 1; // Plant a flower
                n--;
                if (n == 0) return true;
            }
        }
        
        return n <= 0;
    }
}
