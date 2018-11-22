public final class Board {
    // fields
    private char[][] grid;

    public Board(char[][] grid) {
        this.grid = grid;
    }

    // queries
    boolean containsPoint(Point point) {
        return point.getX() >= 0 && point.getX() < grid.length
            && point.getY() >= 0 && point.getY() < grid.length;
    }

    // accessors
    public char[][] getGrid() {
        return grid;
    }

    // factories
    public static Board create() {
        return create(Config.BOARD_SIZE);
    }
    
    public static Board create(int size) {
        char[][] grid = new char[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = Config.BOARD_SPACE;
            }
        }

        return new Board(grid);
    }
}
