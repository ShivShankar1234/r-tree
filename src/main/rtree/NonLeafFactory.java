package rtree;

import geometry.Geometry;

import java.util.List;

public interface NonLeafFactory <T, S extends Geometry>{
    NonLeaf<T, S> createNonLeaf(List<? extends Node<T, S>> children, Context<T, S> context);
}
