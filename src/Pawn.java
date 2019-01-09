class Pawn {
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

    int getX() {
        return position.getX();
    }

    int getY() {
        return position.getY();
    }

    boolean isCaptured() {
        return isCaptured;
    }
}
