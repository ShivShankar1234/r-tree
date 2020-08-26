package internal;

import geometry.Geometry;
import geometry.ListPair;
import rtree.Context;
import rtree.Entry;
import rtree.Node;
import rtree.NonLeaf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.of;

public final class NonLeafHelper {
    private NonLeafHelper(){

    }

    public static <T, S extends Geometry> List<Node<T, S>> add(
            Entry<? extends T, ? extends S> entry, NonLeaf<T, S> node){
        Context<T, S> context = node.context();
        List<Node<T, S>> children = node.children();
        final Node<T, S> child = context.selector().select(entry.geometry().minBoundingRectangle(), children);
        List<Node<T, S>> list = child.add(entry);
        List<? extends Node<T, S>> children2 = Util.replace(children, child, list);
        if(children2.size() <= context.maxChildren()){
            return Collections.singletonList((Node<T, S>) context.factory().createNonLeaf(children2, context));
        } else {
            ListPair<? extends Node<T, S>> pair = context.splitter().split(children2, context.minChildren());
            return makeNonLeaves(pair, context);
        }
    }

    private static <T, S extends Geometry>  List<Node<T, S>> makeNonLeaves(
            ListPair<? extends Node<T, S>> pair, Context<T, S> context) {
        List<Node<T, S>> list =  new ArrayList<Node<T, S>>();
        list.add(context.factory().createNonLeaf(pair.group1().list(), context));
        list.add(context.factory().createNonLeaf(pair.group2().list(), context));
        return list;
    }

    /**
     *
     * @param entry
     * @param all
     * @param node
     * @param <T>
     * @param <S>
     * @return
     */
    public static <T, S extends Geometry> NodeAndEntries<T, S> delete(
            Entry<? extends T, ? extends S> entry, boolean all, NonLeaf<T, S> node){
        List<Entry<T, S>> entriesToAdd = new ArrayList<>();
        List<Node<T, S>> nodesToRemove = new ArrayList<>();
        List<Node<T, S>> nodesToAdd = new ArrayList<>();
        int countDeleted = 0;

        List<? extends Node<T, S>> children = node.children();
        for(final Node<T, S> child : children){
            if(entry.geometry().intersects(child.geometry().minBoundingRectangle())){
                final NodeAndEntries<T, S> result = child.delete(entry, all);
                if(result.node().isPresent()){
                    //deletion occurred and child is above minChildren so we update it
                    if(result.node().get() != child){
                        nodesToAdd.add(result.node().get());    //add updated version of child
                        nodesToRemove.add(child);               // remove old version of child
                        entriesToAdd.addAll(result.entriesToAdd());
                        countDeleted += result.countDeleted();
                        if(!all){
                            break;
                        }
                    }
                } else {
                    //deletion occurred and now the child has less than minChildren
                    // so we redistribute the entries
                    nodesToRemove.add(child);
                    entriesToAdd.addAll(result.entriesToAdd());
                    countDeleted += result.countDeleted();
                    if(!all){
                        break;
                    }
                }
            }
        }
        if(nodesToRemove.isEmpty()){
            // done removing in this subtree
            return new NodeAndEntries<T, S>(of(node), Collections.emptyList(), 0);
        } else {
            List<Node<T, S>> nodes = Util.remove(children, nodesToRemove);
            nodes.addAll(nodesToAdd);
            if(nodes.size() == 0){
                // no new nodes to add (meaning nothing was deleted)
                return new NodeAndEntries<T, S>(Optional.empty(), entriesToAdd, countDeleted);
            } else {
                // new node created (something was deleted)
                // most commonly occurs when new version of node is replacing old version
                NonLeaf<T, S> nd = node.context().factory().createNonLeaf(nodes, node.context());
                return new NodeAndEntries<T, S>(of(nd), entriesToAdd, countDeleted);
            }
        }

    }


}
