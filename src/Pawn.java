public class Pawn {
    private Point position;

    Pawn(Point position) {
        this.position = position;
    }

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
