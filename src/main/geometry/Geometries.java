package geometry;

import geometry.internal.LineDouble;
import geometry.internal.PointDouble;
import geometry.internal.PointFloat;
import geometry.internal.RectangleDouble;
import guavaUtils.annotations.VisibleForTesting;


//TODO: Add support for PointFloat, LineFloat, RectangleFloat
public final class Geometries {
    private Geometries() {
        //prevent instantiation
    }

    public static Point point(double x, double y){
        return PointDouble.create(x, y);
    }

    public static Point point(float x, float y){
        return PointFloat.create(x, y);
    }

    public static Point pointGeographic(double lat, double lon){
        return point(normalizeLongitudeDouble(lon), lat);
    }

    public static Point pointGeographic(float lat, float lon){
        return point(normalizeLongitudeFloat(lon), lat);
    }

    public static Rectangle rectangle(double x1, double y1, double x2, double y2){
        return rectangleDouble(x1, y1, x2, y2);
    }

    public static Rectangle rectangle(float x1, float y1, float x2, float y2){
        return rectangleDouble(x1, y1, x2, y2);
    }

    public static Rectangle rectangleGeographic(double lon1, double lat1, double lon2, double lat2){
        return rectangleGeographic((float) lon1, (float) lat1, (float) lon2, (float) lat2);
    }

    public static Rectangle rectangleGeographic(float lon1, float lat1, float lon2, float lat2){
        float x1 = normalizeLongitudeFloat(lon1);
        float x2 = normalizeLongitudeFloat(lon2);
        if(x2 < x1){
            x2 += 360;
        }
        return rectangle(x1, lat1, x2, lat2);
    }

    public static Rectangle rectangleDouble(double x1, double y1, double x2, double y2){
        return RectangleDouble.create(x1, y1, x2, y2);
    }

    public static Line line(double x1, double y1, double x2, double y2){
        return LineDouble.create(x1, y1, x2, y2);
    }

    public static Line line(float x1, float y1, float x2, float y2){
        return LineDouble.create(x1, y1, x2, y2);
    }


    @VisibleForTesting
    static double normalizeLongitude(double d){
        return normalizeLongitudeDouble((float) d);
    }

    private static float normalizeLongitudeFloat(float d){
        if(d == -180.0f){
            return -180.0f;
        } else {
            float sign = Math.signum(d);
            float x = Math.abs(d) / 360;
            float x2 = (x - (float) Math.floor(x)) * 360;
            if(x2 >= 180){
                x2 -= 360;
            }
            return x2 * sign;
        }
    }

    private static double normalizeLongitudeDouble(double d){
        if(d == -180.0f){
            return -180.0d;
        } else{
            double sign = Math.signum(d);
            double x = Math.abs(d) / 360;
            double x2 = (x - (float) Math.floor(x)) * 360;
            if(x2 >= 180){
                x2 -= 360;
            }
            return x2 * sign;
        }
    }
}
