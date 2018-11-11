public class Pawn {
    private Point position;

    Pawn(Point position) {
        this.position = position;
    }

    // commands
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
