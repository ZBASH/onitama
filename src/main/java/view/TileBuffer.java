package view;

import org.jetbrains.annotations.NotNull;

import java.io.PrintStream;

class TileBuffer {
    private @NotNull PrintStream mOut;
    private Tile[][] mBuffer;

    // lifetime
    TileBuffer(@NotNull PrintStream out) {
        mOut = out;
    }

    // commands
    void initialize(int length) {
        mBuffer = new Tile[length][length];
    }

    void set(int x, int y, Tile tile) {
        mBuffer[y][x] = tile;
    }

    void draw() {
        if (!isInitialized()) {
            throw new Errors.NoBoard();
        }

        for (Tile[] row : mBuffer) {
            int i = 0;

            for (Tile tile : row) {
                mOut.print(tile.render());
                mOut.print(i++ == row.length - 1 ? '\n' : ' ');
            }
        }

        mBuffer = null;
    }

    // queries
    boolean isInitialized() {
        return mBuffer != null;
    }

    boolean contains(int x, int y) {
        return y < mBuffer.length && x < mBuffer[y].length;
    }
}
