package geometry.internal;

import geometry.Geometry;
import geometry.Rectangle;
import guavaUtils.Preconditions;

public class RectangleDouble implements Rectangle {
    private final double x1, x2, y1, y2;

    private RectangleDouble(double x1, double y1, double x2, double y2){
        Preconditions.checkArgument(x2 >= x1);
        Preconditions.checkArgument(y2 >= y1);
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public static RectangleDouble create(double x1, double y1, double x2, double y2){
        return new RectangleDouble(x1, y1, x2, y2);
    }

    @Override
    public double x1() {
        return x1;
    }

    @Override
    public double x2() {
        return x2;
    }

    @Override
    public double y1() {
        return y1;
    }

    @Override
    public double y2() {
        return y2;
    }

    @Override
    public double area() {
        return (x2 - x1) * (y2 - y1);
    }

    @Override
    public double perimeter() {
        return 2 * ((x2 - x1) + (y2 - y1));
    }

    @Override
    public double intersectionArea(Rectangle r) {
        if(!intersects(r)){
            return 0;
        } else{
            return (Math.min(x2, r.x2()) - Math.max(x1, r.x1())) *
                    (Math.min(y2, r.y2()) - Math.max(y1, r.y1()));
        }
    }

    @Override
    /**
     * Merges another rectangle (which contains 1 or more points) with the current rectangle instance
     */
    public Rectangle add(Rectangle r) {
        return new RectangleDouble(Math.min(x1, r.x1()), Math.min(y1, r.y1()),
                Math.max(x2, r.x2()), Math.max(y2, r.y2()));
    }

    /**
     *
     * @param x
     * @param y
     * @return      true if point (x, y) is contain within the rectangle
     */
    @Override
    public boolean contains(double x, double y) {
        return x >= x1 && x <= x2 && y >= y1 && y <= y2;
    }

    @Override
    public double distance(Rectangle r) {
        return GeometryUtils.distance(x1, y1, x2, y2, r.x1(), r.y1(), r.x2(), r.y2());
    }

    @Override
    public Rectangle minBoundingRectangle() {
        return this;
    }

    @Override
    public boolean intersects(Rectangle r) {
        return GeometryUtils.intersects(x1, y1, x2, y2, r.x1(), r.y1(), r.x2(), r.y2());
    }

    @Override
    public boolean isDoublePrecision() {
        return true;
    }


    @Override
    public Geometry geometry() {
        return this;
    }
}
