/****************************************************************************
  * Percolation.java model a percolation system using N-by_N grid of sides
  * Dependencies: StdIn.java StdOut.java WeightedQuickUnionUF.java
  * made by j.z. on 10/13/2015
  */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.In;

public class Percolation {
  
   private WeightedQuickUnionUF uf;
   private boolean[] opensites;                            // keep track of open sides
   private int size;     
   public Percolation(int N) {               // create N-by-N grid, with all sites blocked
      if (N <= 0)
          throw new java.lang.IllegalArgumentException();
      size = N;
      opensites = new boolean[N * N];      // true is open site
       for (int i = 0; i < N * N; i++) {
           opensites[i] = false;
       }
       
       uf = new WeightedQuickUnionUF(N * N + 2); // add 1 virtual sites at top and bottom each
    }
   
   public void open(int i, int j) {          // open site (row i, column j) if it is not open already
                                            // note index need to subduct 1
   if (i < 1 || i > size || j < 1 || j > size)
       throw new java.lang.IndexOutOfBoundsException();
    
   int siteIndex = (i - 1) * size + (j - 1);
   
   if (opensites[siteIndex])
       return;
   opensites[siteIndex] = true;
   
    
   /***union adjacent open sites: left, right, up and down****/
     //connect up
    if (i == 1)                       // all top opened sites root to virtual site 0
       uf.union(siteIndex + 1, 0);
    
    if (i == size)                // all bottom opened sites root to virtual site N*N + 2 
        uf.union(siteIndex + 1, size * size + 1);
    
    //connect up
    if (i > 1 && isOpen(i - 1, j))
       uf.union(siteIndex + 1, siteIndex + 1 - size);
    
    //connect down
     if (i < size && isOpen(i + 1, j))
       uf.union(siteIndex + 1, siteIndex + 1 + size); 
     
    //connect left
   if (j > 1 && isOpen(i, j - 1))
       uf.union(siteIndex + 1, siteIndex);
       
   //connect right
   if (j < size && isOpen(i, j + 1))
       uf.union(siteIndex + 1, siteIndex + 2);
            
   }
   
   public boolean isOpen(int i, int j) {     // is site (row i, column j) open?

   if (i < 1 || i > size  || j < 1 || j > size)
      throw new java.lang.IndexOutOfBoundsException();
   return (opensites[(i - 1) * size + j - 1]);
   }
   
   public boolean isFull(int i, int j) {     // is site (row i, column j) full?
   if (i < 1 || i > size || j < 1 || j > size)
       throw new java.lang.IndexOutOfBoundsException();
   
    return uf.connected((i - 1) * size + j, 0);
   }
   
   public boolean percolates() {            // does the system percolate?
   if (size == 1)
      return (opensites[0]);
   
    return uf.connected(0, size * size + 1);
   }
   
    public static void main(String[] args) {
        In in = new In(args[0]);      // input file
        int N = in.readInt();         // N-by-N percolation system
        Percolation perco = new Percolation(N);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            if (!perco.isOpen(i, j)) {
            perco.open(i, j);
            }
         }
             
   }
}
