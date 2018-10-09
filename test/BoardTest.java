import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import factories.GridFactory;

class BoardTest {
    @Test
    void itShowsTheInitialGrid() {
        Board board = new Board();

        char[][] expected = GridFactory.grid(
            "* * * * *" +
            "* * * * *" +
            "* * * * *" +
            "* * * * *" +
            "* * * * *"
        );

        assertTrue(Arrays.deepEquals(expected, board.getGrid()));
    }
}
