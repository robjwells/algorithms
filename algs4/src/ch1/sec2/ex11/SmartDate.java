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

    /**
     * Returns the date’s English weekday name as a string, computed using Gauss’s algorithm.
     * <p>
     * For Exercise 1.2.12.
     * <p>
     * I don’t have a copy of Calendrical Calculations, so I’ve found this algorithm on the web.
     * Beware using the algorithm currently on Wikipedia (2019-09-19) as it’s, at best, very
     * poorly explained and I found it impossible to get correct. The one below is taken
     * from the Calendar Wiki site.
     *
     * @return the English name of the day of the week
     * @see <a href="https://calendars.wikia.org/wiki/Calculating_the_day_of_the_week">Calendar Wiki</a>
     */
    public String dayOfWeek() {
        // Days numbered 0-6, Sunday-Saturday
        String[] names = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        int Y = month < 3 ? year - 1 : year;
        int d = day;
        int m = Math.floorMod((month + 9), 12) + 1;
        int y = Y % 100;
        int c = Y / 100;

        int w = Math.floorMod(
                (d + ((Double) Math.floor(2.6 * m - 0.2)).intValue() + y +
                        Math.floorDiv(y, 4) + Math.floorDiv(c, 4) - 2 * c),
                7
        );
        return names[w];
    }
}
