public class Point {
    private int x;
    private int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // operators
    Point add(Point other) {
        return new Point(
            x + other.getX(),
            y + other.getY()
        );
    }

    Point mirrorX() {
        return new Point(
            x,
            y * -1
        );
    }

    // accessors
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // factories
    public static Point zero() {
        return new Point(0, 0);
    }

    // equality
    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Point)) {
            return false;
        }

        Point point = (Point)other;
        return point.x == this.x
            && point.y == this.y;
    }

    // debugging
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
