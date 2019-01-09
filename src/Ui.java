import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

final class Ui {
    private PrintStream out;
    private InputStream in;
    private Tile[][] buffer;
    private String flash;

    Ui(PrintStream out, InputStream in) {
        this.out = out;
        this.in  = in;
    }

    // compositing
    void render(@NotNull Board board) {
        char[][] grid = board.getGrid();

        buffer = new Tile[grid.length][];
        for (int y = 0; y < grid.length; y++) {
            char[] row = grid[y];

            buffer[y] = new Tile[row.length];
            for (int x = 0; x < row.length; x++) {
                buffer[y][x] = new Tile(row[x]);
            }
        }
    }

    void render(@NotNull ArrayList<Player> players) {
        if (buffer == null) {
            throw new NoBoardError();
        }

        for (Player player : players) {
            for (Pawn pawn : player.getPawns()) {
                if(!pawn.isCaptured()) {
                    render(pawn, player.getColor());
                }
            }
        }
    }

    private void render(@NotNull Pawn pawn, Color color) {
        if (buffer == null) {
            throw new NoBoardError();
        }

        int x = pawn.getX();
        int y = pawn.getY();

        if (y >= buffer.length || x >= buffer[y].length) {
            throw new OutOfBoardError();
        }

        buffer[y][x] = new Tile('#', color);
    }

    // errors
    void flash(@NotNull Error error) {
        if(error instanceof PendingMove.PositionWasOccuppied) {
            flash = "The position " + ((PendingMove.PositionWasOccuppied) error).getNewPosition() + " is occupied.";
        } else if (error instanceof PendingMove.PositionOutOfBounds) {
            flash = "The position " + ((PendingMove.PositionOutOfBounds) error).getNewPosition() + " is out of bounds.";
        } else if (error instanceof PendingMove.InvalidPawnId) {
            flash = "There is no pawn with id " + ((PendingMove.InvalidPawnId) error).getPawnId();
        }
    }

    // display
    void clear() {
        out.print("\033[H\033[2J");
        out.flush();
    }

    void flush() {
        if (buffer == null) {
            throw new NoBoardError();
        }

        for (Tile[] row : buffer) {
            int i = 0;

            for (Tile tile : row) {
                out.print(tile.render());
                out.print(i++ == row.length - 1 ? '\n' : ' ');
            }
        }

        buffer = null;

        if(flash != null) {
            out.println();
            out.println(flash);
        }

        flash = null;
    }

    // user input
    int pickPawnId() {
        out.println();
        out.print("enter pawn id [0-4]: ");

        Scanner scanner = new Scanner(in);

        Integer pawnId = null;
        while (pawnId == null) {
            String input = scanner.next();
            try {
                pawnId = Integer.parseInt(input);
            } catch (NumberFormatException ignored) {
                // continue with loop until success
            }
        }

        out.println();

        return pawnId;
    }

    // errors
    class NoBoardError extends RuntimeException {
    }

    class OutOfBoardError extends RuntimeException {
    }

    // tile
    private class Tile {
        private char glyph;
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
            switch (color) {
                case RED:
                    return "\033[0;31m";
                case BLUE:
                    return "\033[0;34m";
                default:
                    return "";
            }
        }

        private String suffix() {
            switch (color) {
                case NONE:
                    return "";
                default:
                    return "\033[0m";
            }
        }
    }
}
