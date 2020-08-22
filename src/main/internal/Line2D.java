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

    /**
     * Tests if line segment form (x1, y1) to (x2, y2) intersects the segment from (x3, y3) to (x4, y4)
     */
    
}
