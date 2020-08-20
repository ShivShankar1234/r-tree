package geometry;

public interface Line extends Geometry {

    double x1();

    double x2();

    double y1();

    double y2();

    boolean intersects(Line line);

    boolean intersects(Point p);

    boolean intersects(Circle c);
}
