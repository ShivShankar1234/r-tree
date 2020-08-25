package geometry;

import java.util.List;

public class ListPair<T extends HasGeometry> {
    private final Group<T> group1;
    private final Group<T> group2;

    private double areaSum = -1;
    private final double marginSum;

    public ListPair(List<T> list1, List<T> list2){
        this.group1 = new Group<T>(list1);
        this.group2 = new  Group<T>(list2);
        this.marginSum = group1.geometry().minBoundingRectangle().perimeter() +
                group2.geometry().minBoundingRectangle().perimeter();
    }

    public Group<T> group1(){
        return group1;
    }

    public Group<T> group2(){
        return group2;
    }

    public double areaSum(){
        if(areaSum == -1){
            areaSum = group1.geometry().minBoundingRectangle().area() +
                    group2.geometry().minBoundingRectangle().area();
        }
        return areaSum;
    }

    public double marginSum() {
        return marginSum;
    }
}
