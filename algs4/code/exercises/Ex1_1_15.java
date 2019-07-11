import java.util.Arrays;

class Ex1_1_15 {
    public static void main(String[] args) {
        int[] values = {1, 1, 3, 1, 4, 3, 2, 1};
        int[] expected = {0, 4, 1, 2, 1};
        int m = 5;
        assert Arrays.equals(histogram(values, m), expected);

        int[] outOfRange = {-5, 20, 100, 200, -50, 15};
        assert Arrays.stream(histogram(outOfRange, m)).sum() == 0;
    }

    static int[] histogram(int[] values, int m) {
        int[] result = new int[m];
        for (int value : values) {
            if (value >= 0 && value <= m - 1) {
                result[value]++;
            }
        }
        return result;
    }
}
