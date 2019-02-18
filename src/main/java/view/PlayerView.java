package view;

import core.Color;
import domain.Pawn;
import domain.Player;
import org.jetbrains.annotations.NotNull;

class PlayerView {
    private @NotNull TileBuffer mBuffer;

    PlayerView(@NotNull TileBuffer buffer) {
        mBuffer = buffer;
    }

    void render(@NotNull Player player, boolean isCurrentPlayer, int selectedPawnIndex) {
        if (!mBuffer.isInitialized()) {
            throw new Errors.NoBoard();
        }

        int i = 0;
        for (Pawn pawn : player.getPawns()) {
            if(!pawn.isCaptured()) {
                boolean isHighlighted = isCurrentPlayer && i == selectedPawnIndex;

                Color foreground = isHighlighted ? Color.NONE : player.getColor();
                Color background = isHighlighted ? player.getColor() : Color.NONE;

                render(pawn, foreground, background);
            }

            i++;
        }
    }

    private void render(@NotNull Pawn pawn, Color foreground, Color background) {
        if (!mBuffer.isInitialized()) {
            throw new Errors.NoBoard();
        }

        int x = pawn.getX();
        int y = pawn.getY();

        if (!mBuffer.contains(x, y)) {
            throw new Errors.OutOfBoard();
        }

        mBuffer.set(x, y, new Tile('#', foreground, background));
    }
}
