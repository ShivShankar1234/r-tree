package rtree;

import geometry.Geometry;
import internal.Comparators;

import java.util.Collections;
import java.util.List;

/**
 * selects node that everlaps the least with all other nodes
 */
public final class SelectorMinOverlapArea implements Selector{

    @Override
    public <T, S extends Geometry> Node<T, S> select(Geometry g, List<? extends Node<T, S>> nodes) {
        return Collections.min(nodes, Comparators.overlapAreaComparator(g.minBoundingRectangle(), nodes));
    }
}
