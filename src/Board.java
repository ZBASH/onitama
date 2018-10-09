final class Board {
    // fields
    private char[][] grid;

    Board() {
        grid = new char[Config.BOARD_SIZE][Config.BOARD_SIZE];

        for (int i = 0; i < Config.BOARD_SIZE; i++) {
            for (int j = 0; j < Config.BOARD_SIZE; j++) {
                grid[i][j] = Config.BOARD_SPACE;
            }
        }
    }

    // accessors
    char[][] getGrid() {
        return grid;
    }
}
