public class Move {
    private Pawn pawn;
    private Point point;

    Move(Pawn pawn, Point newPosition) {
        this.pawn  = pawn;
        this.point = newPosition;
    }

    public Point getPoint() {
        return point;
    }

    public Pawn getPawn() {
        return pawn;
    }
}
