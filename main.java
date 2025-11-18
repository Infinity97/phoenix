public class BinarySearchIterative {
    public static int binarySearch(int[] arr, int target) {
        // Input validation
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        
        // Check if array is sorted
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i-1]) {
                throw new IllegalArgumentException("Array must be sorted in ascending order");
            }
        }
        
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // Prevents integer overflow

            if (arr[mid] == target) {
                return mid; // Target found
            } else if (arr[mid] < target) {
                left = mid + 1; // Search right half
            } else {
                right = mid - 1; // Search left half
            }
        }

        return -1; // Target not found
    }

    public static void main(String[] args) {
        int[] numbers = {2, 4, 6, 8, 10, 12, 14};
        int target = 10;

        int result = binarySearch(numbers, target);
        if (result != -1)
            System.out.println("Element found at index: " + result);
        else
            System.out.println("Element not found.");
    }
}
