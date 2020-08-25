package rtree;

import geometry.Geometry;
import geometry.HasGeometry;
import internal.NodeAndEntries;

import java.util.List;

public interface Node<T, S extends Geometry> extends HasGeometry {
    List<Node<T, S>> add(Entry<? extends T, ? extends S> entry);

    NodeAndEntries<T, S> delete(Entry<? extends T, ? extends S> entry, boolean all);

    int count();

    Context<T, S> context();
}
