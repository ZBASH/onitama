package view;

import org.jline.terminal.Terminal;

class TileBuffer {
    private Terminal mTerminal;
    private Tile[][] mBuffer;

    // lifetime
    TileBuffer(Terminal terminal) {
        mTerminal = terminal;
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
                mTerminal.writer().print(tile.render());
                mTerminal.writer().print(i++ == row.length - 1 ? '\n' : ' ');
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
