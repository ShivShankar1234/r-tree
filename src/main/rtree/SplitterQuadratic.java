package rtree;

import geometry.HasGeometry;
import geometry.ListPair;
import guavaUtils.Lists;
import guavaUtils.Preconditions;
import guavaUtils.annotations.VisibleForTesting;
import internal.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of quadratic splitting algorithm
 */
public final class SplitterQuadratic implements Splitter{
    @SuppressWarnings("unchecked")
    @Override
    public <T extends HasGeometry> ListPair<T> split(List<T> items, int minSize) {
        Preconditions.checkArgument(items.size() >= 2);

        //find the worst combination pairwise in the list and use them to create the 2 groups
        final Pair<T> worstCombination = worstCombination(items);

        final List<T> group1 = Lists.checkAndCreateArrayList(worstCombination.first());
        final List<T> group2 = Lists.checkAndCreateArrayList(worstCombination.second());

        final List<T> remaining = new ArrayList<>(items);
        remaining.remove(worstCombination.first());
        remaining.remove(worstCombination.second());

        final int minGroupSize = items.size() / 2;

        // now add the remainder to the groups using least mbr area increase, unless a minimumSize
        // constriant is violated
        while(remaining.size() > 0){
            assignRemaining(group1, group2, remaining, minGroupSize);
        }
        return new ListPair<>(group1, group2);
    }

    @VisibleForTesting
    static <T extends HasGeometry> Pair<T> worstCombination(List<T> items){
        Optional<T> e1 = Optional.empty();
        Optional<T> e2 = Optional.empty();

        Optional<Double> maxArea = Optional.empty();
        for(int i = 0; i < items.size(); i++){
            for(int j = i + 1; j < items.size(); j++){
                T entry1 = items.get(i);
                T entry2 = items.get(j);
                final double area = entry1.geometry().minBoundingRectangle().add(entry2.geometry())
                        .minBoundingRectangle().area();
                if(!maxArea.isPresent() || area > maxArea.get()){
                    e1 = Optional.of(entry1);
                    e2 = Optional.of(entry2);
                    maxArea = Optional.of(area);
                }
            }
        }
        if(e1.isPresent()){
            return new Pair<T>(e1.get(), e2.get());
        } else {
            return new Pair<T>(items.get(0), items.get(1));
        }
    }
}
