import java.util.*;

class Arrays {

    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        int target = 9;
        int[] ans = twoSum(arr, target);
        System.out.println(ans[0] + " " + ans[1]);
    }

    /**
     * Question
     * 2-sum Problem
     * Find the indices of two numbers such that they add up to a specific target number.
     * 
     * Solution
     * We will be using HashMap to store the difference between the element and the index
     * If the difference is already present in the map, we return the indices
     */

    public static int[] twoSum(int[] arr, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<arr.length; i++) {
            int complement = target - arr[i];
            if(map.containsKey(complement)) {
                return new int[] {map.get(complement), i};
            }
            map.put(arr[i], i);
        }
        return new int[] {-1, -1};
    }

    /**
     * Question
     * 3-sum Problem
     * Find all the sets of three elements such that they add up to a specific target number.
     * 
     * Solution
     * a[i] + a[j] + a[k] = target
     * a[k] = target - a[i] - a[j]
     * We will use a hashmap to store the frequency of each element in the array.
     * We will have 2 pointers i=0 and j=1 to iterate over the array.
     * Time Complexity: O(n^2)
     * Space Complexity: O(n) + O(m)
     * 
     * Solution 2 - Sort the array and iterate over the array.
     * 
     */

    public static void threeSum(int[] a, int target, int n){
        HashMap<Integer, Integer> map= new HashMap<Integer, Integer>();
        for(int i=0; i<n; i++){
            // Storing frequency of each of the elements
            if(map.containsKey(a[i]))
                map.put(a[i], map.get(a[i]) + 1);
            else 
                map.put(a[i], 1);
        }
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                int sum = target - a[i] - a[j];
                if(map.containsKey(sum)){
                    if(map.get(sum) > 1){
                        System.out.println(a[i] + " " + a[j] + " " + sum);
                        map.put(sum, map.get(sum) - 1);
                    }
                }
            }
        }
    }

     public static void threeSumNoSpace(int[] a, int tar, int n){
        Arrays.sort(a);
        for(int i=0; i<n; i++){
            int j = i+1;
            int k = n-1;
            int target = tar - a[i];
            while(j < k){
                if(a[i] + a[j] + a[k] == target){
                    System.out.println(a[i] + " " + a[j] + " " + a[k]);
                    while(j < k && a[j] == a[j+1]) j++;
                    while(j < k && a[k] == a[k-1]) k--;
                    j++;
                    k--;
                } else if(a[i] + a[j] + a[k] < target){
                    j++;
                } else {
                    k--;
                }
            }
        }
     }

    public class Main
{
    /*
     * Smallest number whose product is N
     * If N = 100 then the smallest number will be 455
     * If N = 17 then the smallest number will be 17 because it is prime and has not factors
     * If N = 81000 then the smallest number will be 555899.
     * 
     * Approach
     * Check the divisibility by every digit from 2 to 9 as there are only these digits
     * Create an array from 2 to 9 and keep dividing and incrementing the value by 1
     * Start from the lowest index i.e 2 and keep on adding the numbers to the string
     * Time - O(logN)
     * Space - O(1)
    */
    
    public static boolean isPrime(int n){
        for(int i = 2; i<=n/2; i++)
            if(n%i==0)
                return false;
        return true;
    }
    
    public static int smallestNumber(int n){
        if(n<10) return n;
        if(isPrime(n)) return n;
        int[] factors = new int[8];
        for(int i=9;i>1;i--){
            while(n%i==0){
                factors[i-2]=factors[i-2]+1;
                n/=i;
            }
            if(n==1) break;
        }
        String result = "";
        for(int i=0;i<factors.length;i++){
            while(factors[i]!=0){
                result = result+""+(i+2);
                factors[i]=factors[i]-1;
            }
        }
        return Integer.parseInt(result);
    }

    public static String smallestNumberPro(int n){
        String ans = "";
        for(int i=9;i>=2;i--){
            while(n%i==0){
                ans=i+ans;
                n/=i;
            }
        }
        if(n!=1) return String.valueOf(n);
        return ans;
    }

    /*
     * Find the majority element in the array
     * Majority element is an element which appears more then n/2 times in the array where n = length of array
     * 
     * Approach
     * 1. Bruteforce the array to calculate the frequency of each element
     * 2. Store the frequency of each element in a hashmap
     * 3. Moore's voting algorithm - Majority element is occurring n/2 times therefore other elements is less than n/2 times
    */
    
    public static void majorElement(int[] n) {
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < n.length; i++) {
            freqMap.put(n[i], freqMap.getOrDefault(n[i], 0) + 1);
        }
        
        final int[] result = {-1}; // Using an array to hold the result
        freqMap.forEach((k, v) -> {
            if (v > n.length / 2) {
                result[0] = k; // Updating the result array
            }
        });
        
