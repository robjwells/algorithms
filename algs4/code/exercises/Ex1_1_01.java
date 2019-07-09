class Ex1_1_1 {
    public static void main(String[] args) {
        assert (0 + 15) / 2 == 7;
        assert 2.0e-6 * 100000000.1 == 200.0000002;
        //              ^-------^ 100M, 10^8
        assert (true && false || true & true);
    }
}
