package domain;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class PendingMove {
    // dependency
    @NotNull
    private Game game;

    // form properties
    @Nullable
    private int pawnId;

    @Nullable
    private Point card;

    public PendingMove(@NotNull Game game) {
        this.game = game;
    }

    // commands
    public void pickPawn(int pawnId) {
        this.pawnId = pawnId;
    }

    public void pickCard(@NotNull Point card) {
        this.card = card;
    }

    // queries
    @NotNull
    public Result getValidMove() {
        Pawn pawn = game.findCurrentPlayerPawnById(pawnId);
        if(pawn == null) {
            return new Result(new InvalidPawnId(pawnId));
        }

        Point delta = card;
        if(!game.isFirstPlayerCurrentPlayer()) {
            delta = delta.mirrorX();
        }

        Point newPosition = pawn.getPosition().add(delta);
        if(!game.getBoard().containsPoint(newPosition)) {
            return new Result(new PositionOutOfBounds(newPosition));
        }

        if(game.findCurrentPlayerPawnByPosition(newPosition) != null) {
            return new Result(new PositionWasOccuppied(newPosition));
        }

        return new Result(new Move(pawnId, newPosition));
    }

    public class Result {
        @Nullable
        private Error error;

        @Nullable
        private Move move;

        Result(@NotNull Move move) {
            this.move = move;
        }

        Result(@NotNull Error error) {
            this.error = error;
        }

        // accessors
        @Nullable
        public Error getError() {
            return error;
        }

        @Nullable
        public Move getMove() {
            return move;
        }
    }

    public static class InvalidPawnId extends Error {
        private int pawnId;

        InvalidPawnId(int pawnId) {
            this.pawnId = pawnId;
        }

        public int getPawnId() {
            return pawnId;
        }
    }

    public static class PositionOutOfBounds extends Error {
        @NotNull
        private Point newPosition;

        PositionOutOfBounds(@NotNull Point newPosition) {
            this.newPosition = newPosition;
        }

        @NotNull
        public Point getNewPosition() {
            return newPosition;
        }
    }

    public static class PositionWasOccuppied extends Error {
        @NotNull
        private Point newPosition;

        PositionWasOccuppied(@NotNull Point newPosition) {
            this.newPosition = newPosition;
        }

        @NotNull
        public Point getNewPosition() {
            return newPosition;
        }
    }
}
