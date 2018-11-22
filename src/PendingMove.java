class PendingMove {
    // dependency
    private Game game;

    // form properties
    private int   pawnId;
    private Point delta;

    PendingMove(Game game) {
        this.game = game;
    }

    // commands
    void pickPawn(int pawnId) {
        this.pawnId = pawnId;
    }

    void pickCard(Point delta) {
        this.delta = delta;
    }

    // queries
    Move getValidMove() {
        Pawn pawn = game.findCurrentPlayerPawnById(pawnId);
        if(pawn == null) {
            return null;
        }

        Point newPosition = pawn.getPosition().add(delta);
        if(!game.getBoard().containsPoint(newPosition)) {
            return null;
        }

        if(game.findPawnByPosition(newPosition) != null) {
            return null;
        }

        return new Move(pawnId, newPosition);
    }
}
