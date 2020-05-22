import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private int size;
    private double[] times;
    private int numberOfExperiments;
    private int randomNumber;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n > 0 && trials > 0) {
            size = n;
            times = new double[trials];
            numberOfExperiments = trials;
            int count = 0;
            Percolation per = new Percolation(n);
            for (int i = 0; i < trials; i++) {
                while (!per.percolates()) {
                    randomNumber = StdRandom.uniform(1, n*n + 1);
                    while (!per.isOpen(toRow(randomNumber), toCol(randomNumber))) {
                        randomNumber = StdRandom.uniform(n*n) + 1;
                    }
                    per.open(toRow(randomNumber), toCol(randomNumber));
                    count++;
                }
                double fraction = (double) count / (n * n);
                times[i] = fraction;
            }
        } else {
            throw new IllegalArgumentException("wrong input");
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(times);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(times);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - CONFIDENCE_95 * stddev() / Math.sqrt(numberOfExperiments);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + CONFIDENCE_95 * stddev() / Math.sqrt(numberOfExperiments);
    }

    private int toRow(int num) {
        if (num % size == 0) {
            return num / size;
        } else {
            return num / size + 1;
        }
    }

    private int toCol(int random) {
        return random % size + 1;
    }

    // test client (optional)
    public static void main(String[] args) {
        PercolationStats stats = new PercolationStats(3, 4);
        stats.mean();
    }
}
