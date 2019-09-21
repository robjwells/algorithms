package ch1.sec2.ex16;

import java.util.Objects;

/**
 * Ex 1.2.16
 * <p>
 * There are other considerations to consider when implementing a robust Rational class
 * that aren’t accounted for in the implementation below (because they aren’t specified
 * in the problem and for time reasons).
 * <p>
 * For instance, there is no consideration of zero-handling (x/0 should be invalid) or
 * normalising negative denominators (2/-5 should be -2/5), or parsing from a string.
 */
public class Rational {
    private long numerator;
    private long denominator;

    public Rational(long numerator, long denominator) {
        long divisor = gcd(numerator, denominator);
        this.numerator = numerator / divisor;
        this.denominator = denominator / divisor;
    }

    /**
     * Find the greatest common divisor of <code>a</code> and <code>b</code> recursively.
     * <p>
     * Using a recursive version of Euclid’s algorithm.
     *
     * @param a a number
     * @param b another number
     * @return the greatest common divisor of <code>a</code> and <code>b</code>
     */
    protected static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        // The below largely taken from my PoPII Fraction project
        Rational half = new Rational(37, 74);
        System.out.format("Rational `half` has the value %s.\n \n", half);

        Rational threeQuarters = new Rational(3, 4);
        Rational fiveQuarters = half.plus(threeQuarters);
        System.out.format(
                "Adding %s to %s gives the value %s.\n \n",
                threeQuarters,
                half,
                fiveQuarters
        );

        Rational two = new Rational(2, 1);
        System.out.format(
                "Subtracting %s from %s gives %s, ",
                two,
                threeQuarters,
                threeQuarters.minus(two)
        );

        System.out.format(
                "Dividing %s by %s gives %s. Multiplying the result by %s gives us %s again.\n \n",
                two,
                threeQuarters,
                two.divides(threeQuarters),
                threeQuarters,
                two.divides(threeQuarters).times(threeQuarters)
        );

        System.out.format(
                "Is %s multiplied by %s equal to %s? %s\n \n",
                half,
                two,
                new Rational(1, 1),
                new Rational(1, 1).equals(half.times(two))
        );
    }

    public Rational plus(Rational other) {
        return new Rational(
                this.numerator * other.denominator + other.numerator * this.denominator,
                this.denominator * other.denominator
        );
    }

    public Rational minus(Rational other) {
        return plus(new Rational(-other.numerator, other.denominator));
    }

    public Rational times(Rational other) {
        return new Rational(
                this.numerator * other.numerator,
                this.denominator * other.denominator
        );
    }

    public Rational divides(Rational other) {
        return new Rational(
                this.numerator * other.denominator,
                this.denominator * other.numerator
        );
    }

    @Override
    public String toString() {
        return String.format("%d/%d", numerator, denominator);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rational rational = (Rational) o;
        // Can compare the numbers directly as the constructor ensures
        // that rationals are always kept in their lowest form.
        return numerator == rational.numerator &&
                denominator == rational.denominator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }
}
