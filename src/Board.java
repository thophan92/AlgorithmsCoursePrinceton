import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Board {
    private final int[][] clone;
    private final int dim;
    private final int manhattan;
    private final int hamming;
    private final String toString;
    private int zeroRow;
    private int zeroCol;
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        clone = Arrays.stream(tiles).map(int[]::clone).toArray(int[][]::new);
        dim = tiles.length;
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        int man = 0;
        stringBuilder.append(dim);
        for (int i = 0; i < dim; i++) {
            stringBuilder.append("\n");
            for (int j = 0; j < dim; j++) {
                stringBuilder.append(" ").append(clone[i][j]);
                man += manhattanPoint(i, j);
                if (clone[i][j] != 0 && clone[i][j] != toNumber(i, j)) {
                    count++;
                }
                if (clone[i][j] == 0) {
                    zeroRow = i;
                    zeroCol = j;
                }
            }
        }
        toString = stringBuilder.toString();
        hamming = count;
        manhattan = man;
    }

    // string representation of this board
    public String toString() {
        return toString;
    }

    // board dimension n
    public int dimension() {
        return dim;
    }

    private int toNumber(int row, int col) {
        return row * dim + col + 1;
    }

    private int manhattanPoint(int i, int j) {
        if (clone[i][j] == 0) return 0;
        int row = (clone[i][j] - 1) / dim;
        int col = (clone[i][j] - 1) % dim;
        return Math.abs(row - i) + Math.abs(col - j);
    }

    // number of tiles out of place
    public int hamming() {
        return hamming;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return manhattan == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (!y.getClass().equals(this.getClass())) return false;
        Board that = (Board) y;
        if (this.dimension() != that.dimension()) return false;
        if (dim > 0) {
            for (int i = 0; i < dim; i++) {
                for (int j = 0; j < dim; j++) {
                    if (that.clone[i] == null || that.clone[i][j] != this.clone[i][j]) return false;
                }
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Iterable<Board> iterable = new Iterable<Board>() {
            @Override
            public Iterator<Board> iterator() {
                return new BoardIterator();
            }
        };
        return iterable;
    }

    private class Point {
        private final int row;
        private final int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }

    private ArrayList<Point> getNeighbor() {
        ArrayList<Point> points = new ArrayList<Point>();
        if (zeroCol < dim - 1) {
            points.add(new Point(zeroRow, zeroCol + 1));
        }
        if (zeroCol > 0) {
            points.add(new Point(zeroRow, zeroCol - 1));
        }
        if (zeroRow < dim - 1) {
            points.add(new Point(zeroRow + 1, zeroCol));
        }
        if (zeroRow > 0) {
            points.add(new Point(zeroRow - 1, zeroCol));
        }
        return points;
    }

    private class BoardIterator implements Iterator<Board> {
        int current = 0;
        ArrayList<Point> neighborPoints = getNeighbor();
        @Override
        public boolean hasNext() {
            return current < neighborPoints.size();
        }

        @Override
        public Board next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Board board = exchange(zeroRow, zeroCol, neighborPoints.get(current).getRow(), neighborPoints.get(current).getCol());
            current++;
            return board;
        }
    }

    private Board exchange(int oriRow, int oriCol, int nexRow, int nexCol) {
        int[][] newClone = Arrays.stream(clone).map(int[]::clone).toArray(int[][]::new);
        int temp = newClone[oriRow][oriCol];
        newClone[oriRow][oriCol] = newClone[nexRow][nexCol];
        newClone[nexRow][nexCol] = temp;
        return new Board(newClone);
    }
    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        Board board = exchange(StdRandom.uniform(dim), StdRandom.uniform(dim), StdRandom.uniform(dim), StdRandom.uniform(dim));
        return board;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        // read in the board specified in the filename
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tiles[i][j] = in.readInt();
            }
        }
        Board board = new Board(tiles);
        System.out.println(board.toString());
        System.out.println("Dimension:  " + board.dimension());
        System.out.println("Hamming:    " + board.hamming());
        System.out.println("Manhattan:  " + board.manhattan());
        System.out.println("Is Goal:    " + board.isGoal());
        System.out.println("Equals:     " + board.equals(board));
        System.out.println("Neighbors:  ");
        Iterator<Board> iterator = board.neighbors().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
        System.out.println("Twin:      ");
        System.out.println(board.twin());
    }
}
