import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[] percolationGrid;
    private int size;
    private WeightedQuickUnionUF unionFind;
    private WeightedQuickUnionUF weightedUnionFind;
    private int topIndex;
    private int bottomIndex;
    private int n;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Input must be greater or equal to 0");
        }

        this.n = n;
        this.percolationGrid = new boolean[n * n + 1];
        this.unionFind = new WeightedQuickUnionUF(n * n + 2);
        this.weightedUnionFind = new WeightedQuickUnionUF(n * n + 1);
        this.percolationGrid[0] = true;
        this.size = n;
        this.topIndex = 0;
        this.bottomIndex = n*n+1;

        for (int i = 1; i < (n * n + 1); i++) {
                this.percolationGrid[i] = false;
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || row > this.size) {
            throw new IllegalArgumentException("Passed value must be positive and less than the size of array");
        }

        if (col <= 0 || col > this.size) {
            throw new IllegalArgumentException("Passed value must be positive and less than the size of array");
        }
        int siteLocation = ((row-1) * this.n + col);
        this.percolationGrid[siteLocation] = true;

        if (row < size && isOpen(row+1, col)) {
            this.weightedUnionFind.union(row-1, col);
            this.unionFind.union(row-1, col);
        }

        if (row > 1 && isOpen(row-1, col)) {
            this.weightedUnionFind.union(row-1, col);
            this.unionFind.union(row-1, col);
        }

        if (col < size && isOpen(row, col+1)) {
            this.weightedUnionFind.union(row-1, col+1);
            this.unionFind.union(row, col+1);
        }

        if (col > 1 && isOpen(row, col-1)) {
            this.weightedUnionFind.union(row, col-1);
            this.unionFind.union(row, col-1);
        }

        if (row == 1) {
            unionFind.union(siteLocation, 0);
            weightedUnionFind.union(siteLocation, 0);
        }

        if (row == size) {
            unionFind.union(siteLocation, size * size + 1);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || row >= this.size) {
            throw new IllegalArgumentException("Input must be in range of Percolation Grid dimensions");
        }

        if (col <= 0 || col > this.size) {
            throw new IllegalArgumentException("Passed value must be positive and less than the size of array");
        }

        int siteLocation = ((row-1) * this.n + col);
        return this.percolationGrid[siteLocation];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0 || row >= this.size) {
            throw new IllegalArgumentException("Input must be in range of Percolation Grid dimensions");
        }

        if (col <= 0 || col > this.size) {
            throw new IllegalArgumentException("Passed value must be positive and less than the size of array");
        }

        int siteLocation = ((row-1) * this.n + col);
        return this.weightedUnionFind.find(siteLocation) == this.weightedUnionFind.find(topIndex);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i < n*n+1; i++) {
            if (this.percolationGrid[i]) {
                count++;
            }
        }
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return this.weightedUnionFind.find(topIndex) == this.weightedUnionFind.find(bottomIndex);
    }


    // test client (optional)
    public static void main(String[] args) {

    }
}
