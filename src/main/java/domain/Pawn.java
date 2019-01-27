package domain;

public final class Pawn {
    private Point position;
    private Boolean isCaptured;

    Pawn(Point position) {
        this.position   = position;
        this.isCaptured = false;
    }

    // commands
    void moveTo(Point position) {
        this.position = position;
    }

    void capture() {
        isCaptured = true;
    }

    // accessors
    Point getPosition() {
        return position;
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    public boolean isCaptured() {
        return isCaptured;
    }
}
