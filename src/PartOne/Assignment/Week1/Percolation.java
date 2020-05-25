package PartOne.Assignment.Week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final int TOP = 0;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF fullness;
    private int size;
    private int count = 0;
    private int bottom;
    private boolean[][] grid;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n > 0) {
            uf = new WeightedQuickUnionUF((int) Math.pow(n, 2) + 2);
            fullness = new WeightedQuickUnionUF((int) Math.pow(n, 2) + 1);
            size = n;
            grid = new boolean[n][n];
            bottom = n * n + 1;
        } else {
            throw new IllegalArgumentException();
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!isInGrid(row, col)) {
            throw new IllegalArgumentException();
        }
        if (!grid[row-1][col-1]) {
            grid[row-1][col-1] = true;
            count++;
            int pos = toInt(row, col);
            if (row == 1) {
                uf.union(pos, TOP);
                fullness.union(pos, TOP);
            }
            if (row == size) {
                uf.union(pos, bottom);
            }
            if (isTop(row, col)) {
                if (isOpen(row - 1, col)) {
                    int nextPos = toInt(row - 1, col);
                    uf.union(pos, nextPos);
                    fullness.union(pos, nextPos);
                }
            }
            if (isBottom(row, col)) {
                if (isOpen(row + 1, col)) {
                    int nextPos = toInt(row + 1, col);
                    uf.union(pos, nextPos);
                    fullness.union(pos, nextPos);
                }
            }
            if (isLeft(row, col)) {
                if (isOpen(row, col -1)) {
                    int nextPos = toInt(row, col - 1);
                    uf.union(pos, nextPos);
                    fullness.union(pos, nextPos);
                }
            }
            if (isRight(row, col)) {
                if (isOpen(row, col+1)) {
                    int nextPos = toInt(row, col + 1);
                    uf.union(pos, nextPos);
                    fullness.union(pos, nextPos);
                }
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!isInGrid(row, col)) {
            throw new IllegalArgumentException();
        }
        return grid[row-1][col-1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!isInGrid(row, col)) {
            throw new IllegalArgumentException("out of grid");
        }
        return fullness.find(0) == fullness.find(toInt(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(TOP) == uf.find(bottom);
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
        return row >= 1 && row <= size && col >= 1 && col <= size;
    }

    private int toInt(int row, int col) {
        return (row - 1) * size + col;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation per = new Percolation(10);
        per.isFull(-1, 5);
    }
}
