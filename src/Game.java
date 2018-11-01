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
        Player player1 = Player.createAtRow(0);
        player1.chooseColor(Color.RED);
        players.add(player1);

        Player player2 = Player.createAtRow(Config.BOARD_SIZE - 1);
        player2.chooseColor(Color.BLUE);
        players.add(player2);

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
