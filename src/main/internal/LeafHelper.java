package internal;

import static java.util.Optional.of;

import geometry.Geometry;
import geometry.ListPair;
import rtree.Context;
import rtree.Entry;
import rtree.Leaf;
import rtree.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


public final class LeafHelper {
    private LeafHelper(){

    }

    public static <T, S extends Geometry> NodeAndEntries<T, S> delete(
            Entry<? extends T, ? extends S> entry, boolean all, Leaf<T, S> leaf){
        List<Entry<T, S>> entries = leaf.entries();
        if(!entries.contains(entry)){
            return new NodeAndEntries<T, S>(of(leaf), Collections.<Entry<T, S>> emptyList(), 0);
        } else{
            final List<Entry<T, S>> entries2 = new ArrayList<Entry<T, S>>(entries);
            entries2.remove(entry);
            int numDeleted = 1;
            // keep deleting if all is specified
            while(all && entries2.remove(entry)){
                numDeleted++;
            }

            if (entries2.size() >= leaf.context().minChildren()) {
                //if our new entry list is big enough to be a leaf, return it as a leaf
                Leaf<T, S> node = leaf.context().factory().createLeaf(entries2, leaf.context());
                return new NodeAndEntries<T, S>(of(node), Collections.<Entry<T, S>> emptyList(), numDeleted);
            } else{
                return new NodeAndEntries<T, S>(Optional.<Node<T, S>> empty(), entries2, numDeleted);
            }
        }
    }

    public static <T, S extends Geometry> List<Node<T, S>> add(
            Entry<? extends T, ? extends S> entry, Leaf<T, S> leaf){
        List<Entry<T, S>> entries = leaf.entries();
        Context<T, S> context = leaf.context();

        @SuppressWarnings("unchecked")
        final List<Entry<T, S>> entries2 = Util.add(entries, (Entry<T, S>) entry);
        if(entries2.size() <= context.maxChildren()){
            return Collections.singletonList((Node<T, S>) context.factory().createLeaf(entries2, context));
        } else{
            ListPair<Entry<T, S>> pair = context.splitter().split(entries2, context.minChildren());
            return makeLeaves(pair, context);
        }
    }

    public static <T, S extends Geometry> List<Node<T, S>> makeLeaves(ListPair<Entry<T, S>> pair,
                                                                      Context<T, S> context){
        List<Node<T, S>> list = new ArrayList<Node<T, S>>(2);
        list.add(context.factory().createLeaf(pair.group1().list(), context));
        list.add(context.factory().createLeaf(pair.group2(). list(), context));
        return list;
    }
}
