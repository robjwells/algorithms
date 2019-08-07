class Ex1_2_6 {
    public static void main(String[] args) {
        System.out.printf("Are ACTGACG and TGACGAC correctly determined\nto be circular shifts of each other?\n"
                + areCircularShifts("ACTGACG", "TGACGAC") + "\n");
    }

    public static boolean areCircularShifts(String a, String b) {
        return a.length() == b.length() && (a + a).contains(b);
    }
}
