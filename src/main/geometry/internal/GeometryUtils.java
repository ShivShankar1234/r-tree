package geometry.internal;

import geometry.Rectangle;

public final class GeometryUtils {
    private GeometryUtils(){

    }

    /**
     *
     * @param x
     * @param y
     * @param r
     * @return Distance from point to rectangle
     */
    public static double distance(double x, double y, Rectangle r){
        return distance(x, y, r.x1(), r.y1(), r.x2(), r.y2());
    }

    public static double distance(double x, double y, double a1, double b1, double a2, double b2){
        return distance(x, y, x, y, a1, b1, a2, b2);
    }

    public static double distance(double x1, double y1, double x2, double y2,
                                  double a1, double b1, double a2, double b2){
        if(intersects(x1, y1, x2, y2, a1, b1, a2, b2)){
            return 0;
        } else{
            double leftmostX1 = Math.min(x1, a1);
            double rightmostX1 = Math.max(x1, a1);
            double leftmostX2 = Math.min(x2, a2);


            // rightmostX1 = leftmostX1 when the points are contained in the same rectangle
            // if not the distance is the distance between the rightmost and leftmost point of both rectangles
            double xDiff = Math.max(0, leftmostX1 == rightmostX1 ? 0 : rightmostX1 - leftmostX2);

            double downmostY1 = Math.min(y1, b1);
            double upmostY1 = Math.max(y1, b1);
            double downmostY2 = Math.min(y2, b2);

            double yDiff = Math.max(0, downmostY1 == upmostY1 ? 0 : upmostY1 - downmostY2);

            return Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
        }
    }

    //This is the code for distance from the repo (change to this if we get errors)
    /*
    public static double distance(double x1, double y1, double x2, double y2, double a1, double b1,
            double a2, double b2) {
        if (intersects(x1, y1, x2, y2, a1, b1, a2, b2)) {
            return 0;
        }
        boolean xyMostLeft = x1 < a1;
        double mostLeftX1 = xyMostLeft ? x1 : a1;
        double mostRightX1 = xyMostLeft ? a1 : x1;
        double mostLeftX2 = xyMostLeft ? x2 : a2;
        double xDifference = max(0, mostLeftX1 == mostRightX1 ? 0 : mostRightX1 - mostLeftX2);

        boolean xyMostDown = y1 < b1;
        double mostDownY1 = xyMostDown ? y1 : b1;
        double mostUpY1 = xyMostDown ? b1 : y1;
        double mostDownY2 = xyMostDown ? y2 : b2;

        double yDifference = max(0, mostDownY1 == mostUpY1 ? 0 : mostUpY1 - mostDownY2);

        return Math.sqrt(xDifference * xDifference + yDifference * yDifference);
    }
     */

    public static boolean intersects(double x1, double y1, double x2, double y2,
                                     double a1, double b1, double a2, double b2){
        return x1 <= a2 && a1 <= x2 && y1 <= b2 && b1 <= y2;
    }
}