        if (result[0] != -1) {
            System.out.println(result[0]);
        } else {
            System.out.println("No majority element found");
        }
    }

    /*
    * Rotate the N-matrix to 90 degrees
    * Approach
    * 1. Transpose the matrix
    *    a. The diagonal elements are constant
    *    b. a[i,j] = temp = a[j,i] 
    * 2. Swap the columns a[0]...a[n]
    * Time - O(n^2)
    * Space - O(1)
    */

    public static void rotateMatrix(int[][] n){
        // Transpose the matrix
        for(int i=0; i<n.length; i++){
            for(int j=0; j<n.length; j++){
                int temp = n[i][j];
                n[i][j]=n[j][i];
                n[j][i]=temp;
            }
        }
        // Swap the columns
        int left = 0, right = n.length;
        while(left<right){
            for(int i=0; i< n.length; i++){
                int temp = n[i][left];
                n[i][left] = n[i][right];
                n[i][right] = temp;
            }
            left++;
            right--;
        }
    }

    /*
     * Print the elements of a NxM matrix in sprial order
     * 
     * Approach
     * 1. Consider the matrix as rectangles
     * 2. We will have 4 variables - rMin, rMax, cMin, cMax
     * 3. We start printing with top boundary, right boundary, bottom boundary, left boundary
     * 4. To print the top boundary
     *    a. rMin remains constant and we traverse from cMin to cMax
     *    b. Then we increment rMin++, so it moves to next row.
     * 5. To print the right boundary
     *    a. cMax remains constant and we traverse from rMin to rMax
     *    b. Then we decrement cMax--, so it moves to the previous column.
     * 6. To print the bottom boundary
     *    a. rMax remains constant and we traverse from cMax to cMin
     *    b. Then we decrement rMax--, so it moves to previous row.
     * 7. To print the left boundary
     *    a. cMin remains constant and we traverse from rMax to rMin
     *    b. Then we increment cMin++, so it moves to next column.
     * 8. Then we move to the next rectangle inside
     * Time - O(n*m) | Space - O(1)
    */
    
    public static void spiralMatrix(int[][] arr){
        int n=arr.length;
        int m=arr[0].length;
        int rMin=0, rMax=arr.length-1;
        int cMin=0, cMax=arr[0].length-1;
        int count=0;
        
        while(count<n*m){
            // Top boundary
            for(int col=cMin; col<=cMax && count<n*m; col++){
                System.out.print(arr[rMin][col]+" ");
                count++;
            }
            rMin++;
            // Right boundary
            for(int row=rMin; row<=rMax && count<n*m; row++){
                System.out.print(arr[row][cMax]+" ");
                count++;
            }
            cMax--;
            // Bottom boundary
            for(int col=cMax; col>=cMin && count<n*m; col--){
                System.out.print(arr[rMax][col]+ " ");
                count++;
            }
            rMax--;
            // Left boundary
            for(int row=rMax; row>=rMin && count<n*m; row--){
                System.out.print(arr[row][cMin]+ " ");
                count++;
            }
            cMin++;
        }
    }

    /*
    * Given an integer A, generate a square matrix filled with elements from 1 to A2 in spiral order and return the generated square matrix.
    * 
    * Approach
    * 1. It is going to be an NxN matrix so we know the size of the matrix
    * 2. We will have 4 variables - rMin, rMax, cMin, cMax
    * 3. We start printing with top boundary, right boundary, bottom boundary, left boundary
    * 4. To populate the top boundary
    *    a. rMin remains constant and we traverse from cMin to cMax
    *    b. Then we increment rMin++, so it moves to next row.
    * 5. To populate the right boundary
    *    a. cMax remains constant and we traverse from rMin to rMax
    *    b. Then we decrement cMax--, so it moves to the previous column.
    * 6. To populate the bottom boundary
    *    a. rMax remains constant and we traverse from cMax to cMin
    *    b. Then we decrement rMax--, so it moves to previous row.
    * 7. To populate the left boundary
    *    a. cMin remains constant and we traverse from rMax to rMin
    *    b. Then we increment cMin++, so it moves to next column.
    * 8. Then we move to the next rectangle inside
    * Time - O(n*m) | Space - O(1) 
    */

    public int[][] generateSpiralMatrix(int A) {
        int limit=A*A;
        int element=1;
        int[][] res = new int[A][A];
        int rmin=0,rmax=A-1,cmin=0,cmax=A-1;
        while(element<=limit){
            //top left to right
            for(int col=cmin;col<=cmax&&element<=limit;col++){
                res[rmin][col]=element++; 
            }
            rmin++;
            // right top to bottom
            for(int row=rmin;row<=rmax&&element<=limit;row++){
                res[row][cmax]=element++; 
            }
            cmax--;
            // bottom right to left
            for(int col=cmax;col>=cmin&&element<=limit;col--){
                res[rmax][col]=element++; 
            }
            rmax--;
            // left bottom to top
            for(int row=rmax;row>=rmin&&element<=limit;row--){
                res[row][cmin]=element++; 
            }
            cmin++;
        }
        return res;
    }
    
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,4,4,4};
        majorElement(arr);
    }
    
}
