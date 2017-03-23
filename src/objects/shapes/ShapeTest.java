package objects.shapes;

/**
 * Created by ksenia on 22.03.2017.
 */
public class ShapeTest {
    public static void main(String[] args) {
        Circle circle = new Circle(2);
        System.out.println("P круга " + circle.getPerimeter());
        System.out.println("S круга " + circle.getArea());

        Triangle triangle = new Triangle(2, 3, 3);
        System.out.println("P треугольника " + triangle.getPerimeter());
        System.out.println("S треугольника " + triangle.getArea());

        Rectangle rectangle = new Rectangle(2, 3);
        System.out.println("P прям-ка " + rectangle.getPerimeter());
        System.out.println("S прям-ка " + rectangle.getArea());

        Square square = new Square(2);
        System.out.println("P квадрата " + square.getPerimeter());
        System.out.println("S квадрата " + square.getArea());

    }
}
