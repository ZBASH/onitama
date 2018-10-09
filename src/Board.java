public final class Board {
    // constants
    private static final int SIZE = 5;
    private static final char SPACE = '*';

    // fields
    private char[][] grid;

    Board() {
        grid = new char[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = SPACE;
            }
        }
    }

    // accessors
    public char[][] getGrid() {
        return grid;
    }
}
