package objects.drawer;

/**
 * Created by ksenia on 21.03.2017.
 */
public class Rectangle {
    Point2D point1;
    Point2D point2;
    Point2D point3;
    Point2D point4;

    public Rectangle(Point2D point1, Point2D point3) {
        this.point1 = point1;
        this.point3 = point3;
    }

    public int square() {
        return Math.abs(point4.y - point1.y) * Math.abs(point2.x - point1.x);
    }


    public int perimeter() {
        return 2 * Math.abs(point4.y - point1.y) + 2 * Math.abs(point2.x - point1.x);
    }
}
