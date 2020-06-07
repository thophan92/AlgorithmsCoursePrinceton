import PartOne.Assignment.Week3.Point;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        int[] arr = new int[3];
//        arr[0] = 2; arr[1] = 1; arr[2] = 0;
//        int[] temp = arr;
//        System.out.println("print temp");
//        for (int i = 0; i < temp.length; i++) {
//            System.out.println(temp[i]);
//        }
//        Arrays.sort(arr);
//        System.out.println("print arr");
//        for (int i = 0; i < arr.length; i++) {
//            System.out.println(arr[i]);
//        }
//        System.out.println("print temp");
//        for (int i = 0; i < temp.length; i++) {
//            System.out.println(temp[i]);
//        }

        Point[] points = new Point[3];
        points[0] = new Point(2,2);
        points[1] = new Point(1, 1);
        points[2] = new Point(0, 0);
        Point[] copy = new Point[3];
        for (int i = 0; i < points.length; i++) {
            copy[i] = points[i];
        }
        System.out.println("points");
        printPoint(points);
        System.out.println("copy");
        printPoint(copy);
        Arrays.sort(points);
        System.out.println("points");
        printPoint(points);
        System.out.println("copy");
        printPoint(copy);
    }

    public static void printPoint(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            System.out.println(points[i]);
        }
    }

    private void printPoints(Point[] points, Point p) {
        for (int i = 0; i < points.length; i++) {
            System.out.println(points[i] + " >>> " + p.slopeTo(points[i]));
        }
    }

    private void printSlope(Point[] points, Point p) {
        for (int i = 0; i < points.length; i++) {
            System.out.print(p.slopeTo(points[i]) + " >>> ");
        }
    }
}
