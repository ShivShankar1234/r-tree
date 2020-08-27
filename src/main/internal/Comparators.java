package internal;

import geometry.Geometry;
import geometry.HasGeometry;
import geometry.Rectangle;
import rtree.Entry;

import java.util.Comparator;
import java.util.List;

public final class Comparators {
    private Comparators(){

    }

    /**
     * Compares nodes by how much they overlap with other nodes
     *
     * @param r     The min bounding rectangle of the entry we want to insert
     * @param list
     * @param <T>
     * @return
     */
    public static <T extends HasGeometry> Comparator<HasGeometry> overlapAreaComparator(
            final Rectangle r, final List<T> list){
        return new Comparator<HasGeometry>() {
            @Override
            public int compare(HasGeometry g1, HasGeometry g2){
                int comp = Double.compare(overlapArea(r, list, g1), overlapArea(r, list, g2));
                if(comp == 0){
                    comp = Double.compare(areaIncrease(r, g1), areaIncrease(r, g2));
                    if(comp == 0){
                        comp = Double.compare(area(r, g1), area(r, g2));
                    }
                }
                return comp;
            }
        };
    }

    public static<T extends HasGeometry> Comparator<HasGeometry> areaIncreaseComparator(final Rectangle r){
        return new Comparator<HasGeometry>() {
            @Override
            public int compare(HasGeometry g1, HasGeometry g2){
                int comp = Double.compare(areaIncrease(r, g1), areaIncrease(r, g2));
                if(comp == 0){
                    comp = Double.compare(area(r, g1), area(r, g2));
                }
                return comp;
            }
        };
    }

    private static double area(Rectangle r, HasGeometry g){
        return g.geometry().minBoundingRectangle().add(r).area();
    }

    /**
     * Returns the total interesting area of a nodes min bounding rectangle and every other nodes
     * min bounding rectangle
     * @param r
     * @param list
     * @param g
     * @return
     */
    private static float overlapArea(Rectangle r, List<? extends HasGeometry> list, HasGeometry g) {
        Rectangle newRect = g.geometry().minBoundingRectangle().add(r);
        float m = 0;
        for(HasGeometry other : list){
            if(other != g){
                m += newRect.intersectionArea(other.geometry().minBoundingRectangle());
            }
        }
        return m;
    }

    private static double areaIncrease(Rectangle r, HasGeometry g){
        Rectangle newRect = g.geometry().minBoundingRectangle().add(r);
        return newRect.area() - g.geometry().minBoundingRectangle().area();
    }

    /**
     * Returns a comparator used to sort entries returned by search methods
     */
    public static<T, S extends Geometry> Comparator<Entry<T, S>> ascendingDistance(final Rectangle r){
        return new Comparator<Entry<T, S>>() {
            @Override
            public int compare(Entry<T, S> o1, Entry<T, S> o2) {
                return Double.compare(o1.geometry().distance(r), o2.geometry().distance(r));
            }
        };
    }
}
