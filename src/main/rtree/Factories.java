package rtree;

import geometry.Geometry;
import internal.FactoryDefault;

public final class Factories {

    private Factories() {
        //prevent instantiation
    }

    public static <T, S extends Geometry> Factory<T, S> defaultFactory() {
        return FactoryDefault.instance();
    }
}
