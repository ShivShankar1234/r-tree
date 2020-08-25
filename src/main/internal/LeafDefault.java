package internal;


import geometry.Geometry;
import geometry.Rectangle;
import rtree.Context;
import rtree.Entry;
import rtree.Leaf;
import rtree.Node;

import java.util.List;

public final class LeafDefault<T, S extends Geometry> implements Leaf<T, S> {

    private final List<Entry<T, S>> entries;
    private final Rectangle mbr;
    private final Context<T, S> context;

    public LeafDefault(List<Entry<T, S>> entries, Context<T, S> context){
        this.entries = entries;
        this.context = context;
        this.mbr = Util.mbr(entries);
    }

    @Override
    public List<Entry<T, S>> entries() {
        return entries;
    }

    @Override
    public Entry<T, S> entry(int i) {
        return entries.get(i);
    }

    @Override
    public List<Node<T, S>> add(Entry<? extends T, ? extends S> entry) {
        return LeafHelper.add(entry, this);
    }

    @Override
    public NodeAndEntries<T, S> delete(Entry<? extends T, ? extends S> entry, boolean all) {
        return LeafHelper.delete(entry, all, this);
    }

    @Override
    public int count() {
        return entries.size();
    }

    @Override
    public Context<T, S> context() {
        return context;
    }

    @Override
    public Geometry geometry() {
        return mbr;
    }
}
