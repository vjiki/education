import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.UF;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

  WeightedQuickUnionUF uf;
  private final boolean[] openStat;   // saves the state of the site
  private final int size;
  private final int gridSideSize;
  private int openSitesSize = 0;

  // creates n-by-n grid, with all sites initially blocked
  // Performance requirements:
  // The constructor must take time proportional to n2;
  // all instance methods must take constant time plus a constant number of calls to union() and find().
  public Percolation(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException();
    }
    gridSideSize = n;
    size = n * n + 2;
    uf = new WeightedQuickUnionUF(size);
    openStat = new boolean[size];
    openStat[0] = true; // TODO
    openStat[size-1] = true; // TODO
    for (int i = 1; i <= n; i++) {
      uf.union(0, i);
      uf.union(size - 1, size-i-1);
    }
  }

  // test client (optional)
  public static void main(String[] args) {

//    if (args.length == 2) {
//      StdOut.println("Hello " + args[0] + " and " + args[1] + ".");
//      StdOut.println("Goodbye " + args[1] + " and " + args[0] + ".");
//    }
    int n = StdIn.readInt();
    Percolation percolation = new Percolation(n);
//    while (!StdIn.isEmpty()) {
      int first = StdIn.readInt();
      int second = StdIn.readInt();
//      if (!percolation.isOpen(first, second)) {
//        continue;
//      }
      percolation.open(first, second);
      StdOut.println("isOpen " + percolation.isOpen(first, second));
      boolean per = percolation.percolates();
      StdOut.println("isPercolates " + per);
//    }
    StdOut.println(percolation.numberOfOpenSites() + " components");
  }

  private void checkIfRowColInRange(int row, int col) {
    if (row <= 0 || row > gridSideSize || col <= 0 || col > gridSideSize) {
      throw new IllegalArgumentException();
    }
  }

  // opens the site (row, col) if it is not open already
  public void open(int row, int col) {
    checkIfRowColInRange(row, col);
    int unit = (row-1) * gridSideSize + col;
    openStat[unit] = true;
    openSitesSize++;

    int left = unit - 1;
    if (col-1 > 0 && openStat[left]) {
      uf.union(unit, left);
    }
    int right = unit + 1;
    if (col + 1 <= gridSideSize && openStat[right]) {
      uf.union(unit, right);
    }
    int top = row * gridSideSize + col;
    if (row <= gridSideSize && openStat[top]) {
      uf.union(unit, top);
    }
    int bottom = (row - 2) * gridSideSize + col;
    if (row-2 > 0 && openStat[bottom]) {
      uf.union(unit, bottom);
    }
  }

  // is the site (row, col) open?
  public boolean isOpen(int row, int col) {
    checkIfRowColInRange(row, col);
    return openStat[(row-1) * gridSideSize + col];
  }

  // is the site (row, col) full? // TODO: what it means?
  // A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites.
  public boolean isFull(int row, int col) {
    checkIfRowColInRange(row, col);
    return uf.find((row-1) * gridSideSize + col) == uf.find(size - 1);
  }

  // returns the number of open sites
  public int numberOfOpenSites() {
      return openSitesSize;
  }

  // does the system percolate?
  public boolean percolates() {
    return uf.find(0) == uf.find(size - 1);
  }
}