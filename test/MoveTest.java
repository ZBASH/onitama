import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {
    @Test
    void itCreatesAMove() {
        Move move = new Move(new Pawn(new Point(1,1)),Point.zero());

        assertEquals(0, move.getPoint().getX());
        assertEquals(0, move.getPoint().getY());
    }
}