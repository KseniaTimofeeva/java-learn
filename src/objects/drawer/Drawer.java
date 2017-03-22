package objects.drawer;

import java.util.Arrays;

/**
 * Created by xmitya on 18.03.17.
 */
public class Drawer {
    int xLimit;
    int yLimit;

    public Drawer(int xLimit, int yLimit) {
        this.xLimit = xLimit;
        this.yLimit = yLimit;
    }

    public void draw(Point2D... points) {
        for (int y = 0; y < yLimit; y++) {
            for (int x = 0; x < xLimit; x++) {

                boolean set = false;

                for (Point2D point : points) {
                    if (point.x == x && y == point.y) {
                        System.out.print("*");

                        set = true;

                        break;
                    }
                }

                if (!set)
                    System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    public void draw(Line... lines) {
        Point2D[] points = new Point2D[xLimit * yLimit];

        int ptNum = 0;

        for (double k = 0; k < 1.0; k += 0.02) {
            for (Line line : lines) {
                int x = (int) Math.floor(line.point1.x + (line.point2.x - line.point1.x) * k);
                int y = (int) Math.floor(line.point1.y + (line.point2.y - line.point1.y) * k);

                points[ptNum++] = new Point2D(x, y);
            }
        }

        draw(Arrays.copyOfRange(points, 0, ptNum - 1));
    }

    public void draw(Triangle triangle) {
        draw(
                new Line(triangle.point1, triangle.point2),
                new Line(triangle.point2, triangle.point3),
                new Line(triangle.point3, triangle.point1)
        );
    }
}
