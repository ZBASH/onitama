import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PendingMoveTest {

    @Test
    void itCreatesAPendingMove() {
        Pawn pawn = new Pawn(Point.zero());
        Point point = new Point(1,2);
        PendingMove pendingMove = new PendingMove(pawn, point);

        assertEquals(1, pendingMove.getPoint().getX());
        assertEquals(2, pendingMove.getPoint().getY());
    }
}