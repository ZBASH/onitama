package view;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

class PickPawnInput {
    private InputStream mIn;
    private PrintStream mOut;
    private Integer mSelectedPawnIndex;

    PickPawnInput(InputStream in, PrintStream out) {
        mIn  = in;
        mOut = out;
        mSelectedPawnIndex = 0;
    }

    // commands
    void call() throws IOException, InterruptedException {
        mOut.println();
        mOut.print("select a pawn [< > enter]");

        String[] setRaw = {"/bin/sh", "-c", "stty raw </dev/tty"};
        Runtime.getRuntime().exec(setRaw).waitFor();

        Integer pawnId = null;
        try {
            int code = mIn.read();
            switch(code) {
                case 37:
                    mSelectedPawnIndex--;
                    break;
                case 39:
                    mSelectedPawnIndex++;
                    break;
                default:
                    mSelectedPawnIndex = null;
                    break;
            }
        } catch (IOException error) {
            mOut.println("error");
        } finally {
            String[] setCooked = {"/bin/sh", "-c", "stty cooked </dev/tty"};
            Runtime.getRuntime().exec(setCooked).waitFor();
        }

        mOut.println();
    }

    // queries
    Integer getSelectedPawnIndex() {
        return mSelectedPawnIndex;
    }
}
