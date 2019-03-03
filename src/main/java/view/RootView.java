package view;

import domain.*;
import org.jetbrains.annotations.NotNull;
import org.jline.terminal.Terminal;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public final class RootView {
    private @NotNull Terminal      mTerminal;
    private @NotNull TileBuffer    mBuffer;
    private @NotNull Flash         mFlash;
    private @NotNull GameView      mGameView;
    private @NotNull PickPawnInput mPickPawnInput;

    public RootView(@NotNull Terminal terminal) {
        mTerminal = terminal;
        mFlash    = new Flash(terminal);
        mBuffer   = new TileBuffer(terminal);
        mGameView = new GameView(mBuffer);
        mPickPawnInput = new PickPawnInput(terminal);
    }

    // commands
    public Integer pickPawnId() throws IOException, InterruptedException   {
        mPickPawnInput.call();
        return mPickPawnInput.getSelectedPawnIndex();
    }

    // render
    public void renderGame(@NotNull Game game) {
        mGameView.render(game, mPickPawnInput.getSelectedPawnIndex());
    }

    public void renderError(@NotNull Error error) {
        mFlash.render(error);
    }

    // draw
    public void clear() {
        mTerminal.writer().print("\033[H\033[2J");
        mTerminal.writer().flush();
    }

    public void draw() {
        mBuffer.draw();
        mFlash.draw();
        mTerminal.flush();
    }
}
