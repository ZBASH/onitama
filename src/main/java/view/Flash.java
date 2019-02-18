package view;

import domain.PendingMove;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.PrintStream;

public class Flash {
    private String      mText;
    private PrintStream mOut;

    // lifetime
    Flash(PrintStream out) {
        mOut = out;
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
            mOut.println();
            mOut.println(mText);
        }

        mText = null;
    }
}
