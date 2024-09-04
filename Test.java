import java.util.*;

class Test {
    public static void main(String[] args) {
        // int[] arr = { 1, 2, 3 };
        // Total permutations is N! i.e 3x2x1 = 6
        // printPermutation(arr, 0, new boolean[arr.length], new ArrayList<Integer>());
        int n = 4;
        char[][] arr = new char[n][n];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                arr[i][j] = '.';
            }
        }
        nQueens(arr, n);
    }

    public static void printPermutation(int[] arr, int pos, boolean[] selected, ArrayList<Integer> ans) {
        if (pos == arr.length) {
            System.out.println(ans);
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (selected[i] == false) {
                selected[i] = true;
                ans.add(arr[i]);
                printPermutation(arr, pos + 1, selected, ans);
                selected[i] = false;
                ans.remove(ans.size() - 1); // Backtrack to remove the current element from the array and list.
            }
        }
    }

    // Recursion track has N length
    public static void tss(int[] arr, int i, int target, ArrayList<Integer> ans) {
        if (target < 0)
            return;
        if (i == arr.length) {
            System.out.println(ans);
            return;
        }

        // Accept the ith element
        ans.add(arr[i]);
        tss(arr, i + 1, target - arr[i], ans);
        ans.remove(ans.size() - 1);

        // Reject the ith element
        tss(arr, i + 1, target, ans);
    }

    public static void print(char[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean isSafe(char[][] arr, int row, int col) {
        // Check the column
        for (int i = 0; i < row; i++) {
            if (arr[i][col] == 'Q')
                return false;
        }

        // Check the upper left diagonal
        int i = row - 1, j = col - 1;
        while (i >= 0 && j >= 0) {
            if (arr[i][j] == 'Q')
                return false;
            i--;
            j--;
        }

        // Check the upper right diagonal
        i = row - 1;
        j = col + 1;
        while (i >= 0 && j < arr[0].length) {
            if (arr[i][j] == 'Q')
                return false;
            i--;
            j++;
        }

        return true;
    }

    public static void nQueens(char[][] arr, int row) {
        if (row == arr.length) {
            print(arr);
            return;
        }

        for (int col = 0; col < arr[0].length; col++) {
            if (isSafe(arr, row, col)) {
                arr[row][col] = 'Q';
                nQueens(arr, row + 1);
                arr[row][col] = '.';
            }
        }
    }

}