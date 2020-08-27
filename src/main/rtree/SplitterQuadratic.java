package rtree;

import geometry.HasGeometry;
import geometry.ListPair;
import guavaUtils.Preconditions;
import internal.Pair;

import java.util.List;

public final class SplitterQuadratic implements Splitter{
    @SuppressWarnings("unchecked")
    @Override
    public <T extends HasGeometry> ListPair<T> split(List<T> items, int minSize) {
        Preconditions.checkArgument(items.size() >= 2);

        //find the worst combination pairwise in the list and use them to create the 2 groups
        final Pair<T> worstCombination = worstCombination(items);

        final List<T> group1 = Lists.newArrayList(worstCombination.value1());
    }
}
