import java.util.ArrayList;

public class RecursionBacktracking {
    public static void main(String[] args) {

        // 1. Permutations of the array
        // int[] arr = {1,2,3};
        // printPermutations(arr, 0, new boolean[3], new ArrayList<Integer>());

        // 2. Sum of subset elements equals target
        // int[] arr = {1,2,3};
        // int target = 3;
        // sumSubset(arr, 0, target, new ArrayList<Integer>());

        // 3. nQueens Problem - Arrange N queens in a N x N board.
        // int N = 8;
        // char[][] arr = new char[N][N];
        // for(int i=0;i<N;i++){
        // for(int j=0;j<N;j++){
        // arr[i][j]='.';
        // }
        // }
        // nQueens2(arr, 0, new boolean[N], new boolean[2*N-1], new boolean[2*N-1]);

        // 4. Sudoku Solver
        // int N = 9;
        // char[][] arr = new char[N][N];
        // for(int i=0;i<N;i++){
        // for(int j=0;j<N;j++){
        // arr[i][j]='.';
        // }
        // }
        // solveSudoku(arr);

    }

    /**
     * 1*. Total Permutations of the elements of the array
     * 
     * @param arr      - Array entered by the use
     * @param pos      - Starting position will be 0 and will get incremented after
     *                 adding new element
     * @param selected - Boolean array to find out whether the number was selected
     *                 before or not
     * @param result   - Result array storing
     */
    public static void printPermutations(int arr[], int pos, boolean[] selected, ArrayList<Integer> result) {
        if (pos == arr.length) {
            System.out.println("1");
            System.out.println(result);
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (selected[i] == false) {
                selected[i] = true;
                result.add(arr[i]);
                printPermutations(arr, pos + 1, selected, result);
                selected[i] = false;
                result.remove(result.size() - 1);
            }
        }
    }

    /**
     * 2*. Print all subsets of array arr such that (sum of the elements == target)
     * Print subset
     * 
     * @param arr    - Array
     * @param i      - Index of the current element in consideration
     * @param target - Target sum to achieve
     * @param result - Resultant arraylist to display
     */
    public static void sumSubset(int arr[], int i, int target, ArrayList<Integer> result) {
        if (target < 0) {
            return;
        }

        if (i == arr.length) {
            if (target == 0)
                System.out.println(result);
            return;
        }

        // Select element with index 'i'
        result.add(arr[i]);
        sumSubset(arr, i + 1, target - arr[i], result);
        result.remove(result.size() - 1);

        // Discard element with index 'i'
        sumSubset(arr, i + 1, target, result);
    }

    /**
     * 3*. nQueens - Arrange N queens in a N x N chessboard
     * 
     * @param arr = Empty char array with only '.'
     * @param row = N
     */
    public static void nQueens(char arr[][], int row) {
        if (row == arr.length) {
            print2DArray(arr);
            System.out.println("---XXX---");
            return;
        }
        for (int col = 0; col < arr.length; col++) {
            if (isSafe(arr, row, col)) {
                arr[row][col] = 'Q';
                nQueens(arr, row + 1);
                arr[row][col] = '.';
            }
        }
    }

    // 3. Check safety of the queen at particular index
    public static boolean isSafe(char[][] arr, int row, int col) {
        // TOP Column
        for (int i = row - 1; i >= 0; i--) {
            if (arr[i][col] == 'Q')
                return false;
        }

        // Left Diagnol
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (arr[i][j] == 'Q')
                return false;
        }

        // Right Diagnol
        for (int i = row - 1, j = col + 1; i >= 0 && j < arr.length; i--, j++) {
            if (arr[i][j] == 'Q')
                return false;
        }

        return true;
    }

    // 3. Print the 2D array
    public static void print2DArray(char[][] arr) {
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr.length; col++) {
                System.out.print(arr[row][col]);
            }
            System.out.println();
        }
    }

    // 3**. Optimised nQueens
    public static void nQueens2(char arr[][], int row, boolean[] columns, boolean leftDiagnol[],
            boolean[] rightDiagnol) {
        if (row == arr.length) {
            print2DArray(arr);
            System.out.println("----------");
            return;
        }
        for (int col = 0; col < arr[0].length; col++) {
            if (columns[col] == false && leftDiagnol[row + col] == false
                    && rightDiagnol[row - col + arr.length - 1] == false) {
                arr[row][col] = 'Q';
                columns[col] = true;
                leftDiagnol[row + col] = true;
                rightDiagnol[row - col + arr.length - 1] = true;
                nQueens2(arr, row + 1, columns, leftDiagnol, rightDiagnol);

                arr[row][col] = '.';
                columns[col] = false;
                leftDiagnol[row + col] = false;
                rightDiagnol[row - col + arr.length - 1] = false;
            }
        }
    }

    // 4. Sudoku Solver
    public static void solveSudoku(char[][] arr) {
        if (sudokuSolver(arr, 0, 0)) {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    System.out.print(arr[i][j]);
                }
                System.out.println();
            }
        }
    }

    // 4*. Sudoku Solver
    public static boolean sudokuSolver(char[][] arr, int x, int y) {
        if (x == arr.length) {
            return true;
        }

        // Traverse row and column change
        int nextX = y == 8 ? x + 1 : x;
        int nextY = y == 8 ? 0 : y + 1;

        // Checking if the array element is empty
        if (arr[x][y] == '.') {
            for (int val = 1; val <= 9; val++) {
                if (canPlace(arr, val, x, y)) {
                    arr[x][y] = (char) (val + '0');
                    if (sudokuSolver(arr, nextX, nextY)) {
                        // After filling the element move to next index
                        return true;
                    }
                    // Backtracking
                    arr[x][y] = '.';
                }
            }
        } else {
            // If the current element is already filled then move to next index
            if (sudokuSolver(arr, nextX, nextY))
                return true;
        }

        return false;
    }

    // 4*. Sudoku Solver
    public static boolean canPlace(char[][] arr, int val, int x, int y) {
        // Check if value is present in row
        for (int col = 0; col < arr.length; col++) {
            if (arr[x][col] == (char) (val + '0'))
                return false;
        }

        // Check if value is present in column
        for (int row = 0; row < arr.length; row++) {
            if (arr[row][y] == (char) (val + '0'))
                return false;
        }

        // Check in 3x3 grid
        int xIndex = x - (x % 3);
        int yIndex = y - (y % 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (arr[xIndex + i][yIndex + j] == (char) (val + '0'))
                    return false;
            }
        }

        return true;

    }

}