package rtree;

import geometry.Geometry;

import java.util.List;

public interface NonLeaf<T, S extends Geometry> extends Node<T, S> {

    Node<T, S> child(int i);

    /**
     *
     * @return      returns list of child nodes
     */
    List<Node<T, S>> children();
}
