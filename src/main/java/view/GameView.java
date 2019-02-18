package view;

import domain.Game;
import org.jetbrains.annotations.NotNull;

class GameView {
    private @NotNull TileBuffer mBuffer;
    private @NotNull BoardView  mBoardView;
    private @NotNull PlayerView mPlayerView;

    GameView(@NotNull TileBuffer buffer) {
        mBuffer     = buffer;
        mBoardView  = new BoardView(mBuffer);
        mPlayerView = new PlayerView(mBuffer);
    }

    void render(@NotNull Game game, Integer selectedPawnIndex) {
        mBoardView.render(game.getBoard());
        mPlayerView.render(game.getCurrentPlayer(), true, selectedPawnIndex);
        mPlayerView.render(game.getOtherPlayer(), true, selectedPawnIndex);
    }
}
