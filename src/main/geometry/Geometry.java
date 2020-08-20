package geometry;

/**
 * A geometric region that represents an Entry.
 * For custom implementations of this interface please
 *  implement the equals() and hashCode() methods appropriately.
 */
public interface Geometry {

    /**
     *
     * @param r     Rectangle to measure distance to
     * @return      distance to the Rectangle r from the geometry
     */
    public double distance(Rectangle r);

    /**
     * @return      minimum bounding Rectangle
     */
    public Rectangle minBoundingRectangle();

    boolean intersects(Rectangle r);

    boolean isDoublePrecision();


}
