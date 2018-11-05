import java.util.ArrayList;

public final class Game {
    private Board board;
    private ArrayList<Player> players;
    private Player currentPlayer;

    Game() {
        board   = Board.create();
        players = new ArrayList<>(2);
    }

    public void start() {
        Player player1 = Player.createAtRow(0);
        player1.chooseColor(Color.RED);
        players.add(player1);

        Player player2 = Player.createAtRow(Config.BOARD_SIZE - 1);
        player2.chooseColor(Color.BLUE);
        players.add(player2);

        currentPlayer = player1;
    }

    public Board getBoard() {
        return board;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void updateBoard(Move move) {

    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
