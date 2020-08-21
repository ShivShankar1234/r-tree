package geometry;

/**
 * Note that a rectangle can be represented by just 2 points: The bottom left (x1, y1)
 * and top right point (x2, y2).
 */
public interface Rectangle extends Geometry, HasGeometry {
    double x1();

    double x2();

    double y1();

    double y2();

    double area();

    double perimeter();

    double intersectionArea(Rectangle r);

    Rectangle add(Rectangle r);

    boolean contains(double x, double y);

    boolean isDoublePrecision();

}
