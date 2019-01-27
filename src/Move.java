public class Move {
    private int   pawnId;
    private Point newPosition;

    Move(int pawnId, Point newPosition) {
        this.pawnId      = pawnId;
        this.newPosition = newPosition;
    }

    // accessors
    public int getPawnId() {
        return pawnId;
    }

    public Point getNewPosition() {
        return newPosition;
    }
}
