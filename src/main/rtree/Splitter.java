package rtree;

import geometry.HasGeometry;
import geometry.ListPair;

import java.util.List;

public interface Splitter {
    /**
     *
     * @param items     list of items to split
     * @param minSize   min size of each list (after split)
     * @param <T>   geometry type
     * @return
     */
    <T extends HasGeometry> ListPair<T> split(List<T> items, int minSize);
}
