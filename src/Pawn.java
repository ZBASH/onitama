public class Pawn {
    Point position;

    Pawn(Point position) {
        this.position = position;
    }

    // mutations
    public void moveTo(Point position) {
        this.position = position;
    }

    // accessors
    public Point getPosition() {
        return position;
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }
}
