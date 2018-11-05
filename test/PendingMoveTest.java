import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PendingMoveTest {

    @Test
    void itCreatesAPendingMove() {
        Game game = new Game();
        PendingMove pendingMove = new PendingMove(game);

        assertEquals(true, pendingMove.getValidMove());
    }
}