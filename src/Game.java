public class Game {
    Game() {
        initBoard();
    }

    // -- BOARD --

    // constants
    private static final int  BOARD_SIZE  = 5;
    private static final char BOARD_SPACE = '*';

    // fields
    private char[][] board;

    // initialization
    private void initBoard() {
        board = new char[BOARD_SIZE][BOARD_SIZE];

        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = BOARD_SPACE;
            }
        }
    }

    // main
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
