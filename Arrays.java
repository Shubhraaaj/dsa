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

}
