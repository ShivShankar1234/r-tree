package rtree;

import geometry.Geometry;
import internal.EntryDefault;

public final class Entries {
    private Entries(){
        //prevent instantiation
    }

    public static <T, S extends Geometry> Entry<T, S> entry(T object, S geometry){
        return EntryDefault.entry(object, geometry);
    }
}
