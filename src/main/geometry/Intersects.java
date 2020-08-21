package geometry;

import java.util.function.BiPredicate;

//TODO Implement methods that use the Circle geometry

public final class Intersects {

    private Intersects(){
        //private instantiation
    }

    public static final BiPredicate<Line, Line> lineIntersectsLine = new BiPredicate<Line, Line>() {
        @Override
        public boolean test(Line line, Line line2) {
            return line.intersects(line2);
        }
    };

    //I tried to use Java 8 lambda functions for functional interfaces with this method
    //If it doesn't work, just reformat it to look like the other methods (wrapper style)
    public static BiPredicate<Line, Rectangle> lineIntersectsRectangle(){
        return (line, rectangle) -> {
            return rectangleIntersectsLine().test(rectangle, line);
        };
    }

    public static BiPredicate<Rectangle, Line> rectangleIntersectsLine(){
        //the lambda expression becomes the test method for the BiPredicate functional interface
        return (rectangle, line) -> {
            return line.intersects(rectangle);
        };
    }

    public static final BiPredicate<Line, Point> lineIntersectsPoint = new BiPredicate<Line, Point>() {
        @Override
        public boolean test(Line line, Point point) {
            return pointIntersectsLine.test(point, line);
        }
    };

    public static final BiPredicate<Point, Line> pointIntersectsLine = new BiPredicate<Point, Line>() {
        @Override
        public boolean test(Point point, Line line) {
            return line.intersects(point);
        }
    };

    public static final BiPredicate<Geometry, Line> geometryIntersectsLine = new BiPredicate<Geometry, Line>() {
        @Override
        public boolean test(Geometry geometry, Line line) {
            if(geometry instanceof Line){
                return line.intersects((Line) geometry);
            } else if (geometry instanceof Circle){
                return line.intersects((Circle) geometry);
            } else if (geometry instanceof Point){
                return line.intersects((Point) geometry);
            } else if (geometry instanceof Rectangle){
                return line.intersects((Rectangle) geometry);
            } else {
                throw new RuntimeException("Unrecognized Geometry: " + geometry);
            }
        }
    };

    public static final BiPredicate<Geometry, Rectangle> geometryIntersectsRectangle = new BiPredicate<Geometry, Rectangle>() {
        @Override
        public boolean test(Geometry geometry, Rectangle rectangle) {
            if(geometry instanceof Line){
                return geometry.intersects(rectangle);
            } else if (geometry instanceof Circle){
                return geometry.intersects(rectangle);
            } else if (geometry instanceof Point){
                return geometry.intersects(rectangle);
            } else if (geometry instanceof Rectangle){
                return geometry.intersects(rectangle);
            } else {
                throw new RuntimeException("Unrecognized Geometry: " + geometry);
            }
        }
    };

    public static final BiPredicate<Rectangle, Geometry> rectangleIntersectsGeometry = new BiPredicate<Rectangle, Geometry>() {
        @Override
        public boolean test(Rectangle rectangle, Geometry geometry) {
            return geometryIntersectsRectangle.test(geometry, rectangle);
        }
    };

    //Intersection of geometry and point is intersection of geometry and min bounding rectangle that point is in
    public static final BiPredicate<Geometry, Point> geometryIntersectsPoint = new BiPredicate<Geometry, Point>() {
        @Override
        public boolean test(Geometry geometry, Point point) {
            return geometryIntersectsRectangle.test(geometry, point.minBoundingRectangle());
        }
    };

    public static final BiPredicate<Point, Geometry> pointIntersectsGeometry = new BiPredicate<Point, Geometry>() {
        @Override
        public boolean test(Point point, Geometry geometry) {
            return geometryIntersectsPoint.test(geometry, point);
        }
    };

}
