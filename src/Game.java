import java.util.ArrayList;

final class Game {
    private int currentPlayerId;
    private Board board;
    private ArrayList<Player> players;

    Game() {
        currentPlayerId = 0;
        board   = Board.create();
        players = new ArrayList<>(2);
    }

    // command
    void start() {
        Player player1 = Player.createAtRow(0);
        player1.chooseColor(Color.RED);
        players.add(player1);

        Player player2 = Player.createAtRow(Config.BOARD_SIZE - 1);
        player2.chooseColor(Color.BLUE);
        players.add(player2);
    }

    // queries
    Pawn findCurrentPlayerPawnById(int pawnId) {
        Player currentPlayer = getPlayers().get(currentPlayerId);
        if(pawnId < 0 || pawnId > currentPlayer.getPawns().size()) {
            return null;
        }

        return currentPlayer.getPawns().get(pawnId);
    }

    // accessors
    Board getBoard() {
        return board;
    }

    ArrayList<Player> getPlayers() {
        return players;
    }
}
