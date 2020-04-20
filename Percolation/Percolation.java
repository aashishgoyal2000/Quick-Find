import edu.princeton.cs.algs4.In;   
import edu.princeton.cs.algs4.StdOut;   
import edu.princeton.cs.algs4.WeightedQuickUnionUF;   
public class Percolation  {   
    private boolean[] arr;   
    private final int n;   
    private final WeightedQuickUnionUF uf;   
    private int count = 0;   
    public Percolation(int n) {   
        if (n <= 0) throw new IllegalArgumentException("Invalid input : n must > 0 !");   
        this.n = n;   
        arr = new boolean[(this.n*this.n) + 2];   
        uf = new WeightedQuickUnionUF((this.n*this.n) + 2);   
        for (int i = 0; i < (this.n*this.n)+2; i++) {   
            arr[i] = false; 
        } 
    }
    public void open(int i, int j) { 
        int pos = (i * n) + j;
         if (!isOpen(i, j)) {
            count++;
           arr[pos] = true;
           if (i > 0) { 
             if (isOpen(i-1, j)) {
               uf.union(pos + 1, pos-n + 1);
             }
           } else { 
            uf.union(0, pos + 1);
           }           
          if (i < n-1) { 
             if (isOpen(i+1, j)) {
               uf.union(pos + 1, pos+n + 1);
             }
           } else { 
            uf.union(n*n+1, pos+1);
           }
          if (j > 0 && isOpen(i, j-1)) { 
             uf.union(pos + 1, pos-1 + 1);
           }
           
           if (j < n-1 && isOpen(i, j+1)) { 
             uf.union(pos + 1, pos+1 + 1);
           }
         } 
    } 
    public boolean isOpen(int row, int col) { 
        validate(row, col); 
        int pos = row * n + col;
        if (arr[pos]) {   
            return true;   
        } 
        return false;   
    } 
    public boolean isFull(int row, int col) {   
        validate(row, col); 
        int pos = row * n + col;
        if (arr[pos])  {   
            return true;   
        } 
        return false;   
    }  
    public int numberOfOpenSites() {   
        return count;   
    }  
    public boolean percolates() {   
        return uf.connected(0, n * n + 1);   
    } 
    public static void main(String[] args)  {  
        
        if (args.length != 2) {
            return;
        }
 
        In in = new In(args[0]);   
        int n = in.readInt();   
        Percolation per = new Percolation(n);   
        while (!per.percolates()) {   
            int row = in.readInt();   
            int col = in.readInt();   
            per.validate(row, col);   
            per.open(row, col);   
        } 
        if (per.percolates())  {   
            StdOut.println("percolates");   
        } else  {   
            StdOut.println("does not percolate");   
        } 
    } 
    private void validate(int row, int col)  {   
        if (row <= 0 || row >= n + 1)  {   
            throw new IllegalArgumentException("Invalid input : row index out of bounds !");   
        }
        if (col <= 0 || col >= n + 1)  {   
            throw new IllegalArgumentException("Invalid input : col index out of bounds !");   
        }
    } 
} 