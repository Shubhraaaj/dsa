public class DynamicProgramming {
    public static void main(String[] args) {
        // 1. Fibonacci
        // int n=10;
        // System.out.println(fibDpTab(n));

        // 2. Find total number of ways to climb stair to N
        // int N = 4;
        // System.out.println(climbStairsDPTab(N));

        // 3. Least sum of stairs to stair N by taking one step or two step
        // int N = 9;
        // int[] arr = { 1, 2, 3, 4, 1, 3, 1, 8, 3 };
        // System.out.println(climbToNDPTab(arr, N));

        // 4. Longest increasing sequence
        int[] arr = {10, 2, 9, 5, 7, 3, 60, 80, 1};
        // System.out.println(lis(arr, 0, -1, new int[arr.length][arr.length]));

        // 4. Longest increasing sequence 2nd approach
        // int ans = 0;
        // for(int i=0; i<arr.length; i++){
        //     int len = lis2(arr, i);
        //     ans = Math.max(ans, len);
        // }
        // System.out.println(ans);

        // 4. Longest increasing sequence tabulation approach
        System.out.println(lisTab(arr));
    }
    
    // 1. Fibonacci without DP
    public static int fib(int n){
        if(n==0||n==1) 
            return n;
        return fib(n-1)+fib(n-2);
    }

    // 1*. Fibonacci with DP - Memoization (Top-down approach)
    public static int fibDp(int n, int[] dp){
        if(n==0||n==1) 
            return n;
        
        if(dp[n]!=0) 
            return dp[n];

        int fn = fibDp(n-1, dp) + fibDp(n-2, dp);
        dp[n] = fn;
        return fn;
    }

    // 1*. Fibonacci with DP - Tabulation (Bottom-up approach)
    public static int fibDpTab(int n){
        int[] dp = new int[n+1];
        dp[0]=0;
        dp[1]=1;

        for(int i=2;i<dp.length;i++)
            dp[i]=dp[i-1]+dp[i-2];
        
        return dp[n];
    }

    // 2. Climb Stair without DP - Can take 1,2 or 3 jumps to reach N. Find total ways
    public static int climbStairs(int n){
        if(n==0) return 1;
        if(n<0) return 0;
        int f1 = climbStairs(n-1);
        int f2 = climbStairs(n-2);
        int f3 = climbStairs(n-3);
        int res = f1 + f2 + f3;
        return res;
    }

    // 2. Climb stairs with DP - Memoization
    public static int climbStairsDP(int n, int dp[]){
        if(n==0) return 1;
        if(n<0) return 0;
        if(dp[n]!=0)
            return dp[n];
        int res = climbStairsDP(n-1, dp) + climbStairsDP(n-2, dp) + climbStairsDP(n-3, dp);
        dp[n] = res;
        return res;
    }

    // 2. Climb stairs with DP - Tabulation
    public static int climbStairsDPTab(int n){
        int[] dp = new int[n+1];
        dp[0]=1;
        dp[1]=1;
        dp[2]=2;
        for(int i=3;i<=n;i++)
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        return dp[n];
    }

    // 3. Climb stairs to n without DP
    public static int climbToN(int[] arr, int n){
        if(n==0)
            return arr[0];
        if(n==1)
            return arr[0] + arr[1];
        int f1 = climbToN(arr, n-1);
        int f2 = climbToN(arr, n-2);
        int res = Math.min(f1, f2) + arr[n-1];
        return res;
    }

    // 3. Climb stairs to n with DP - Memoization
    public static int climbToNDP(int[] arr, int n, int[] dp){
        if(n==0)
            return arr[0];
        if(n==1)
            return arr[0] + arr[1];
        if(dp[n]!=0)
            return dp[n];
        int f1 = climbToNDP(arr, n-1, dp);
        int f2 = climbToNDP(arr, n-2, dp);
        int res = Math.min(f1, f2) + arr[n-1];
        dp[n] = res;
        return res;
    }

    // 3. Climb stairs to n with DP - Tabulation
    public static int climbToNDPTab(int[] arr, int n){
        int dp[] = new int[n];
        dp[0] = arr[0];
        dp[1] = arr[0] + arr[1];
        for(int i=2; i<n; i++){
            dp[i] = Math.min(dp[i-1], dp[i-2]) + arr[i];
        }
        return dp[n-1];
    }

    // 4. Longest Increasing Sequence in an Array
    public static int lis(int[] arr, int idx, int pidx, int[][] dp){
        if(idx==arr.length)
            return 0;
        
        if(pidx!=-1 && dp[idx][pidx]!=0){
            return dp[idx][pidx];
        }
        
        int f1 = 0 + lis(arr, idx+1, pidx, dp);
        int f2 = 0; // current element wants to be a part of the increasing subsequence
        if(pidx == -1 || arr[idx] > arr[pidx]){
            f2 = 1 + lis(arr, idx+1, idx, dp);
        }
        int ans = Math.max(f1, f2);
        
        if(pidx!=-1){
            dp[idx][pidx]=ans;
        }
            
        return ans;
    }

    // 4. Longest increasing subsequence ending at ith
    public static int lis2(int[] arr, int i){
        int max = 0;
        for(int a=0; a<i; a++){
            // Consider all the elements smaller than current element in left side
            if(arr[a] < arr[i]){
                max = Math.max(max, lis2(arr, a));
            }
        }
        return max+1;
    }

    public static int lisTab(int[] arr){
        int[] dp = new int[arr.length];
        dp[0] = 1;
        int ans = 0;
        for(int i=1;i<arr.length;i++){
            int max = 0;
            for(int j=0;j<i;j++){
                if(arr[j]<arr[i]){
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i]=max+1;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
