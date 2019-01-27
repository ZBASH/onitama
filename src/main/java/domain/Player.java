package domain;

import core.Color;
import core.Config;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public final class Player {
    private Color color;
    private ArrayList<Pawn> pawns;
    private Pawn pawnToMove;

    Player() {
        this.color = Color.NONE;
        this.pawns = new ArrayList<>();
    }

    // commands
    void chooseColor(@NotNull Color color) {
        this.color = color;
    }

    void movePawn(Pawn pawn, Point position) {
        pawnToMove = pawn;
        pawnToMove.moveTo(position);
    }

    // queries
    Pawn findPawnByPosition(Point position) {
        for(Pawn pawn : getPawns()) {
            if(pawn.getPosition().equals(position)) {
                return pawn;
            }
        }

        return null;
    }

    // accessors
    public ArrayList<Pawn> getPawns() {
        return pawns;
    }

    public Color getColor() {
        return color;
    }

    Pawn pawnToMove() {
        return pawnToMove;
    }

    // factories
    static Player createAtRow(int row) {
        return createAtRow(row, Config.BOARD_SIZE);
    }

    static Player createAtRow(int row, int size) {
        Player player = new Player();

        for(int x = 0; x < size; x++) {
            player.pawns.add(new Pawn(new Point(x, row)));
        }

        return player;
    }
}

