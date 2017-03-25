package objects.shapes;

/**
 * Created by ksenia on 22.03.2017.
 */
public class Triangle implements Shape {
    private int a;
    private int b;
    private int c;

    public Triangle(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double getPerimeter() {
        return a + b + c;
    }

    @Override
    public double getArea() {
        return Math.sqrt(getPerimeter() * (getPerimeter() - a) * (getPerimeter() - b) * (getPerimeter() - c));
    }

}
