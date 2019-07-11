class Ex1_1_14 {
    public static void main(String[] args) {
        assert lg(0) == 0;
        assert lg(1) == 0;
        assert lg(2) == 1;
        assert lg(4) == 2;
        assert lg(32) == 5;
        assert lg(33) == 5;
        assert lg(1023) == 9;
        assert lg(1024) == 10;
        assert lg(1025) == 10;
    }

    static int lg(int n) {
        int result = 0;
        while (n >= 2) {
            result++;
            n /= 2;
        }
        return result;
    }
}
