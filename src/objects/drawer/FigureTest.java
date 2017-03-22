package objects.drawer;

/**
 * Created by ksenia on 22.03.2017.
 */
public class FigureTest {
    public static void main(String[] args) {
        Figure figure = new Figure(25, 20);

        Rectangle rectangle = new Rectangle(new Point2D(2, 2), new Point2D(20,5));
        figure.draw(rectangle);
        System.out.println("S = " + rectangle.square());
        System.out.println("P = " + rectangle.perimeter());

        Triangle triangle = new Triangle(new Point2D(2, 2), new Point2D(2, 5), new Point2D(10, 5));
        figure.draw(triangle);
        System.out.println("S = " + triangle.square());
        System.out.println("P = " + triangle.perimeter());

        Circle circle = new Circle(new Point2D(10, 10), new Point2D(2, 10));
        figure.draw(circle);
        System.out.println("S = " + circle.square());
        System.out.println("P = " + circle.perimeter());

    }
}
