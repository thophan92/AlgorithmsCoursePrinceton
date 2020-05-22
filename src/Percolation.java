import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF uf;
    private int size;
    private int count = 0;
    private boolean[][] grid;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n > 0) {
            uf = new WeightedQuickUnionUF((int) Math.pow(n, 2) + 2);
            size = n;
            grid = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    grid[i][j] = false; // default value
                }
            }
            for (int i = 1; i <= size; i++) {
                uf.union(0, i); // top
                uf.union((int) Math.pow(n, 2) + 1, n * (n-1) + i); // bottom
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            grid[row-1][col-1] = true;
            count++;
            if (isTop(row, col)) {
                if (isOpen(row - 1, col)) {
                    uf.union(toInt(row, col), toInt(row - 1, col));
                }
            }
            if (isBottom(row, col)) {
                if (isOpen(row + 1, col)) {
                    uf.union(toInt(row, col), toInt(row + 1, col));
                }
            }
            if (isLeft(row, col)) {
                if (isOpen(row, col -1)) {
                    uf.union(toInt(row, col), toInt(row, col - 1));
                }
            }
            if (isRight(row, col)) {
                if (isOpen(row, col+1)) {
                    uf.union(toInt(row, col), toInt(row, col + 1));
                }
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (isInGrid(row, col)) {
            return grid[row-1][col-1];
        }
        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return uf.find(0) == uf.find(toInt(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(0) == uf.find(size*size + 1);
    }

    private boolean isLeft(int row, int col) {
        return col > 1 && row >= 1 && row <= size;
    }

    private boolean isRight(int row, int col) {
        return col < size && row >= 1 && row <= size;
    }

    private boolean isTop(int row, int col) {
        return row > 1 && col >= 1 && col <= size;
    }

    private boolean isBottom(int row, int col) {
        return row < size && col >= 1 && col <= size;
    }

    private boolean isInGrid(int row, int col) {
        if (row >= 1 && row <= size && col >= 1 && col <= size) {
            return true;
        } else {
            throw new IllegalArgumentException("Out of Grid");
        }
    }

    private int toInt(int row, int col) {
        return (row - 1) * size + col;
    }

    // test client (optional)
    public static void main(String[] args) {
    }
}
