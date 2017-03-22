package objects.drawer;

/**
 * Created by xmitya on 18.09.16.
 */
public class Triangle {
    Point2D point1;
    Point2D point2;
    Point2D point3;

    public Triangle() {
    }

    public Triangle(Point2D point1, Point2D point2, Point2D point3) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
    }

    public double perimeter() {
        double side1 = Math.sqrt(Math.pow((point2.x - point1.x), 2) + Math.pow((point2.y - point1.y), 2));
        double side2 = Math.sqrt(Math.pow((point3.x - point2.x), 2) + Math.pow((point3.y - point2.y), 2));
        double side3 = Math.sqrt(Math.pow((point1.x - point3.x), 2) + Math.pow((point1.y - point3.y), 2));
        return side1 + side2 + side3;
    }

    public double square() {
        double side1 = Math.sqrt(Math.pow((point2.x - point1.x), 2) + Math.pow((point2.y - point1.y), 2));
        double side2 = Math.sqrt(Math.pow((point3.x - point2.x), 2) + Math.pow((point3.y - point2.y), 2));
        double side3 = Math.sqrt(Math.pow((point1.x - point3.x), 2) + Math.pow((point1.y - point3.y), 2));
        double p = (side1 + side2 + side3) / 2;
        return Math.sqrt(p * (p - side1) * (p - side2) * (p - side3));
    }

    public void draw() {
        System.out.printf("Drawing triangle on %s %s, %s %s and %s %s\n", point1.x, point1.y, point2.x, point2.y, point3.x, point3.y);
    }

    public String getName() {
        return "Triangle";
    }
}
