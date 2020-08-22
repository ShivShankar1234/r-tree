package geometry.internal;

import geometry.Line;

public class LineDouble implements Line{

    private final double x1;
    private final double y1;
    private final double x2;
    private final double y2;

    private LineDouble(double x1, double y1, double x2, double y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public static LineDouble create(double x1, double y1, double x2, double y2){
        return new LineDouble(x1, y1, x2, y2);
    }
}
