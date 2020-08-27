package guavaUtils;

import guavaUtils.annotations.VisibleForTesting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class Lists {
    private Lists(){

    }

    public static <E> ArrayList<E> checkAndCreateArrayList(E... elements){
        Preconditions.checkNotNull(elements);
        // This is to avoid integer overflow when large array is passed in
        int capacity = computeArrayListCapacity(elements.length);
        ArrayList<E> list = new ArrayList<E>(capacity);
        Collections.addAll(list, elements);
        return list;
    }

    @VisibleForTesting
    static int computeArrayListCapacity(int arraySize){
        Preconditions.checkArgument(arraySize >= 0, "arraySize must be non-negative");

        return saturatedCast(5L + arraySize + (arraySize / 10));
    }

    @VisibleForTesting
    static int saturatedCast(long value) {
        if(value > Integer.MAX_VALUE){
            return Integer.MAX_VALUE;
        }
        if(value < Integer.MIN_VALUE){
            return Integer.MIN_VALUE;
        }
        return (int) value;
    }

    public static <E> ArrayList<E> checkAndCreateArrayList(){
        return new ArrayList<E>();
    }

    public static <E> ArrayList<E> checkAndCreateArrayList(Iterable<? extends E> elements){
        return (elements instanceof Collection) ? new ArrayList<E>(Collections2.cast(elements)) :
                checkAndCreateArrayList(elements.iterator());
    }

    public static <E> ArrayList<E> checkAndCreateArrayList(Iterator<? extends E> elements){
        ArrayList<E> list = checkAndCreateArrayList();
        Iterators.addAll(list, elements);
        return list;
    }
}
