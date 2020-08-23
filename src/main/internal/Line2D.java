package internal;

public class Line2D {
    private final double x1, y1, x2, y2;

    public Line2D(double x1, double y1, double x2, double y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }


    public double getY1() {
        return y1;
    }

    public double getY2(){
        return y2;
    }

    /**
     * Returns the minimum distance from a point to a line. (The residual distance).
     * Note this is similar to projecting onto an infinite line segment because if the point is perpendicular
     * to any point on the line the distance is simply the magnitude of the error vector of the projection (residual).
     * Otherwise the distance is the distance between the point and the closest end point of the line.
     */
    public double ptSegDist(double x, double y){
        return Math.sqrt(ptSegDistSq(getX1(), getY1(), getX2(), getY2(), x, y));
    }

    public double ptSegDist(double x1, double xy1, double x2, double y2, double px, double py){
        return Math.sqrt(ptSegDistSq(x1, y1, x2, y2, px, py));
    }

    /**
     * Explanation on https://stackoverflow.com/questions/849211/shortest-distance-between-a-point-and-a-line-segment
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param px
     * @param py
     * @return
     */
    public double ptSegDistSq(double x1, double y1, double x2, double y2, double px, double py){
        //First we center the vectors to have an endpoint at 0 for ease of calculation
        //This is the vector representing the line (location of second endpoint)
        x2 -= x1;
        y2 -= y1;
        //This is the vector representing the points location
        px -= x1;
        py -= y1;

        double dotProduct = dot(px, py, x2, y2);
        double lenSq = dot(x2, y2, x2, y2);

        double param = -1;

        if(lenSq != 0){
            param = dotProduct / lenSq;
        }

        double closestX, closestY;

        if (param < 0){
            closestX = x1;
            closestY = y1;
        } else if (param > 1){
            closestX = x2;
            closestY = y2;
        } else{
            closestX = x1 + param * x2;
            closestY = y1 + param * y2;
        }

        double dx = px - closestX;
        double dy = py - closestY;

        return dot(dx, dy, dx, dy);         // gives length squared

    }

    public double dot(double a, double b, double x, double y){
        return a * x + b * y;
    }

    public boolean intersectsLine(Line2D line){
        return linesIntersect(line.getX1(), line.getY1(), line.getX2(), line.getY2(),
                getX1(), getY1(), getX2(), getY2());
    }

    /**
     * Tests if line segment form (x1, y1) to (x2, y2) intersects the segment from (x3, y3) to (x4, y4)
     *
     * https://www.geeksforgeeks.org/check-if-two-given-line-segments-intersect/
     *
     * Let lines be given by a pair of endpoints: line 1 = (p1, q1), line 2 = (p2, q2)
     * Case 1: Lines intersect and are not collinear if the paths formed by:
     *          (p1, q1, p2) and (p1, q1, q2) have different orientations AND
     *          (p2, q2, p1) and (p2, q2, q1) have different orientations
     *
     * Case 2: (p1, q1, p2), (p1, q1, q2), (p2, q2, p1), and (p2, q2, q1) are all collinear and
     *          The x projection of (p1, q1) and (p2, q2) intersect
     *          The y projection of (p1, q1) and (p2, q2) intersect
     *
     * Note:
     * p1 = (x1, y1)
     * q1 = (x2, y2)
     * p2 = (x3, y3)
     * q2 = (x4, y4)
     */

    private static boolean linesIntersect(double x1, double y1, double x2, double y2,
                                          double x3, double y3, double x4, double y4){
        //Orientations of the 4 special cases
        int o1 = orientation(x1, y1, x2, y2, x3, y3);
        int o2 = orientation(x1, y1, x2, y2, x4, y4);
        int o3 = orientation(x3, y3, x4, y4, x1, y1);
        int o4 = orientation(x3, y3, x4, y4, x1, y1);

        if(o1 != o2 && o3 != o4){
            return true;        //Case 1
        }

        //Case 2
        if(o1 == 0 && onSegment(x1, y1, x3, y3, x2, y2)){
            return true;
        }
        if(o2 == 0 && onSegment(x1, y1, x4, y4, x2, y2)){
            return true;
        }
        if(o3 == 0 && onSegment(x3, y3, x1, y1, x4, y4)){
            return true;
        }
        if(o4 == 0 && onSegment(x3, y3, x2, y2, x4, y4)){
            return true;
        }
        return false;
    }

    /**
     * Returns an int corresponding to orientation
     * 0 ---> p, q, r are collinear
     * 1 ---> Clockwise
     * 2 ---> CounterClockwise
     *
     * https://www.geeksforgeeks.org/orientation-3-ordered-points/
     */
    static int orientation(double p1, double p2, double q1, double q2, double r1, double r2){
        double val = (q2 - p2) * (r1 - q1) - (q1 - p1) * (r2 - q2);

        if (val == 0){
            return 0;       //collinear
        }
        return (val > 0) ? 1 : 2;
    }

    /**
     * Given 3 collinear points p, q, and r, checks if point q lies on line segment 'pr'
     */
    static boolean onSegment(double p1, double p2, double q1, double q2, double r1, double r2){
        return q1 <= Math.max(p1, r1) && q1 >= Math.min(p1, r1) &&
                q2 <= Math.max(p2, r2) && q2 >= Math.min(p2, r2);
    }

}
