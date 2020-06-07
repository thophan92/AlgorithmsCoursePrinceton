package PartOne.Assignment.Week3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private final int numberOfSegments;
    private final ArrayList<LineSegment> segments;
    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        Point[] arr = points.clone();
        validate(arr);
        segments = new ArrayList<>();
        int n = arr.length;
        for (int i = 0; i < n - 3; i++) {
            Arrays.sort(arr);
            Arrays.sort(arr, arr[i].slopeOrder());
            for (int p = 0, first = 1, last = 2; last < n; last++) {
                while (last < n && Double.compare(arr[p].slopeTo(arr[first]), arr[p].slopeTo(arr[last])) == 0) {
                    last++;
                }
                if (last - first >= 3 && arr[p].compareTo(arr[first]) < 0) {
                    segments.add(new LineSegment(arr[p], arr[last - 1]));
                }
                first = last;
            }
        }
        numberOfSegments = segments.size();
    }

//    private ArrayList<Integer> getAllLines(PartOne.Assignment.Week3.Point[] points, PartOne.Assignment.Week3.Point point) {
//        if (points.length < 4) return null;
//        ArrayList<Integer> ans = new ArrayList<>();
//        int min = 1;
//        double minSlope = point.slopeTo(points[1]);
//        int n = points.length;
//        if (Double.compare(point.slopeTo(points[1]), point.slopeTo(points[n-1])) == 0) {
//            ans.add(n-1);
//            return ans;
//        }
//        for (int last = 2; last < n; last++) {
//            while (last < n && Double.compare(point.slopeTo(points[last]), minSlope) == 0) {
//                last++;
//            }
//            if (last - min >= 3 && point.compareTo(points[min]) < 0) {
//                ans.add(last-1);
//                minSlope = point.slopeTo(points[last]);
//            }
//            min = last;
//        }
//        return ans;
//    }

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

    public int numberOfSegments() {
        return numberOfSegments;
    }

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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
