package internal;

public class Pair<T> {
    private final T val1;
    private final T val2;

    public Pair(T val1, T val2){
        this.val1 = val1;
        this.val2 = val2;
    }

    public T first(){
        return val1;
    }

    public T second(){
        return val2;
    }
}
