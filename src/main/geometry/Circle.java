package geometry;

public interface Circle extends Geometry {
    double x();

    double y();

    double radius();

    boolean intersects(Circle c);

    boolean intersects(Point p);

    boolean intersects(Line line);

}
