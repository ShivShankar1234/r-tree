package guavaUtils;

import java.util.Collection;
import java.util.Collections;

public final class Collections2 {
    private Collections2(){

    }

    //used to avoid http://bugs.sun.com/view_bug.do?bug_id=6558557
    static <T> Collection<T> cast(Iterable<T> iterable){
        return (Collection<T>) iterable;
    }
}
