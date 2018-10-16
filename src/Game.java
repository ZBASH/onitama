public final class Game {
    private Board board;
    private Ui ui;
    private Player[] players;

    Game() {
        ui      = new Ui(System.out);
        board   = Board.create();
        players = new Player[2];
    }

    private void start() {
        players[0] = Player.createAtRow(0);
        players[1] = Player.createAtRow(Config.BOARD_SIZE - 1);

        ui.render(board);
        ui.flush();
    }

    // main
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
