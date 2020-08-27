package rtree;

import geometry.Geometry;
import internal.Comparators;

import java.util.Collections;
import java.util.List;

public final class SelectorMinAreaIncrease implements Selector {

    @Override
    public <T, S extends Geometry> Node<T, S> select(Geometry g, List<? extends Node<T, S>> nodes) {
        return Collections.min(nodes, Comparators.areaIncreaseComparator(g.minBoundingRectangle()));
    }
}
