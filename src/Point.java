public class Point {
    private int x;
    private int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Point)) {
            return false;
        }

        Point point = (Point)other;
        return point.x == this.x
            && point.y == this.y;
    }
}
