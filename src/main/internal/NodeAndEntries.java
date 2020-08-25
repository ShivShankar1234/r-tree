package internal;

import geometry.Geometry;
import rtree.Entry;
import rtree.Node;

import java.util.List;
import java.util.Optional;

/**
 * Used for tracking deletions through recursive calls.
 * @param <T>
 * @param <S>
 */
public final class NodeAndEntries<T, S extends Geometry> {
    private final Optional<? extends Node<T, S>> node;
    private final List<Entry<T, S>> entries;
    private final int count;

    public NodeAndEntries(Optional<? extends Node<T, S>> node, List<Entry<T, S>> entries, int countDeleted){
        this.node = node;
        this.entries = entries;
        this.count = countDeleted;
    }

    public Optional<? extends Node<T, S>> node(){
        return node;
    }

    public List<Entry<T, S>> entriesToAdd(){
        return entries;
    }

    public int countDeleted(){
        return count;
    }
}
