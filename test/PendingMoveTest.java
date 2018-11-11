import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PendingMoveTest {
    @Test
    void itCreatesValidMoves() {
        Game game = new Game();
        game.start();
        PendingMove pendingMove = new PendingMove(game);

        pendingMove.pickPawn(0);
        pendingMove.pickCard(new Point(0,1));
        Move actual = pendingMove.getValidMove();

        assertEquals(0, actual.getPawnId());
        assertEquals(0, actual.getDelta().getX());
        assertEquals(1, actual.getDelta().getY());
    }

    @Test
    void itIgnoresInvalidPawns() {
        Game game = new Game();
        game.start();
        PendingMove pendingMove = new PendingMove(game);

        pendingMove.pickPawn(100);
        Move actual = pendingMove.getValidMove();

        assertEquals(null, actual);
    }

    @Test
    void itIgnoredOutOfBoundsMoves() {
        Game game = new Game();
        game.start();
        PendingMove pendingMove = new PendingMove(game);

        pendingMove.pickPawn(2);
        pendingMove.pickCard(new Point(3, 0));
        Move actual = pendingMove.getValidMove();

        assertEquals(null, actual);
    }
}