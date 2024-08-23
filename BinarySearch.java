public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {5, 6, 7, 8, 1, 2, 3, 4};
        int key = 1;
        int index = findElementInRotatedArray(arr, key);
        System.out.println("Index of " + key + " is: " + index);
        
    }

    /**
     * Find the start and end index of N in a sorted array
     * We will run two BS one for first index and one for end index
     */

    public static void firstAndLastIndex(int[] arr, int key){
        int left = 0, right = arr.length-1;
        int mid = (left + right) / 2;
        int firstIndex = -1, lastIndex = -1;
        while(left <= right){
            if(arr[mid] == key){
                firstIndex = mid;
                right = mid - 1;
            } else if(arr[mid]<key){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        left = 0;
        right = arr.length - 1;
        while(left <= right){
            if(arr[mid] == key){
                lastIndex = mid;
                left = mid + 1;
            } else if(arr[mid]<key){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println("First index of " + key + " is: " + firstIndex);
        System.out.println("Last index of " + key + " is: " + lastIndex);
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

    /**
     * Find minimum element in a sorted and rotated array
     */

     public static int findMin(int[] arr) {
        int left = 0, right = arr.length -1;
        while(left < right){
            int mid = (left + right) / 2;
            if(arr[mid]<arr[right]){
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return arr[left];
     }

    /**
     * Find the index of the first element in a sorted and rotated array
     * 
     * We need to find the index of the smallest element in a sorted and rotated array
     * Then Binary search in two parts from 0 to minIndex and minIndex to arr.length -1
     */
    public static int binarySearch(int[] arr, int left, int right, int target){
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(arr[mid] == target){
                return mid;
            } else if(arr[mid] < target){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static int findMinIndex(int[] arr){
        int left = 0, right = arr.length - 1;
        while(left < right){
            int mid = (left + right) / 2;
            if(arr[mid] < arr[right]){
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static int findIndexSortedRotated(int[] arr, int key){
        int minIdx = findMinIndex(arr);
        int ans = binarySearch(arr, 0, minIdx -1, key);
        if(ans == -1)
            ans = binarySearch(arr, minIdx -1, arr.length - 1, key);
        return ans;
    }

    /**
     * Check if the element is present in a sorted 2D array
     * Start from top right corner and then start traversing
     * If the element is greater than arr(i,j) then go to the next row else go to the previous column
     */

     public static boolean findElementIn2DArray(int[][] arr, int target){
        int i = 0, j = arr.length - 1;
        while(i< arr.length && j>=0){
            if(arr[i][j]==target){
                return true;
            } else if(arr[i][j]<target){
                i++;
            } else {
                j--;
            }
        }
        return false;
     }
}
    