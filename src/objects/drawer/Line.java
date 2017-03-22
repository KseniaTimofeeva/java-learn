package objects.drawer;

/**
 * Created by xmitya on 18.09.16.
 */
public class Line {
    Point2D point1;
    Point2D point2;

    public Line() {
    }

    public Line(Point2D point1, Point2D point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public void draw() {
        System.out.printf("Drawing line from %s %s to %s %s\n", point1.x, point1.y, point2.x, point2.y);
    }

    public String getName() {
        return "Line";
    }
}
