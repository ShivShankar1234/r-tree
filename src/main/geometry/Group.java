package geometry;

import internal.Util;

import java.util.List;

/**
 * list of a spatial structures and the minimum bounding rectangle that
 * covers them all.
 * @param <T>
 */
public class Group<T extends HasGeometry> implements HasGeometry {
    private final List<T> list;
    private final Rectangle mbr;

    public Group(List<T> list){
        this.list = list;
        this.mbr = Util.mbr(list);
    }

    public List<T> list() {
        return list;
    }

    @Override
    public Geometry geometry(){
        return mbr;
    }
}
