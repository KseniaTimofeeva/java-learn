package objects.drawer;

/**
 * Created by ksenia on 22.03.2017.
 */
public class Circle {
    Point2D point0;         //центр окружности
    Point2D point1;         //точка на окружности

    public Circle(Point2D point0, Point2D point1) {
        this.point0 = point0;
        this.point1 = point1;
    }

    public double square() {
        double r = Math.sqrt(Math.pow((point0.x - point1.x), 2) + Math.pow((point0.y - point1.y), 2));
        return Math.PI * Math.pow(r, 2);
    }

    public double perimeter() {
        double r = Math.sqrt(Math.pow((point0.x - point1.x), 2) + Math.pow((point0.y - point1.y), 2));
        return 2 * Math.PI * r;
    }
}
