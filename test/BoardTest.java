import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    @Test
    void itShowsTheInitialGrid() {
        Board board = new Board();

        char[][] expected = grid(
            "* * * * *" +
            "* * * * *" +
            "* * * * *" +
            "* * * * *" +
            "* * * * *"
        );

        assertTrue(Arrays.deepEquals(expected, board.getGrid()));
    }

    // helpers
    private char[][] grid(String source) {
        char[]   chars  = source.replace(" ", "").toCharArray();
        char[][] result = new char[5][5];

        for (int i = 0; i < 5; i++) {
            int start = i * 5;
            result[i] = Arrays.copyOfRange(chars, start, start + 5);
        }

        return result;
    }
}
