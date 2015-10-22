/****************************************************************************
  * PercolationStats.java perform a series of computational experiments
  * to determine the percolation threshold
  * Dependencies: StdIn.java StdOut.java WeightedQuickUnionUF.java
  * made by j.z. on 10/13/2015
  */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private Percolation perco;
    private double[] threshold;
    private double times;
    public PercolationStats(int N, int T){     // perform T independent experiments on an N-by-N grid
        
        if (N <= 0 || T <= 0)
            throw new java.lang.IllegalArgumentException();
   
        threshold = new double[T];
        times     = (double) T;   
    
  //  perform independent experiments
        for (int tm = 0; tm < T; tm++) {
                perco = new Percolation(N);
            int count = 0;    //counting opened cells, initially all closed
           
         while (!perco.percolates()) {
           int i = StdRandom.uniform(1, N+1);   //random number is from 1 to N
           int j = StdRandom.uniform(1, N+1);
           if (!perco.isOpen(i, j)) {
             perco.open(i, j);
             count = count + 1;
             }
          }
            threshold[tm] = (double) count / (N * N);
        }
    }
    
    public double mean() {                      // sample mean of percolation threshold
      return StdStats.mean(threshold);
    }
    
    public double stddev() {                    // sample standard deviation of percolation threshold
     return StdStats.stddev(threshold);
    }
    
    public double confidenceLo() {             // low  endpoint of 95% confidence interval
     double confLo = mean() - 1.96 * stddev() / Math.sqrt(times);
     return confLo;
    }
    
    public double confidenceHi() {              // high endpoint of 95% confidence interval
     double confHi = mean() + 1.96 * stddev() / Math.sqrt(times);
     return confHi;
    }
    
    public static void main(String[] args) {    // test client (described below)
        int N, T;
        if (args.length == 2) {
        N = Integer.parseInt(args[0]);
        T = Integer.parseInt(args[1]);
        }
        else {
        StdOut.println("no command line input using default values N = 200, T = 100");
        N = 200;
        T = 100;
        }
        PercolationStats PerStat = new PercolationStats(N, T);
        StdOut.println("mean                        = " + PerStat.mean());
        StdOut.println("stddev                      = " + PerStat.stddev());
        StdOut.println("95% confidence interval     = " + PerStat.confidenceLo()  + ",  "  + PerStat.confidenceHi());
    }
}
