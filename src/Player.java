import java.util.ArrayList;

class Player {
    private ArrayList<Point> pawns;

    Player() {
        pawns = new ArrayList<>();
    }

    // setup
    void assignStartingRow(int row) {
        for(int x = 0; x < Config.BOARD_SIZE; x++) {
            pawns.add(new Point(x, row));
        }
    }

    // accessors
    ArrayList<Point> getPawns() {
        return pawns;
    }
}
