package rtree;

import geometry.Geometry;

import java.util.List;

public interface Leaf<T, S extends Geometry> extends Node<T, S> {
    List<Entry<T, S>> entries();

    /**
     *
     * @param i
     * @return      The ith entry of the node
     */
    Entry<T, S> entry(int i);
}
