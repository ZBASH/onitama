import java.util.ArrayList;

public final class Game {
    private Board board;
    private Ui ui;
    private ArrayList<Player> players;

    Game() {
        ui      = new Ui(System.out);
        board   = Board.create();
        players = new ArrayList<>(2);
    }

    private void start() {
        players.add(Player.createAtRow(0));
        players.add(Player.createAtRow(Config.BOARD_SIZE - 1));

        ui.render(board);
        ui.render(players);
        ui.flush();
    }

    // main
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
