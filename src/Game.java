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
        // current player makes move
        Pawn pawn = findCurrentPlayerPawnById(move.getPawnId());
        pawn.moveTo(move.getNewPosition());

        // capture opponents pawn if possible
        Pawn otherPawn = findOtherPlayerPawnByPosition(move.getNewPosition());
        if(otherPawn != null) {
            otherPawn.capture();
        }

        // swap current player
        currentPlayerId = 1 - currentPlayerId;
    }

    // queries
    Pawn findCurrentPlayerPawnById(int pawnId) {
        Player currentPlayer = getCurrentPlayer();
        if(pawnId < 0 || pawnId >= currentPlayer.getPawns().size()) {
            return null;
        }

        Pawn pawn = currentPlayer.getPawns().get(pawnId);
        if(pawn.isCaptured()) {
            return null;
        }

        return pawn;
    }

    Player getCurrentPlayer() {
        return getPlayers().get(currentPlayerId);
    }

    Pawn findCurrentPlayerPawnByPosition(Point position) {
        return getCurrentPlayer().findPawnByPosition(position);
    }

    boolean isFirstPlayerCurrentPlayer() {
        return currentPlayerId == 0;
    }

    private Player getOtherPlayer() {
        return getPlayers().get(1 - currentPlayerId);
    }

    private Pawn findOtherPlayerPawnByPosition(Point position) {
        return getOtherPlayer().findPawnByPosition(position);
    }

    // accessors
    Board getBoard() {
        return board;
    }

    ArrayList<Player> getPlayers() {
        return players;
    }
}
