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

    void makeMove(Move move) {
        Pawn pawn = findCurrentPlayerPawnById(move.getPawnId());
        pawn.moveTo(move.getNewPosition());

        // swap current player
        currentPlayerId = 1 - currentPlayerId;
    }

    // queries
    Pawn findCurrentPlayerPawnById(int pawnId) {
        Player currentPlayer = getCurrentPlayer();
        if(pawnId < 0 || pawnId >= currentPlayer.getPawns().size()) {
            return null;
        }

        return currentPlayer.getPawns().get(pawnId);
    }

    Pawn findPawnByPosition(Point position) {
        for(Player player : players) {
            for(Pawn pawn : player.getPawns()) {
                if(pawn.getPosition().equals(position)) {
                    return pawn;
                }
            }
        }

        return null;
    }

    // accessors
    Board getBoard() {
        return board;
    }

    ArrayList<Player> getPlayers() {
        return players;
    }

    Player getCurrentPlayer() {
        return getPlayers().get(currentPlayerId);
    }
}
