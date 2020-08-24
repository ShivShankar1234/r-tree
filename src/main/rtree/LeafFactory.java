package rtree;

import geometry.Geometry;
import rtree.Context;
import rtree.Entry;

import java.util.List;

public interface LeafFactory<T, S extends Geometry> {
    Leaf<T, S> createLeaf(List<Entry<T, S>> entries, Context<T, S> context);
}
