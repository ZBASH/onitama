package view;

import org.jline.terminal.Terminal;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

class PickPawnInput {
    private Terminal mTerminal;
    private int mSelectedPawnIndex;

    PickPawnInput(Terminal terminal) {
        mTerminal = terminal;
        mSelectedPawnIndex = 0;
    }

    // commands
    void call() throws IOException, InterruptedException {
        mTerminal.writer().println();
        mTerminal.writer().print("select a pawn [< > enter]");

        int code = mTerminal.reader().read();
        switch(code) {
            case 37:
                mSelectedPawnIndex--;
                break;
            case 39:
                mSelectedPawnIndex++;
                break;
            default:
//                    mSelectedPawnIndex = null;
                break;
        }

        mTerminal.writer().println();
    }

    // queries
    Integer getSelectedPawnIndex() {
        return mSelectedPawnIndex;
    }
}
