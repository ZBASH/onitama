public final class Game {
    private Board board;
    private Ui ui;

    Game() {
        board = new Board();
        ui    = new Ui(System.out);
    }

    private void start() {
        ui.renderBoard(board.getGrid());
    }

    // main
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
