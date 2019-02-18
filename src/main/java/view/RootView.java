package view;

import domain.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public final class RootView {
    private @NotNull PrintStream   out;
    private @NotNull InputStream   in;
    private @NotNull TileBuffer    mBuffer;
    private @NotNull Flash         mFlash;
    private @NotNull GameView      mGameView;
    private @NotNull PickPawnInput mPickPawnInput;

    public RootView(@NotNull PrintStream out, @NotNull InputStream in) {
        this.out  = out;
        this.in   = in;
        mFlash    = new Flash(this.out);
        mBuffer   = new TileBuffer(this.out);
        mGameView = new GameView(mBuffer);
        mPickPawnInput = new PickPawnInput(this.in, this.out);
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
        out.print("\033[H\033[2J");
        out.flush();
    }

    public void draw() {
        mBuffer.draw();
        mFlash.draw();
    }
}
