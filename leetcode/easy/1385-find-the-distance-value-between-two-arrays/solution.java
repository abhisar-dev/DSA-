import java.util.Arrays;

class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        // Sort arr2 to enable fast range checking via binary search
        Arrays.sort(arr2);
        int distanceValueCount = 0;

        for (int val : arr1) {
            // Check if any element in arr2 falls within [val - d, val + d]
            if (isValid(arr2, val, d)) {
                distanceValueCount++;
            }
        }

        return distanceValueCount;
    }

    private boolean isValid(int[] arr, int target, int d) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // If an element is within the invalid window, target fails the condition
            if (Math.abs(arr[mid] - target) <= d) {
                return false;
            }

            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        // No element found in the invalid range
        return true;
    }
}
