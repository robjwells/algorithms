package ch1.sec2.ex11;

public class SmartDate {
    private final static int[] monthLengths = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private final int month;
    private final int day;
    private final int year;

    SmartDate(int month, int day, int year) {
        if (!isValid(month, day, year)) {
            throw new IllegalDateException();
        }
        this.month = month;
        this.day = day;
        this.year = year;
    }

    private static boolean isValid(int month, int day, int year) {
        // dayInRange also validates the month is in range so no point calling twice
        return dayInRange(month, day, year);
    }

    private static boolean monthInRange(int month) {
        return month >= 1 && month <= 12;
    }

    private static boolean isLeapYear(int year) {
        return (year % 100 != 0 && year % 4 == 0) || (year % 100 == 0 && year % 400 == 0);
    }

    private static boolean dayInRange(int month, int day, int year) {
        if (month == 2 && isLeapYear(year)) {
            return day >= 1 && day <= 29;
        }
        // Check monthInRange here to be sure that accessing the monthLengths array is safe
        return (day >= 1 && monthInRange(month) && day <= monthLengths[month]);
    }
}
