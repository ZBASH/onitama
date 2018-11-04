public class PendingMove {
    private Pawn pawn;
    private Point point;

    PendingMove(Pawn pawn, Point point) {
        this.pawn = pawn;
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }
}
