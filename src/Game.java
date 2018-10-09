public final class Game {
    private Board board;
    private Ui ui;
    private Player[] players;

    Game() {
        ui      = new Ui(System.out);
        board   = new Board();
        players = new Player[2];
    }

    private void start() {
        players[0] = new Player();
        players[1] = new Player();
        players[0].assignStartingRow(0);
        players[1].assignStartingRow(Config.BOARD_SIZE - 1);

        ui.renderBoard(board.getGrid());
    }

    // main
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
