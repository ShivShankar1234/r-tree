package rtree;

import geometry.Geometry;
import guavaUtils.Preconditions;

public final class Context<T, S extends Geometry> {

    private final int maxChildren;
    private final int minChildren;
    private final Splitter splitter;
    private final Selector selector;
    private final Factory<T, S> factory;

    public Context(int minChildren, int maxChildren, Selector selector,
                   Splitter splitter, Factory<T, S> factory){
        Preconditions.checkNotNull(splitter);
        Preconditions.checkNotNull(selector);
        Preconditions.checkArgument(maxChildren > 2);
        Preconditions.checkArgument(minChildren >= 1);
        Preconditions.checkArgument(minChildren < maxChildren);
        Preconditions.checkNotNull(factory);
        this.selector = selector;
        this.maxChildren = maxChildren;
        this.minChildren = minChildren;
        this.splitter = splitter;
        this.factory = factory;
    }

    public int maxChildren(){
        return maxChildren;
    }

    public int minChildren(){
        return minChildren;
    }

    public Splitter splitter(){
        return splitter;
    }

    public Selector selector(){
        return selector;
    }

    public Factory<T, S> factory() {
        return factory;
    }
}


