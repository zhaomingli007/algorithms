import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Created by zhaomingli on 14/7/17.
 */
public class PercolationStats {

    private Percolation percolation = null;
    private int trials = 0;
    private int matrixSize = 0;

    private double[] percolationNumbers;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        this.trials = trials;
        this.matrixSize = n;
        percolationNumbers = new double[trials];

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(percolationNumbers);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(percolationNumbers);

    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(trials);
    }


    private void run() {
        for (int t = 0; t < trials; t++) {
            percolation = new Percolation(matrixSize);
            while (!percolation.percolates()) {
                int a = StdRandom.uniform(1, matrixSize + 1);
                int b = StdRandom.uniform(1, matrixSize + 1);
                if (!percolation.isOpen(a, b)) {

                    percolation.open(a, b);
                }
            }

            double numberOfOpenSites = percolation.numberOfOpenSites() * 1.000 / (matrixSize * matrixSize);
            StdOut.println("numberOfOpenSites " + numberOfOpenSites);
            percolationNumbers[t] = numberOfOpenSites;
        }


    }

    // test client (described below)
    public static void main(String[] args) {
        int n = Integer.valueOf(args[0]);
        int t = Integer.valueOf(args[1]);
        if (n <= 0 || t <= 0) {
            throw new IllegalArgumentException("invalid arguments exceptions: n: " + n + ", t: " + t);
        }
        PercolationStats percolationStats = new PercolationStats(n, t);
        percolationStats.run();

        StdOut.println("mean                    = " + percolationStats.mean());
        StdOut.println("stddev                  = " + percolationStats.stddev());
        StdOut.println("95% confidence interval = [" + percolationStats.confidenceLo() + "," + percolationStats.confidenceHi() + "]");
    }
}
