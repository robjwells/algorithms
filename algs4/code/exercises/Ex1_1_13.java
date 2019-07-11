import java.util.Arrays;

class Ex1_1_13 {
    public static void main(String[] args) {
        int[][] array = new int[][] {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
        };
        System.out.println("Source:");
        for (int[] row : array) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("Result:");
        for (int[] row : transpose(array)) {
            System.out.println(Arrays.toString(row));
        }
    }

    static int[][] transpose(int[][] source) {
        int n_rows = source.length;
        int n_cols = source[0].length;
        int[][] result = new int[n_cols][n_rows];
        for (int row = 0; row < n_rows; row++) {
            for (int col = 0; col < n_cols; col++) {
                result[col][row] = source[row][col];
            }
        }
        return result;
    }
}
