package internal;

import geometry.Geometry;
import guavaUtils.Preconditions;
import rtree.Entry

/**
 * An entry in an R-Tree which has a spatial representation
 * @param <T>
 * @param <S>
 */
public final class EntryDefault<T, S extends Geometry> implements Entry<T, S>  {
    private final T value;
    private final S geometry;

    public EntryDefault(T value, S geometry){
        Preconditions.checkNotNull(geometry);
        this.value = value;
        this.geometry = geometry;
    }

    /**
     * rtree.Factory method
     * @param value
     * @param geometry
     * @param <T>
     * @param <S>
     * @return
     */
    public static <T, S extends Geometry> Entry<T, S> entry(T value, S geometry){
        return new EntryDefault<T, S>(value, geometry);
    }

    @Override
    public  T value(){
        return value;
    }

    @Override
    public S geometry() {
        return geometry;
    }
}
