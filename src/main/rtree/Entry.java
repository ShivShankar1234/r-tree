package rtree;

import geometry.Geometry;
import geometry.HasGeometry;

public interface Entry<T, S extends Geometry> extends HasGeometry {
    T value();

    @Override
    S geometry();
}
