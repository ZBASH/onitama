package domain;

import domain.Game;
import domain.Move;
import domain.PendingMove;
import domain.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PendingMoveTest {
    @Test
    void itCreatesValidMovesForTheFirstPlayer() {
        Game game = new Game();
        game.start();
        PendingMove pendingMove = new PendingMove(game);

        pendingMove.pickPawn(1);
        pendingMove.pickCard(new Point(0, 1));
        Move actual = pendingMove.getValidMove().getMove();

        assertNotNull(actual);
        assertEquals(1, actual.getPawnId());
        assertEquals(new Point(1, 1), actual.getNewPosition());
    }

    @Test
    void itCreatesValidMovesForTheSecondPlayer() {
        Game game = new Game();
        game.start();
        game.makeMove(new Move(0, new Point(0,0)));
        PendingMove pendingMove = new PendingMove(game);

        pendingMove.pickPawn(1);
        pendingMove.pickCard(new Point(0, 1));
        Move actual = pendingMove.getValidMove().getMove();

        assertNotNull(actual);
        assertEquals(1, actual.getPawnId());
        assertEquals(new Point(1, 3), actual.getNewPosition());
    }

    @Test
    void itDisallowsInvalidPawns() {
        Game game = new Game();
        game.start();
        PendingMove pendingMove = new PendingMove(game);

        pendingMove.pickPawn(100);
        Error actual = pendingMove.getValidMove().getError();

        assertTrue(actual instanceof PendingMove.InvalidPawnId);
    }

    @Test
    void itDisallowsOutOfBoundsMoves() {
        Game game = new Game();
        game.start();
        PendingMove pendingMove = new PendingMove(game);

        pendingMove.pickPawn(2);
        pendingMove.pickCard(new Point(3, 0));
        Error actual = pendingMove.getValidMove().getError();

        assertTrue(actual instanceof PendingMove.PositionOutOfBounds);
    }

    @Test
    void itDisallowsMovesToOccupiedSpaces() {
        Game game = new Game();
        game.start();
        PendingMove pendingMove = new PendingMove(game);

        pendingMove.pickPawn(0);
        pendingMove.pickCard(new Point(1, 0));
        Error actual = pendingMove.getValidMove().getError();

        assertTrue(actual instanceof PendingMove.PositionWasOccuppied);
    }
}
