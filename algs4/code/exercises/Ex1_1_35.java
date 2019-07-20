import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

class Ex1_1_35 {
    private static int SIDES = 6;
    private static int ARRAYLENGTH = 2 * SIDES + 1;
    private static double COMBINATIONS = Math.pow(SIDES, 2);

    /**
     * After conducting 10,000 simulations (taking 1 hour), the average number
     * of n-dice-throws needed for the observations to match the calculated
     * probabilities to three decimal places was 3,652,024.
     */
    public static void main(String[] args) {
        int simulationsToRun = Integer.parseInt(args[0]);
        double[] probabilities = exactProbabilities();
        long totalTrials = 0;
        for (int i = 0; i < simulationsToRun; i++) {
            int trialsNeededForAccuracy = simulateToAccuracy(probabilities, 3);
            totalTrials += trialsNeededForAccuracy;
        }
        StdOut.printf(
                "After %s simulations, average trials needed for 3-place accuracy: %,d\n",
                simulationsToRun,
                totalTrials / simulationsToRun
        );
    }

    static boolean checkAccuracy(int toPlaces, double[] reference, double[] candidate) {
        double scale = Math.pow(10, toPlaces);
        // StdOut.println((int) (reference[3] * scale));
        for (int idx = 0; idx < reference.length; idx++) {
            if ((int) (reference[idx] * scale) != (int) (candidate[idx] * scale)) {
                return false;
            }
        }
        return true;
    }

    static double[] makePercentageArray(int[] observations, int trials) {
        double[] percentages = new double[observations.length];
        for (int i = 0; i < observations.length; i++) {
            percentages[i] = observations[i] / (double) trials;
        }
        return percentages;
    }

    static void updatePercentagesArray(int[] observations, int trials, double[] percentages) {
        for (int i = 0; i < observations.length; i++) {
            percentages[i] = observations[i] / (double) trials;
        }
    }

    static int simulateToAccuracy(double[] reference, int accuracy) {
        double[] probabilities = reference;
        int[] observations = new int[ARRAYLENGTH];
        double[] percentages = new double[ARRAYLENGTH];
        int trials = 0;
        while (!checkAccuracy(accuracy, probabilities, percentages)) {
            trials++;
            int throwSum = randomThrow() + randomThrow();
            observations[throwSum]++;
            updatePercentagesArray(observations, trials, percentages);
        }
        return trials;
    }

    /**
     * Return a random dice throw [1,SIDES]
     */
    static int randomThrow() {
        return StdRandom.uniform(1, SIDES + 1);
    }

    /**
     * Return double[] that contains the percentage that each dice sum was observed
     * after `times` simulated throws.
     */
    static double[] simulate(int times) {
        int[] observations = new int[ARRAYLENGTH];
        for (int t = 0; t < times; t++) {
            int throwSum = randomThrow() + randomThrow();
            observations[throwSum]++;
        }
        double[] percentages = new double[ARRAYLENGTH];
        for (int i = 2; i < ARRAYLENGTH; i++) {
            percentages[i] = observations[i] / (double) times;
        }
        return percentages;
    }

    static double[] exactProbabilities() {
        int[] frequencies = new int[ARRAYLENGTH];
        for (int i = 1; i <= SIDES; i++) {
            for (int j = 1; j <= SIDES; j++) {
                frequencies[i + j]++;
            }
        }
        double[] probabilities = new double[ARRAYLENGTH];
        for (int k = 2; k <= 2 * SIDES; k++) {
            probabilities[k] = frequencies[k] / COMBINATIONS;
        }
        return probabilities;
    }
}
