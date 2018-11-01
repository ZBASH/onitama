import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

class Player {
    private Color color;
    private ArrayList<Point> pawns;

    Player() {
        this.color = Color.NONE;
        this.pawns = new ArrayList<>();
    }

    // mutators
    void chooseColor(@NotNull Color color) {
        this.color = color;
    }

    // accessors
    ArrayList<Point> getPawns() {
        return pawns;
    }

    Color getColor() {
        return color;
    }

    // factories
    static Player createAtRow(int row) {
        return createAtRow(row, Config.BOARD_SIZE);
    }

    static Player createAtRow(int row, int size) {
        Player player = new Player();

        for(int x = 0; x < size; x++) {
            player.pawns.add(new Point(x, row));
        }

        return player;
    }
}

