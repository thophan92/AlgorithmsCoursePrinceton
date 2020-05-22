import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private int size;
    private double[] times;
    private int count;
    private int random;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n > 0 && trials > 0) {
            size = n;
            times = new double[trials];
            count = 0;
            Percolation per = new Percolation(n);
            for (int i = 0; i < trials; i++) {
                while (!per.percolates()) {
                    random = StdRandom.uniform(1, n*n + 1);
                    while (!per.isOpen(toRow(random), toCol(random))) {
                        random = StdRandom.uniform(n*n) + 1;
                    }
                    per.open(toRow(random), toCol(random));
                    count++;
                }
                times[i] = (double) count /  (double) (n * n);
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
        return mean() - CONFIDENCE_95 * stddev() / times.length;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + CONFIDENCE_95 * stddev() / times.length;
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
    // test client (see below)
    public static void main(String[] args) {
        System.out.println("for testing");
    }

}
