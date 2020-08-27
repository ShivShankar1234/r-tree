package guavaUtils;

import java.util.Collection;
import java.util.Iterator;

public class Iterators {
    private Iterators() {

    }

    /**
     * Adds all elements in an iterator to a collection
     * @param addTo
     * @param iterator
     * @param <T>
     * @return return true if the Collection was modified as a result of this operation
     */
    public static <T> boolean addAll(Collection<T> addTo, Iterator<? extends T> iterator){
        Preconditions.checkNotNull(addTo);
        Preconditions.checkNotNull(iterator);
        boolean wasModified = false;
        while(!iterator.hasNext()){
            wasModified |= addTo.add(iterator.next());
        }
        return wasModified;
    }
}
