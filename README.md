## Percolation:

A percolation system of N-by-N dimensions for a grid of sites is modelled within the java classes of the repository. Each site is either open or blocked and a full site is an open site that can be conntected to an open site in the top row through a chain of connecting open sites that are left, right, or below the said open site. A system only percolates if there is a full site in the bottom row; and most importantly, if all there is a series of full sites that lead from the top row to the bottom row. This system can be equates to a porous surface that is allowing for the passage of water via open sites.

## API:
```
public class Percolation {
   public Percolation(int N)               // create N-by-N grid, with all sites blocked
   public void open(int i, int j)          // open site (row i, column j) if it is not open already
   public boolean isOpen(int i, int j)     // is site (row i, column j) open?
   public boolean isFull(int i, int j)     // is site (row i, column j) full?
   public boolean percolates()             // does the system percolate?

   public static void main(String[] args)  // test client (optional)
}
```



## Percolation Stats:

To determine an approximate percolation threshold, a model for a system that has all sites initially blocked is initialized. The percolation threshold determination is approximated by determining, uniformly at random among all blocked sites, a site that will be opened (corresponding parameters to PercolationStats API is shown below - row i, column j) and the fractions of sites that are opened. The method described above will provide an estimationg of the ppercolation threshold. 

## API:
```
public class PercolationStats {
   public PercolationStats(int N, int T)     // perform T independent experiments on an N-by-N grid
   public double mean()                      // sample mean of percolation threshold
   public double stddev()                    // sample standard deviation of percolation threshold
   public double confidenceLo()              // low  endpoint of 95% confidence interval
   public double confidenceHi()              // high endpoint of 95% confidence interval

   public static void main(String[] args)    // test client (described below)
}
```

## Tests: 
```
% java PercolationStats 200 100
mean                    = 0.5929934999999997
stddev                  = 0.00876990421552567
95% confidence interval = 0.5912745987737567, 0.5947124012262428

% java PercolationStats 200 100
mean                    = 0.592877
stddev                  = 0.009990523717073799
95% confidence interval = 0.5909188573514536, 0.5948351426485464


% java PercolationStats 2 10000
mean                    = 0.666925
stddev                  = 0.11776536521033558
95% confidence interval = 0.6646167988418774, 0.6692332011581226

% java PercolationStats 2 100000
mean                    = 0.6669475
stddev                  = 0.11775205263262094
95% confidence interval = 0.666217665216461, 0.6676773347835391

