package PartOne.Assignment.Week3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private final int numberOfSegments;
    private final ArrayList<LineSegment> segments;
    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        Point[] arr = points.clone();
        validate(arr);
        int n = arr.length;
        segments = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (arr[i].slopeOrder().compare(arr[j], arr[k]) == 0) {
                        for (int m = k + 1; m < n; m++) {
                            if (arr[i].slopeOrder().compare(arr[j], arr[m]) == 0) {
                                Point[] line = new Point[4];
                                line[0] = arr[i];
                                line[1] = arr[j];
                                line[2] = arr[k];
                                line[3] = arr[m];
                                Arrays.sort(line);
                                LineSegment lineSegment = new LineSegment(line[0], line[3]);
                                segments.add(lineSegment);
                            }
                        }
                    }
                }
            }
        }
        numberOfSegments = segments.size();
    }

    private void validate(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        if (points.length < 2) {
            if (points[0] == null) throw new IllegalArgumentException();
        } else {
            if (points[0] == null) throw new IllegalArgumentException();
            for (int i = 0; i < points.length; i++) {
                if (points[i] == null) throw new IllegalArgumentException();
            }
        }
        Arrays.sort(points);
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i+1]) == 0) throw new IllegalArgumentException();
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return numberOfSegments;
    }

    // the line segments
    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[0]);
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}