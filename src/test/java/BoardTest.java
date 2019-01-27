import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    @Test
    void itShowsTheInitialGrid() {
        Board board = Board.create();

        assertEquals(board.getGrid().length,    5);
        assertEquals(board.getGrid()[0].length, 5);
    }
}
