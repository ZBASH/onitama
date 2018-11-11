public class Move {
    private int   pawnId;
    private Point delta;

    Move(int pawnId, Point delta) {
        this.pawnId = pawnId;
        this.delta  = delta;
    }

    // accessors
    public int getPawnId() {
        return pawnId;
    }

    public Point getDelta() {
        return delta;
    }
}
