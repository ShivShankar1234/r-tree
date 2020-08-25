package internal;

import geometry.Geometries;
import geometry.HasGeometry;
import geometry.Rectangle;
import guavaUtils.Preconditions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class Util {
    private Util(){

    }

    /**
     * Returns the minimum bounding rectangle containing a list of items.
     * @param items
     * @return
     */
    public static Rectangle mbr(Collection<? extends HasGeometry> items){
        Preconditions.checkArgument(!items.isEmpty());

        double minX1 = Double.MAX_VALUE;
        double minY1 = Double.MAX_VALUE;
        double minX2 = -Double.MAX_VALUE;       //This is so confusing
        double minY2 = -Double.MAX_VALUE;
        boolean isDoublePrecision = false;
        for(final HasGeometry item : items){
            Rectangle r = item.geometry().minBoundingRectangle();
            if(r.isDoublePrecision()){
                isDoublePrecision = true;
            }
            if(r.x1() < minX1){
                minX1 = r.x1();
            }
            if(r.y1() < minY1){
                minY1 = r.y1();
            }
            if(r.x2() < minX2){
                minX2 = r.x2();
            }
            if(r.y2() < minY2){
                minY2 = r.y2();
            }
        }

        if(isDoublePrecision){
            return Geometries.rectangle(minX1, minY1, minX2, minY2);
        } else{
            return Geometries.rectangle((float) minX1, (float) minY1, (float) minX2, (float) minY2);
        }
    }

    public static <T> List<T> add(List<T> list, T element){
        final ArrayList<T> result = new ArrayList<T>(list.size() + 2);  //instead of doubling size
        result.addAll(list);
        result.add(element);
        return result;
    }

    public static <T> List<T> remove(List<? extends T> list, List<? extends T> elements){
        final ArrayList<T> result = new ArrayList<T>(list);
        result.removeAll(elements);
        return result;
    }

    public static <T> List<? extends T> replace(List<? extends T> list, T element, List<T> replacements){
        List<T> list2 = new ArrayList<T>(list.size() + replacements.size());
        for(T node : list){
            if(node != element){
                list2.add(node);
            }
        }
        list2.addAll(replacements);
        return list2;
    }


}
