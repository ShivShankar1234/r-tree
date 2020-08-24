package rtree;

import geometry.Geometry;
import java.util.List;

public interface Selector {

    /**
     *
     * @param g     geometry
     * @param nodes     list of nodes to select from
     * @param <T>   type of the entry
     * @param <S>   geometry of the entry
     * @return  one of the given nodes
     */
    <T, S extends Geometry> Node<T, S> select(Geometry g, List<? extends Node<T, S>> nodes);
}
