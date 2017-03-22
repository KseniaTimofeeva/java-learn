package objects.drawer;

import java.util.Arrays;

/**
 * Created by ksenia on 21.03.2017.
 */
public class Figure extends Drawer {

    public Figure(int xLimit, int yLimit) {
        super(xLimit, yLimit);
    }

    public void draw(Rectangle rectangle) {
        int x1 = rectangle.point1.x;
        int y1 = rectangle.point1.y;
        int x3 = rectangle.point3.x;
        int y3 = rectangle.point3.y;

        rectangle.point2 = new Point2D(x3, y1);
        rectangle.point4 = new Point2D(x1, y3);

        draw(
                new Line(rectangle.point1, rectangle.point2),
                new Line(rectangle.point2, rectangle.point3),
                new Line(rectangle.point3, rectangle.point4),
                new Line(rectangle.point4, rectangle.point1)
        );
    }

    public void draw(Circle circle) {
        Point2D[] points = new Point2D[xLimit * yLimit];
        int ptQty = 0;
        double r = Math.sqrt(Math.pow((circle.point0.x - circle.point1.x), 2) + Math.pow((circle.point0.y - circle.point1.y), 2));

        for (double k = 0; k < 1.0; k += 0.02) {
            double x = circle.point1.x + (circle.point0.x - circle.point1.x) * k;
            int y1 = (int) Math.floor(circle.point0.y + Math.sqrt(Math.pow(r, 2) - Math.pow(x - circle.point0.x, 2)));
            int y2 = 2 * circle.point0.y - y1;
            points[ptQty++] = new Point2D((int) Math.floor(x), y1);
            points[ptQty++] = new Point2D((int) Math.floor(x), y2);
            points[ptQty++] = new Point2D(2 * circle.point0.x - ((int) Math.floor(x)), y1);
            points[ptQty++] = new Point2D(2 * circle.point0.x - ((int) Math.floor(x)), y2);

        }

        draw(Arrays.copyOfRange(points, 0, ptQty - 1));
    }

}
