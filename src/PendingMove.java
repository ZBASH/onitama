import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

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
        this.delta  = delta;
    }

    // queries
    Move getValidMove() {
        if(isMoveInvalid()) {
            return null;
        }

        return new Move(pawnId, delta);
    }

    private boolean isMoveInvalid() {
        Pawn pawn = game.findCurrentPlayerPawnById(pawnId);
        if(pawn == null) {
            return true;
        }
         
        Point newPosition = pawn.getPosition().add(delta);
        return game.getBoard().containsPoint(newPosition);
    }
}
