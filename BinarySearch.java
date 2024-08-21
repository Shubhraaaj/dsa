public class Main {
    public static void main(String[] args) {
        int[] arr = {5, 6, 7, 8, 1, 2, 3, 4};
        int key = 1;
        int index = findElementInRotatedArray(arr, key);
        System.out.println("Index of " + key + " is: " + index);
        
    }
public static int findElementInRotatedArray(int[] arr, int key) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // If the element is found at mid
            if (arr[mid] == key) {
                return mid;
            }

            // Check if the left half is sorted
            if (arr[left] <= arr[mid]) {
                // If the key is in the left half
                if (key >= arr[left] && key < arr[mid]) {
                    right = mid - 1;
                } else { // Otherwise, it's in the right half
                    left = mid + 1;
                }
            }
            // Otherwise, the right half must be sorted
            else {
                // If the key is in the right half
                if (key > arr[mid] && key <= arr[right]) {
                    left = mid + 1;
                } else { // Otherwise, it's in the left half
                    right = mid - 1;
                }
            }
        }

        // If the element is not found
        return -1;
    }
}