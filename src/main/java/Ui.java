import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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
    void render(@NotNull Game game) {
        render(game.getBoard());
        render(game.getCurrentPlayer(), true);
        render(game.getOtherPlayer(), false);
    }

    private void render(@NotNull Board board) {
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

    private void render(@NotNull Player player, boolean isCurrentPlayer) {
        if (buffer == null) {
            throw new NoBoardError();
        }

        int i = 0;
        for (Pawn pawn : player.getPawns()) {
            if(!pawn.isCaptured()) {
                boolean isHighlighted = isCurrentPlayer && i == selectedPawnIndex;

                Color foreground = isHighlighted ? Color.NONE : player.getColor();
                Color background = isHighlighted ? player.getColor() : Color.NONE;

                render(pawn, foreground, background);
            }

            i++;
        }
    }

    private void render(@NotNull Pawn pawn, Color foreground, Color background) {
        if (buffer == null) {
            throw new NoBoardError();
        }

        int x = pawn.getX();
        int y = pawn.getY();

        if (y >= buffer.length || x >= buffer[y].length) {
            throw new OutOfBoardError();
        }

        buffer[y][x] = new Tile('#', foreground, background);
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
    private int selectedPawnIndex = 0;

    Integer pickPawnId() throws IOException, InterruptedException {
        out.println();
        out.print("select a pawn [< > enter]");

        String[] setRaw = {"/bin/sh", "-c", "stty raw </dev/tty"};
        Runtime.getRuntime().exec(setRaw).waitFor();

        Integer pawnId = null;
        try {
            int code = in.read();
            switch(code) {
                case 37:
                    selectedPawnIndex--;
                    break;
                case 39:
                    selectedPawnIndex++;
                    break;
                default:
                    break;
            }
        } catch (IOException error) {
            out.println("error");
        } finally {
            String[] setCooked = {"/bin/sh", "-c", "stty cooked </dev/tty"};
            Runtime.getRuntime().exec(setCooked).waitFor();
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
        private Color foreground;
        private Color background;

        Tile(char glyph) {
            this(glyph, Color.NONE, Color.NONE);
        }

        Tile(char glyph, Color foreground, Color background) {
            this.glyph      = glyph;
            this.foreground = foreground;
            this.background = background;
        }

        String render() {
            return prefix() + String.valueOf(glyph) + suffix();
        }

        private String prefix() {
            return prefixBackground() + prefixForeground();
        }

        private String prefixForeground() {
            switch (foreground) {
                case RED:
                    return "\033[0;31m";
                case BLUE:
                    return "\033[0;34m";
                default:
                    return "";
            }
        }

        private String prefixBackground() {
            switch (background) {
                case RED:
                    return "\033[41;1m";
                case BLUE:
                    return "\033[44;1m";
                default:
                    return "";
            }
        }


        private String suffix() {
            if(foreground == Color.NONE && background == Color.NONE) {
                return "";
            }

            return "\033[0m";
        }
    }
}
