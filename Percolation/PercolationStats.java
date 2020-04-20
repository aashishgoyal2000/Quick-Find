import edu.princeton.cs.algs4.StdRandom; 
import edu.princeton.cs.algs4.StdOut; 
import edu.princeton.cs.algs4.StdStats; 

public class PercolationStats  { 

    private final double[] arr;
    private final double trial; 

    // perfor m independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {  
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException("Invalid input : n or trials musu > 0 !"); 
        trial = trials; 
        arr = new double[trials]; 
        for (int i = 0;  i < trials;  i++)  {  
            Percolation per = new Percolation(n); 
            while (!per.percolates()) {  
                int row = StdRandom.uniform(1, n + 1); 
                int col = StdRandom.uniform(1, n + 1); 
                if (row < 0 || row > n || col < 0 || col > n)  { 
                    throw new IllegalArgumentException(); 
                } 
                per.open(row, col); 
            } 
            arr[i] = (per.numberOfOpenSites() * 1.0 / (n*n)); 
        } 
    } 
 
     // sample mean of percolation threshold
    public double mean()  {  
        return StdStats.mean(arr); 
    } 

    // sample standard deviation of percolation threshold
    public double stddev()  {  
        return StdStats.stddev(arr); 
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo()  {  
        double confidence1 = mean() - (1.96 * stddev()) / Math.sqrt(trial); 
        return confidence1; 
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi()  {  
        return mean() + (1.96 * stddev()) / Math.sqrt(trial); 
    }
    public static void main(String[] args)  {  
        int n = Integer.parseInt(args[0]); 
        int trial = Integer.parseInt(args[1]); 
        PercolationStats ss = new PercolationStats(n, trial); 
        StdOut.println("mean                    = " + ss.mean()); 
        StdOut.println("stddev                  = " + ss.stddev()); 
        StdOut.println("95% confidence interval = " + ss.confidenceLo() + ", " + ss.confidenceHi()); 

    }
}      