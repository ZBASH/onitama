import org.jetbrains.annotations.NotNull;

import java.io.PrintStream;
import java.util.ArrayList;

final class Ui {
    private PrintStream out;
    private Tile[][]    buffer;

    Ui(PrintStream out) {
        this.out = out;
    }

    // compositing
    void render(@NotNull Board board) {
        char[][] grid = board.getGrid();

        buffer = new Tile[grid.length][];
        for(int y = 0; y < grid.length; y++) {
            char[] row = grid[y];

            buffer[y] = new Tile[row.length];
            for(int x = 0; x < row.length; x++) {
                buffer[y][x] = new Tile(row[x]);
            }
        }
    }

    void render(@NotNull ArrayList<Player> players) {
        if(buffer == null) {
            throw new NoBoardError();
        }

        for(Player player : players) {
            for(Pawn pawn : player.getPawns()) {
                render(pawn, player.getColor());
            }
        }
    }

    private void render(@NotNull Pawn pawn, Color color) {
        if(buffer == null) {
            throw new NoBoardError();
        }

        int x = pawn.getX();
        int y = pawn.getY();

        if(y >= buffer.length || x >= buffer[y].length) {
            throw new OutOfBoardError();
        }

        buffer[y][x] = new Tile('#', color);
    }

    // display
    void flush() {
        if(buffer == null) {
            throw new NoBoardError();
        }

        for(Tile[] row : buffer) {
            int i = 0;

            for(Tile tile : row) {
                out.print(tile.render());
                out.print(i++ == row.length - 1 ? '\n' : ' ');
            }
        }

        buffer = null;
    }

    // errors
    class NoBoardError extends RuntimeException {
    }

    class OutOfBoardError extends RuntimeException {
    }

    // tile
    private class Tile {
        private char  glyph;
        private Color color;

        Tile(char glyph) {
            this(glyph, Color.NONE);
        }

        Tile(char glyph, Color color) {
            this.glyph = glyph;
            this.color = color;
        }

        String render() {
            return prefix() + String.valueOf(glyph) + suffix();
        }

        private String prefix() {
            switch(color) {
                case RED:
                    return "\033[0;31m";
                case BLUE:
                    return "\033[0;34m";
                default:
                    return "";
            }
        }

        private String suffix() {
            switch(color) {
                case NONE:
                    return "";
                default:
                    return "\033[0m";
            }
        }
    }
}
