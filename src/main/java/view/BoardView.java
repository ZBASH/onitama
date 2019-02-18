package view;

import domain.Board;
import org.jetbrains.annotations.NotNull;

class BoardView {
    private @NotNull TileBuffer mBuffer;

    BoardView(TileBuffer buffer) {
        mBuffer = buffer;
    }

    void render(@NotNull Board board) {
        char[][] grid = board.getGrid();

        mBuffer.initialize(grid.length);
        for (int y = 0; y < grid.length; y++) {
            char[] row = grid[y];

            for (int x = 0; x < row.length; x++) {
                mBuffer.set(x, y, new Tile(row[x]));
            }
        }
    }
}
