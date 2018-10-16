import java.util.ArrayList;

class Player {
    private ArrayList<Point> pawns;

    Player() {
        pawns = new ArrayList<>();
    }

    // accessors
    ArrayList<Point> getPawns() {
        return pawns;
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

