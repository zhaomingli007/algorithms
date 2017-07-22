import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by zhaomingli on 14/7/17.
 */
public class Percolation {

    private int virtualTop, virtualBottom;
    private int maxtrixWidth;
    private int numberOfOpenSite = 0;
    private WeightedQuickUnionUF wightedQuickUnionUF = null;
    private int[] open = null;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        wightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2);
        //Initialize matrix and virtual site.
        virtualTop = n * n;
        virtualBottom = n * n + 1;
        //Default all site are block
        open = new int[n * n];
        maxtrixWidth = n;
        for (int i = 1; i <= n; i++) {
            wightedQuickUnionUF.union(idx(1, i), virtualTop);
            wightedQuickUnionUF.union(idx(n, i), virtualBottom);
        }
    }

    private int idx(int row, int col) {

        return maxtrixWidth * (row - 1) + col - 1;
    }


    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || row > maxtrixWidth || col <= 0 || col > maxtrixWidth)
            throw new IndexOutOfBoundsException("row index i out of bounds");
        if (isOpen(row, col)) return;
        open[idx(row, col)] = 1;
        if (col - 1 >= 1 && isOpen(row, col - 1)) {
            wightedQuickUnionUF.union(idx(row, col), idx(row, col - 1));
        }
        if (col + 1 <= maxtrixWidth && isOpen(row, col + 1)) {
            wightedQuickUnionUF.union(idx(row, col), idx(row, col + 1));
        }
        if (row - 1 >= 1 && isOpen(row - 1, col)) {
            wightedQuickUnionUF.union(idx(row, col), idx(row - 1, col));
        }
        if (row + 1 <= maxtrixWidth && isOpen(row + 1, col)) {
            wightedQuickUnionUF.union(idx(row, col), idx(row + 1, col));
        }
        numberOfOpenSite++;
    }


    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || row > maxtrixWidth || col <= 0 || col > maxtrixWidth)
            throw new IndexOutOfBoundsException("row index i out of bounds");
        return open[idx(row, col)] != 0;
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0 || row > maxtrixWidth || col <= 0 || col > maxtrixWidth)
            throw new IndexOutOfBoundsException("row index i out of bounds");
        return isOpen(row, col) && wightedQuickUnionUF.connected(virtualTop, idx(row, col));
    }


    // number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSite;
    }

    // does the system percolate?
    public boolean percolates() {
        return wightedQuickUnionUF.connected(virtualTop, virtualBottom);
    }

}
