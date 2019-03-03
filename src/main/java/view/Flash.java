package view;

import domain.PendingMove;
import org.jetbrains.annotations.NotNull;
import org.jline.terminal.Terminal;

class Flash {
    private String   mText;
    private Terminal mTerminal;

    // lifetime
    Flash(Terminal terminal) {
        mTerminal = terminal;
    }

    // commands
    void render(@NotNull Error error) {
        if(error instanceof PendingMove.PositionWasOccuppied) {
            mText = "The position " + ((PendingMove.PositionWasOccuppied) error).getNewPosition() + " is occupied.";
        } else if (error instanceof PendingMove.PositionOutOfBounds) {
            mText = "The position " + ((PendingMove.PositionOutOfBounds) error).getNewPosition() + " is out of bounds.";
        } else if (error instanceof PendingMove.InvalidPawnId) {
            mText = "There is no pawn with id " + ((PendingMove.InvalidPawnId) error).getPawnId();
        }
    }

    void draw() {
        if(mText != null) {
            mTerminal.writer().println();
            mTerminal.writer().println(mText);
        }

        mText = null;
    }
}
